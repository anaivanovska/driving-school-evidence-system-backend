package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.VehicleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public VehicleDTO createNew(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(vehicleDTO.getRegistrationNumber());
        if(vehicle != null) {
            throw new TrafficSchoolException("Vehicle with type = " + vehicleDTO.getType() + ", brand = " + vehicleDTO.getBrand() + ", registrationNumber = " + vehicleDTO.getRegistrationNumber() + " already exists.");
        }
        setData(vehicle, vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        vehicleDTO = modelMaper.map(vehicle, VehicleDTO.class);
        return vehicleDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Category getCategory(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        if(category == null) {
            throw new TrafficSchoolException("Invalid category name " + categoryName + ". Category does not exist.");
        }
        return category;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User getInstructor(long id) {
        User instructor = userRepository.findUserByIdAndRoles_name(id, Constants.Role.INSTRUCTOR.name())
                .orElseThrow(() -> new TrafficSchoolException("Instructor with id = " + id + " does not exist"));

        return instructor;
    }

    private void setData(Vehicle vehicle, VehicleDTO vehicleDTO) {
        String categoryName = vehicleDTO.getCategoryName();
        long instructorId = vehicleDTO.getInstructor().getId();

        vehicle = modelMaper.map(vehicleDTO, Vehicle.class);
        vehicle.setCategory(this.getCategory(categoryName));
        vehicle.setInstructor(this.getInstructor(instructorId));
    }

    @Transactional
    @Override
    public VehicleDTO edit(VehicleDTO vehicleDTO) {
        long id = vehicleDTO.getId();
        String registrationNumber = vehicleDTO.getRegistrationNumber();
        String brand = vehicleDTO.getBrand();
        String type = vehicleDTO.getType();
        Vehicle vehicle = vehicleRepository.findById(vehicleDTO.getId())
                            .orElseThrow(() -> new TrafficSchoolException(id +" - Vehicle  " + type + "  " + brand + ", registrationNumber = " + registrationNumber+ " does not exist."));

        setData(vehicle, vehicleDTO);
        vehicle.setId(id);
        vehicle = vehicleRepository.save(vehicle);
        vehicleDTO = modelMaper.map(vehicle, VehicleDTO.class);
        return vehicleDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        vehicleRepository.deleteById(id);
    }
}
