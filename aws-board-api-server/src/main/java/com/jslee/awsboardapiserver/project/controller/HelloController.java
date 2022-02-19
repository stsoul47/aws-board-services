package com.jslee.awsboardapiserver.project.controller;

import com.jslee.awsboardapiserver.project.common.MessageSourceImpl;
import com.jslee.awsboardapiserver.project.common.ResponseData;
import com.jslee.awsboardapiserver.project.common.ResponseDataType;
import com.jslee.awsboardapiserver.project.exception.BadValidationException;
import com.jslee.awsboardapiserver.project.exception.DuplicatedException;
import com.jslee.awsboardapiserver.project.exception.NoPermissionException;
import com.jslee.awsboardapiserver.project.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/api/hello")
public class HelloController {

    private final MessageSourceImpl messageSource;
    private final ModelMapper modelMapper;


    @Operation(summary = "test Hello", description = "hello api example")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public ResponseEntity<String> hello(@Parameter(description = "이름", required = true,example = "Lee") @RequestParam String name) {
        return ResponseEntity.ok("Hello " + name);
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
                .message(messageSource.get("",null, locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity DuplicatedExceptionHandler(DuplicatedException e, Locale locale) {
        log.error("HelloController Duplicated exception");
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("",null,locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(value = BadValidationException.class)
    public ResponseEntity BadValidationExceptionHandler(BadValidationException e, Locale locale) {
        log.error("HelloController Validation exception");
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("", null, locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(value = NoPermissionException.class)
    public ResponseEntity NoPermissionExceptionHandler(NoPermissionException e, Locale locale) {
        log.error("HelloController no permission exception");
        ResponseData responseData = ResponseData.builder()
                .type(ResponseDataType.FAILED)
                .message(messageSource.get("", null, locale))
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(responseData);
    }
}
