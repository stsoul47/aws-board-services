package com.jslee.awsboardapiserver.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BadValidationException extends RuntimeException {
    @Getter
    final Object information;
}
