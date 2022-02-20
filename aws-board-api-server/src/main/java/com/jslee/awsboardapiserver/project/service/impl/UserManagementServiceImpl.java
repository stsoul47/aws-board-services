package com.jslee.awsboardapiserver.project.service.impl;

import com.jslee.awsboardapiserver.project.domain.DUser;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementInsertDTO;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementQueryDTO;
import com.jslee.awsboardapiserver.project.repository.UserManagementRepository;
import com.jslee.awsboardapiserver.project.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserManagementRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Page<DUser> getUserManagementList(Pageable pageable, UserManagementQueryDTO queryDTO) {
        return repository.findAll(pageable, queryDTO);
    }

    @Override
    public DUser insertUserManagement(UserManagementInsertDTO insertDTO){
        DUser insert = modelMapper.map(insertDTO, DUser.class);
        return repository.save(insert);
    }

}
