package mk.ukim.finki.trafficschoolevidencesystem.service.impl;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.InstructorDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Instructor;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.trafficschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.trafficschoolevidencesystem.repository.InstructorRepository;
import mk.ukim.finki.trafficschoolevidencesystem.repository.VehicleRepository;
import mk.ukim.finki.trafficschoolevidencesystem.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMaper;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public VehicleDTO createNew(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findByTypeAndBrandAndRegistrationNumber(vehicleDTO.getType(),
                                                                                    vehicleDTO.getBrand(),
                                                                                    vehicleDTO.getRegistrationNumber());
        if(vehicle != null) {
            throw new TrafficSchoolException("Vehicle with type = " + vehicleDTO.getType() + ", brand = " + vehicleDTO.getBrand() + ", registrationNumber = " + vehicleDTO.getRegistrationNumber() + " already exists.");
        }
        vehicle = save(vehicleDTO);
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
    public Instructor getInstructor(long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Instructor with id = " + id + " does not exist"));

        return instructor;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Vehicle save(VehicleDTO vehicleDTO) {
        String categoryName = vehicleDTO.getCategoryName();
        long instructorId = vehicleDTO.getInstructor().getId();

        Vehicle vehicle = modelMaper.map(vehicleDTO, Vehicle.class);
        vehicle.setCategory(this.getCategory(categoryName));
        vehicle.setInstructor(this.getInstructor(instructorId));
        vehicle = vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Transactional
    @Override
    public VehicleDTO edit(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findByTypeAndBrandAndRegistrationNumber(vehicleDTO.getType(),
                                                                                    vehicleDTO.getBrand(),
                                                                                    vehicleDTO.getRegistrationNumber());
        if(vehicle == null) {
            throw new TrafficSchoolException("Vehicle with type = " + vehicleDTO.getType() + ", brand = " + vehicleDTO.getBrand() + ", registrationNumber = " + vehicleDTO.getRegistrationNumber() + " does not exist.");
        }

        vehicle = save(vehicleDTO);
        vehicleDTO = modelMaper.map(vehicle, VehicleDTO.class);
        return vehicleDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        vehicleRepository.deleteById(id);
    }
}
