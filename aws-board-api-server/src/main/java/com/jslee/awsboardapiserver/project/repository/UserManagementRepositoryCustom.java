package com.jslee.awsboardapiserver.project.repository;

import com.jslee.awsboardapiserver.project.domain.DUser;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManagementRepositoryCustom {
    public Page<DUser> findAll(Pageable pageable, UserManagementQueryDTO queryDTO);
}
