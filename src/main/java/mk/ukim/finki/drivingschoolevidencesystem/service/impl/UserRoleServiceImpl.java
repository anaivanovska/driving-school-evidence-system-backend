package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    @Override
    public boolean checkExistenceOfUserWithEmailAndRole(String email, String role) {
        return userRoleRepository.findByUser_EmailAndRole_Name(email, role) != null ? true : false;
    }
}
