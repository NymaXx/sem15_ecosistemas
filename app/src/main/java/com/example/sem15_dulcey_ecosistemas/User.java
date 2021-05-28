package com.example.sem15_dulcey_ecosistemas;

public class User {

    public String id;
    public String name;
    public String email;
    public String tel;

    public User(){}

    public User(String id, String name, String tel, String email) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
