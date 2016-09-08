package com.bankdemo.model.user;

import com.bankdemo.model.BaseObject;

import javax.persistence.*;

/**
 * Created by Ilyas.Kuanyshbekov on 07.09.2016.
 */
@Entity
@Table(name = "user_roles")
public class UserRole extends BaseObject {

    @ManyToOne
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
