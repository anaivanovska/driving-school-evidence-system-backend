package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCategory;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.VehicleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMaper;
    @Autowired
    private UserCategoryRepository userCategoryRepository;
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
        Category category = categoryRepository.findByName(categoryName);
        if(category == null) {
            throw new TrafficSchoolException("Invalid category name " + categoryName + ". Category does not exist.");
        }
        return category;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User findInstructor(long id) {
        List<UserCategory> userCategoryList = userCategoryRepository.findAllByUser_IdAndRole(id, Constants.Role.INSTRUCTOR.name());

        if (userCategoryList.size() == 0) {
            throw new TrafficSchoolException("User with id " + id + " not found");
        }
        return userCategoryList.get(0).getUser();
    }

    private void setData(Vehicle vehicle, VehicleDTO vehicleDTO) {
        String categoryName = vehicleDTO.getCategoryName();
        long instructorId = vehicleDTO.getInstructorId();
        vehicle = modelMaper.map(vehicleDTO, Vehicle.class);
        vehicle.setCategory(this.getCategory(categoryName));
        vehicle.setInstructor(this.findInstructor(instructorId));
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
