package com.jslee.awsboardapiserver.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    @Getter
    final String information;
}
