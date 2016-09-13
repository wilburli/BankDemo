package com.bankdemo.model.account;

import com.bankdemo.enums.CurrencyCode;
import com.bankdemo.model.BaseObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Entity
@Table(name = "accounts")
public class Account extends BaseObject {

    @Column(name = "currencyCode", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "balance", precision = 15, scale = 2)
    private Double balance;

    @Column(name = "iban", length = 20, nullable = false)
    private String iban;

    @Column(name = "begda")
    @Temporal(value = TemporalType.DATE)
    private Date begda;

    @Column(name = "endda")
    @Temporal(value = TemporalType.DATE)
    private Date endda;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @NotNull
    private Person person;

    public Account() {
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Date getBegda() {
        return begda;
    }

    public void setBegda(Date begda) {
        this.begda = begda;
    }

    public Date getEndda() {
        return endda;
    }

    public void setEndda(Date endda) {
        this.endda = endda;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Account{" +
                "currencyCode=" + currencyCode +
                ", balance=" + balance +
                ", iban='" + iban + '\'' +
                ", begda=" + begda +
                ", endda=" + endda +
                ", active=" + active +
                '}';
    }
}
