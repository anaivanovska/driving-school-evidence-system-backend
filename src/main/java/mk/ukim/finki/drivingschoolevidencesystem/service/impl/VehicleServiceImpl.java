package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.VehicleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.search.SearchRepositoryImpl;
import mk.ukim.finki.drivingschoolevidencesystem.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMaper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SearchRepositoryImpl searchRepositoryImpl;

    @Transactional
    @Override
    public Page<VehicleDTO> findAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable)
                                .map(vehicle -> modelMaper.map(vehicle, VehicleDTO.class));
    }

    @Transactional
    @Override
    public List<VehicleDTO> findAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> modelMaper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public VehicleDTO createNew(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(vehicleDTO.getRegistrationNumber());
        if(vehicle != null) {
            throw new TrafficSchoolException("Vehicle with type = " + vehicleDTO.getType() + ", brand = " + vehicleDTO.getBrand() + ", registrationNumber = " + vehicleDTO.getRegistrationNumber() + " already exists.");
        }
        vehicle = new Vehicle();
        String categoryName = vehicleDTO.getCategoryName();
        long instructorId = vehicleDTO.getInstructorId();
        vehicle = modelMaper.map(vehicleDTO, Vehicle.class);
        vehicle.setCategory(this.getCategory(categoryName));
        vehicle.setInstructor(this.findInstructor(instructorId));
        Vehicle result = vehicleRepository.save(vehicle);
        vehicleDTO = modelMaper.map(result, VehicleDTO.class);
        return vehicleDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Category getCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if(category == null) {
            throw new TrafficSchoolException("Invalid category name " + categoryName + ". Category does not exist.");
        }
        return category;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User findInstructor(long id) {
       return userRepository.findById(id).orElseThrow(() -> new TrafficSchoolException("User with id " + id + " not found"));
    }

    private void setData(Vehicle vehicle, VehicleDTO vehicleDTO) {
        String categoryName = vehicleDTO.getCategoryName();
        long instructorId = vehicleDTO.getInstructorId();
        vehicle.setType(vehicleDTO.getType());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setRegistrationDate(vehicleDTO.getRegistrationDate());
        vehicle.setCategory(this.getCategory(categoryName));
        vehicle.setInstructor(this.findInstructor(instructorId));
    }

    @Transactional
    @Override
    public VehicleDTO edit(VehicleDTO vehicleDTO) {
        long id = vehicleDTO.getId();
        Vehicle vehicle = vehicleRepository.findById(vehicleDTO.getId())
                            .orElseThrow(() -> new TrafficSchoolException(id +" - Vehicle does not exist."));

        setData(vehicle, vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        vehicleDTO = modelMaper.map(vehicle, VehicleDTO.class);
        return vehicleDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        vehicleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<VehicleDTO> search(String value, Pageable pageable) {
        List<VehicleDTO> vehicles =  searchRepositoryImpl.searchPhrase(Vehicle.class, value, "brand", "type", "registrationNumber")
                .stream()
                .map(vehicle -> modelMaper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());

        Page<VehicleDTO> vehicleDTOPage = new PageImpl<>(vehicles, pageable, vehicles.size());
        return vehicleDTOPage;
    }
}
