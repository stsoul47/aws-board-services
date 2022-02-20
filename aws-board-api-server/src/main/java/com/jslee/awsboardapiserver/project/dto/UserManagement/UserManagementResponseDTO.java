package com.jslee.awsboardapiserver.project.dto.UserManagement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementResponseDTO {
    /** idx */
    private Long idx;
    /** userId */
    private String userId;
    /** userPwd */
    private String userPwd;
    /** userLevel */
    private String userLevel;
    /** userName */
    private String userName;
    /** userPhone */
    private String userPhone;
    /** userAddress */
    private String userAddress;

    /** registTime */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")
    private Date registTime;
}
