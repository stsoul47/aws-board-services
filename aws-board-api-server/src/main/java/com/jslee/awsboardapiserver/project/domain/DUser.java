package com.jslee.awsboardapiserver.project.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "idx")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_management")
public class DUser {
    /** idx */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    /** userId */
    @Column(name = "user_id")
    private String userId;

    /** userPwd */
    @Column(name = "user_pwd")
    private String userPwd;

    /** userLevel */
    @Column(name = "user_level")
    private String userLevel;

    /** userName */
    @Column(name = "user_name")
    private String userName;

    /** userPhone */
    @Column(name = "user_phone")
    private String userPhone;

    /** userAddress */
    @Column(name = "user_address")
    private String userAddress;

    /** registTime */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "regist_time", nullable = false)
    private Date registTime;
}
