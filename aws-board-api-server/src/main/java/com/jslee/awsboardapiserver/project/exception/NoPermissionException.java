package com.jslee.awsboardapiserver.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NoPermissionException extends RuntimeException {
    @Getter
    final String information;
}
