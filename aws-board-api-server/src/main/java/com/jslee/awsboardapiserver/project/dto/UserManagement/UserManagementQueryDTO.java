package com.jslee.awsboardapiserver.project.dto.UserManagement;

import lombok.*;
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserManagementQueryDTO {
    private String userId;
    private String userName;
    private String userPhone;
    private String userAddress;
}
