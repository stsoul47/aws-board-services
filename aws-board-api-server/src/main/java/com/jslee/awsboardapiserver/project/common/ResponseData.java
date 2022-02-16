package com.jslee.awsboardapiserver.project.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String message;
    private ResponseDataType type;
    private Object result;
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date respDate = new Date();
}
