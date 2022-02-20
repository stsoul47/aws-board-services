package com.jslee.awsboardapiserver.project.controller;

import com.jslee.awsboardapiserver.project.common.MessageSourceImpl;
import com.jslee.awsboardapiserver.project.common.ResponseData;
import com.jslee.awsboardapiserver.project.common.ResponseDataType;
import com.jslee.awsboardapiserver.project.domain.DUser;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementInsertDTO;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementQueryDTO;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementResponseDTO;
import com.jslee.awsboardapiserver.project.exception.BadValidationException;
import com.jslee.awsboardapiserver.project.exception.DuplicatedException;
import com.jslee.awsboardapiserver.project.exception.NoPermissionException;
import com.jslee.awsboardapiserver.project.exception.NotFoundException;
import com.jslee.awsboardapiserver.project.service.impl.UserManagementServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/user")
public class UserManagementController {
    private final MessageSourceImpl messageSource;
    private final ModelMapper modelMapper;
    private final UserManagementServiceImpl userManagementService;

    @Operation(summary = "user get List", description = "User List get")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<ResponseData> getUserManagementList(@ApiIgnore Locale locale, Pageable pageable,
                                                              @ModelAttribute UserManagementQueryDTO queryDTO,
                                                              @ApiIgnore BindingResult result){
        if(result.hasErrors()){
            throw new BadValidationException(result.getFieldError());
        }
        Page<DUser> userManagementPage = userManagementService.getUserManagementList(pageable, queryDTO);
        List<UserManagementResponseDTO> content = userManagementPage.getContent().stream()
                .map(user -> modelMapper.map(user, UserManagementResponseDTO.class)).collect(Collectors.toList());

        PageImpl<UserManagementResponseDTO> responseDTOS = new PageImpl<>(content, pageable, userManagementPage.getTotalElements());
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.SUCCESS)
                .message(messageSource.get("user.management.get.list.success", null, locale))
                .result(responseDTOS)
                .build();

        return ResponseEntity.ok().body(responseData);
    }

    @PostMapping()
    public ResponseEntity<ResponseData> insertUserManagement(@RequestBody UserManagementInsertDTO insertDTO, @ApiIgnore BindingResult result, @ApiIgnore Locale locale) {
        if(result.hasErrors()){
            throw new BadValidationException(result.getFieldError());
        }

        log.debug("[UserManagement] insertDTO INFO" + insertDTO.toString());
        DUser userManagement = userManagementService.insertUserManagement(insertDTO);
        UserManagementResponseDTO responseDTO = modelMapper.map(userManagement, UserManagementResponseDTO.class);

        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.SUCCESS)
                .result(responseDTO)
                .message(messageSource.get("user.management.create.success",null,locale))
                .build();
        return ResponseEntity.created(null).body(responseData);
    }



    /**
     * Exception Handler
     *
     * @author jslee
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity NotFoundExceptionHandler(NotFoundException e, Locale locale) {
        log.error("HelloController Not found Exception");

        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("user.management.not.found.fail",null, locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity DuplicatedExceptionHandler(DuplicatedException e, Locale locale) {
        log.error("HelloController Duplicated exception");
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("user.management.duplicated.fail",null,locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(value = BadValidationException.class)
    public ResponseEntity BadValidationExceptionHandler(BadValidationException e, Locale locale) {
        log.error("HelloController Validation exception");
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("user.management.bad.validation.fail", null, locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(value = NoPermissionException.class)
    public ResponseEntity NoPermissionExceptionHandler(NoPermissionException e, Locale locale) {
        log.error("HelloController no permission exception");
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("user.management.no.permission.fail", null, locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }
}
