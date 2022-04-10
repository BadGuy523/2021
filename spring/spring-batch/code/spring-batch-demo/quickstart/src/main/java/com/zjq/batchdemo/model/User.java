package com.zjq.batchdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String phone;
    private String title;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
    private LocalDateTime sysCreateTime;
    private String sysCreateUser;
    private LocalDateTime sysUpdateTime;
    private String sysUpdateUser;
}
