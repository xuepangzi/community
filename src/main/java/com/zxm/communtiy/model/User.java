package com.zxm.communtiy.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String accoundId;
    private String name;
    private String token;
    private Long tmgCreate;
    private Long tmgModified;
    private String avatarUrl;

}
