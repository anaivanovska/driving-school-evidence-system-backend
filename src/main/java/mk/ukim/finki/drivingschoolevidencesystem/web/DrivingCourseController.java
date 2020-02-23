package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.*;
import mk.ukim.finki.drivingschoolevidencesystem.service.DrivingCourseService;
import mk.ukim.finki.drivingschoolevidencesystem.service.MedicalCertificateService;
import mk.ukim.finki.drivingschoolevidencesystem.service.QualificationService;
import mk.ukim.finki.drivingschoolevidencesystem.service.TrialTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/drivingCourse")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class DrivingCourseController {

    @Autowired
    private DrivingCourseService drivingCourseService;
    @Autowired
    private MedicalCertificateService medicalCertificateService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private TrialTestService trialTestService;

    @PostMapping("/new/{userId}")
    public DrivingCourseOutputDTO createNew(@RequestBody DrivingCourseInputDTO drivingCourseInputDTO, @PathVariable long userId) {
        DrivingCourseOutputDTO drivingCourseOutputDTO = drivingCourseService.createNew(drivingCourseInputDTO.getOrdinalNumber(), drivingCourseInputDTO.getVehicleId(), drivingCourseInputDTO.getLecturerId(), userId);
        long drivingCourseId = drivingCourseOutputDTO.getId();
        MedicalCertificateDTO medicalCertificateDTO = medicalCertificateService.createNew(drivingCourseInputDTO.getMedicalCertificate(), drivingCourseId);
        drivingCourseOutputDTO.setMedicalCertificate(medicalCertificateDTO);
        List<QualificationDTO> qualifications = new ArrayList<>();
        for (QualificationDTO qualificationDTO : drivingCourseInputDTO.getQualifications()) {
            qualificationDTO = qualificationService.createNew(qualificationDTO, drivingCourseId);
            qualifications.add(qualificationDTO);
        }
        drivingCourseOutputDTO.setQualifications(qualifications);
        List<TrialTestDTO> trialTests = new ArrayList<>();
        for (TrialTestDTO trialTestDTO : drivingCourseInputDTO.getTrialTests()) {
            trialTestDTO = trialTestService.createNew(trialTestDTO, drivingCourseId);
            trialTests.add(trialTestDTO);
        }
        drivingCourseOutputDTO.setTrialTests(trialTests);
        return drivingCourseOutputDTO;
    }

    @PostMapping("/edit")
    public DrivingCourseOutputDTO edit(@RequestBody DrivingCourseInputDTO drivingCourseInputDTO) {
        DrivingCourseOutputDTO drivingCourseOutputDTO = drivingCourseService.edit(drivingCourseInputDTO.getId(), drivingCourseInputDTO.getOrdinalNumber(), drivingCourseInputDTO.getVehicleId(), drivingCourseInputDTO.getLecturerId());
        MedicalCertificateDTO medicalCertificateDTO = medicalCertificateService.edit(drivingCourseInputDTO.getMedicalCertificate());
        drivingCourseOutputDTO.setMedicalCertificate(medicalCertificateDTO);
        List<QualificationDTO> qualifications = new ArrayList<>();
        for (QualificationDTO qualificationDTO : drivingCourseInputDTO.getQualifications()) {
            qualificationDTO = qualificationService.edit(qualificationDTO);            qualifications.add(qualificationDTO);
        }
        drivingCourseOutputDTO.setQualifications(qualifications);
        List<TrialTestDTO> trialTests = new ArrayList<>();
        for (TrialTestDTO trialTestDTO : drivingCourseInputDTO.getTrialTests()) {
            trialTestDTO = trialTestService.edit(trialTestDTO);
            trialTests.add(trialTestDTO);
        }
        drivingCourseOutputDTO.setTrialTests(trialTests);
        return drivingCourseOutputDTO;
    }

    @GetMapping("/all/{userId}")
    public Page<DrivingCourseOutputDTO> getAllCoursesForUser(@PathVariable long userId, @PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        Page<DrivingCourseOutputDTO> courses =  drivingCourseService.getAllForUser(userId, pageable);
        setData(courses);
        return courses;

    }

    @GetMapping("/all")
    public Page<DrivingCourseOutputDTO> getAllCoursesForUser(@PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        Page<DrivingCourseOutputDTO> courses =  drivingCourseService.getAll(pageable);
        setData(courses);
        return courses;
    }

    @DeleteMapping("/remove/{drivingCourseId}")
    public long remove(@PathVariable long drivingCourseId) {
        drivingCourseService.remove(drivingCourseId);
        return drivingCourseId;
    }


    private void setData(Page<DrivingCourseOutputDTO> courses) {
        courses.map(course -> {
            long courseId = course.getId();MedicalCertificateDTO medicalCertificateDTO = medicalCertificateService.getMedicalCertificateForDrivingCourse(courseId);
            List<QualificationDTO> qualificationDTOS = qualificationService.getAllQualificationsForDrivingCourse(courseId);
            List<TrialTestDTO> trialTestDTOS =  trialTestService.getAllTrialTestsForDrivingCourse(courseId);
            course.setMedicalCertificate(medicalCertificateDTO);
            course.setQualifications(qualificationDTOS);
            course.setTrialTests(trialTestDTOS);
            return course;
        });
    }
}
