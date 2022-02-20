package com.jslee.awsboardapiserver.project.dto.UserManagement;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserManagementInsertDTO {
    /** user ID */
    private String userId;
    /** user password */
    private String userPwd;
    /** user Level */
    private String userLevel;
    /** user Name */
    private String userName;
    /** user Phone */
    private String userPhone;
    /** user Address */
    private String userAddress;
}
