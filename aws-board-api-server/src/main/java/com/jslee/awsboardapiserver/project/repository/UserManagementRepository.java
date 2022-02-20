package com.jslee.awsboardapiserver.project.repository;

import com.jslee.awsboardapiserver.project.domain.DUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<DUser, Long>, UserManagementRepositoryCustom {
}
