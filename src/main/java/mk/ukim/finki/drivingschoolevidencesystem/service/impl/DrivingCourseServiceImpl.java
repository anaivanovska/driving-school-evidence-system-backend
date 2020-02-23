package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.*;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.VehicleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.DrivingCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("drivingCourseService")
public class DrivingCourseServiceImpl implements DrivingCourseService {
    @Autowired
    private DrivingCourseRepository drivingCourseRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public DrivingCourseOutputDTO createNew(String ordinalNumber, long vehicleId, long lecturerId, long candidateId) {
        DrivingCourse drivingCourse = drivingCourseRepository.findByOrdinalNumber(ordinalNumber);

        if(drivingCourse != null) {
            throw new TrafficSchoolException("Driving course with ordinal number " + ordinalNumber + " exists");
        }
        drivingCourse = new DrivingCourse();
        setDrivingCourse(drivingCourse, ordinalNumber, vehicleId, lecturerId);
        drivingCourse.setCandidate(getUserById(candidateId));
        drivingCourse = drivingCourseRepository.save(drivingCourse);
        return modelMapper.map(drivingCourse, DrivingCourseOutputDTO.class);
    }

    private void setDrivingCourse(DrivingCourse drivingCourse, String ordinalNumber, long vehicleId, long lecturerId) {
        drivingCourse.setOrdinalNumber(ordinalNumber);
        drivingCourse.setLecturer(getUserById(lecturerId));
        drivingCourse.setVehicle(getVechileById(vehicleId));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected User getUserById(long id) {
        return userRepository.findById(id)
                            .orElseThrow(() -> new TrafficSchoolException("User with id " + id + " not found"));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected Vehicle getVechileById(long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Vehicle with id " + id + " not found"));
    }

    @Transactional
    @Override
    public DrivingCourseOutputDTO edit(long id, String ordinalNumber, long vehicleId, long lecturerId) {
        DrivingCourse drivingCourse = drivingCourseRepository.findById(id)
                                                .orElseThrow(() -> new TrafficSchoolException("Driving course with id " + id +" does not exist"));
        setDrivingCourse(drivingCourse, ordinalNumber, vehicleId, lecturerId);
        drivingCourse = drivingCourseRepository.save(drivingCourse);
        DrivingCourseOutputDTO drivingCourseOutputDTO = modelMapper.map(drivingCourse, DrivingCourseOutputDTO.class);
        return drivingCourseOutputDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        drivingCourseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<DrivingCourseOutputDTO> getAllForUser(long userId, Pageable pageable) {
        return drivingCourseRepository.findAllByCandidate_Id(userId, pageable)
                        .map(drivingCourse -> modelMapper.map(drivingCourse, DrivingCourseOutputDTO.class));
    }

    @Transactional
    @Override
    public Page<DrivingCourseOutputDTO> getAll(Pageable pageable) {
        return drivingCourseRepository.findAll(pageable)
                .map(drivingCourse -> modelMapper.map(drivingCourse, DrivingCourseOutputDTO.class));
    }
}
