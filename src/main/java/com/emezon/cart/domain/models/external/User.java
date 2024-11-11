package com.emezon.cart.domain.models.external;

import java.time.LocalDate;

public class User {

    private String id;
    private String name;
    private String lastName;
    private String docNumber;
    private String cellphone;
    private LocalDate birthdate;
    private String email;
    private Role role;

    public User() {}

    public User(String id, String name, String lastName, String docNumber, String cellphone, LocalDate birthdate, String email, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.docNumber = docNumber;
        this.cellphone = cellphone;
        this.birthdate = birthdate;
        this.email = email;
        this.role = role;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
