package com.mert.becomealegend.Profiles;

/**
 * Created by EsrefTurkok on 16.02.2018.
 */

public class calisan {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String profession;
    private String location;
    private String designation;

    public calisan(){}


    public calisan(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getProfession() { return profession; }

    public void setProfession(String profession) { this.profession = profession; }

    public String getLocation() {  return location; }

    public void setLocation(String location) { this.location = location; }

    public String getDesignation() { return designation; }

    public void setDesignation(String designation) { this.designation = designation; }



}
