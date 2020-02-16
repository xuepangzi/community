package com.zxm.communtiy.model;

public class User {
    private int id;
    private String accoundId;
    private String name;
    private String token;
    private Long tmgCreate;
    private Long tmgModified;

    public User() {
    }
    public User(int id, String accoundId, String name, String token, Long tmgCreate, Long tmgModified) {
        this.id = id;
        this.accoundId = accoundId;
        this.name = name;
        this.token = token;
        this.tmgCreate = tmgCreate;
        this.tmgModified = tmgModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccoundId() {
        return accoundId;
    }

    public void setAccoundId(String accoundId) {
        this.accoundId = accoundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTmgCreate() {
        return tmgCreate;
    }

    public void setTmgCreate(Long tmgCreate) {
        this.tmgCreate = tmgCreate;
    }

    public Long getTmgModified() {
        return tmgModified;
    }

    public void setTmgModified(Long tmgModified) {
        this.tmgModified = tmgModified;
    }
}
