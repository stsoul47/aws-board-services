package com.jslee.awsboardapiserver.project.service;

import com.jslee.awsboardapiserver.project.domain.DUser;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementInsertDTO;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManagementService {
    Page<DUser> getUserManagementList(Pageable pageable, UserManagementQueryDTO queryDTO);

    DUser insertUserManagement(UserManagementInsertDTO insertDTO);
}
