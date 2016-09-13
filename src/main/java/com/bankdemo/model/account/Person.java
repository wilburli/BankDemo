package com.bankdemo.model.account;

import com.bankdemo.enums.Gender;
import com.bankdemo.model.BaseObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Entity
@Table(name = "persons")
public class Person extends BaseObject {

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "individualpersonalnumber", length = 20, nullable = false)
    private String individualPersonalNumber;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIndividualPersonalNumber() {
        return individualPersonalNumber;
    }

    public void setIndividualPersonalNumber(String individualPersonalNumber) {
        this.individualPersonalNumber = individualPersonalNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
