package com.bankdemo.rest_client;

import com.bankdemo.enums.Gender;

import java.util.Date;

/**
 * Created by Ilyas.Kuanyshbekov on 20.09.2016.
 */
public class PersonDTO {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private Gender gender;

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                '}';
    }
}
