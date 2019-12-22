package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseOutputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseInputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCategory;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.VehicleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.DrivingCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserCategoryRepository userCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public DrivingCourseOutputDTO createNew(DrivingCourseInputDTO drivingCourseInputDTO, long candidateId) {
        String ordinalNumber = drivingCourseInputDTO.getOrdinalNumber();
        DrivingCourse drivingCourse = drivingCourseRepository.findByOrdinalNumber(ordinalNumber);

        if(drivingCourse != null) {
            throw new TrafficSchoolException("Driving course with ordinal number " + ordinalNumber + " exists");
        }

        setDrivingCourse(drivingCourse, drivingCourseInputDTO);
        drivingCourse.setCandidate(getUserByIdAndRole(candidateId, Constants.Role.CANDIDATE.name()));
        drivingCourse = drivingCourseRepository.save(drivingCourse);
        DrivingCourseOutputDTO drivingCourseOutputDTO = modelMapper.map(drivingCourse, DrivingCourseOutputDTO.class);
        return drivingCourseOutputDTO;
    }

    private void setDrivingCourse(DrivingCourse drivingCourse, DrivingCourseInputDTO drivingCourseInputDTO) {
        drivingCourse.setOrdinalNumber(drivingCourseInputDTO.getOrdinalNumber());
        drivingCourse.setLecturer(getUserByIdAndRole(drivingCourseInputDTO.getLecturerId(), Constants.Role.INSTRUCTOR.name()));
        drivingCourse.setVehicle(getVechileById(drivingCourseInputDTO.getVehicleId()));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User getUserByIdAndRole(long id, String roleName) {
        List<UserCategory> userCategoryList = userCategoryRepository.findAllByUser_IdAndRole(id, roleName);

        if (userCategoryList.size() == 0 ) {
            throw new TrafficSchoolException("Candidate with id " + id + " not found");
        }

        return userCategoryList.get(0).getUser();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Vehicle getVechileById(long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Vehicle with id " + id + " not found"));
    }

    @Transactional
    @Override
    public DrivingCourseOutputDTO edit(DrivingCourseInputDTO drivingCourseInputDTO) {
        long id = drivingCourseInputDTO.getId();
        DrivingCourse drivingCourse = drivingCourseRepository.findById(id)
                                                .orElseThrow(() -> new TrafficSchoolException("Driving course with id " + id +" does not exist"));
        setDrivingCourse(drivingCourse, drivingCourseInputDTO);
        drivingCourse = drivingCourseRepository.save(drivingCourse);
        DrivingCourseOutputDTO drivingCourseOutputDTO = modelMapper.map(drivingCourse, DrivingCourseOutputDTO.class);
        return drivingCourseOutputDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        drivingCourseRepository.deleteById(id);
    }
}
