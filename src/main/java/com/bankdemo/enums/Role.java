package com.bankdemo.enums;

/**
 * Created by Ilyas.Kuanyshbekov on 07.09.2016.
 */
public enum Role {

    ADMIN(1),
    USER(2);

    private final int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Role fromCode(int code) {
        switch(code) {
            case 1: return ADMIN;
            case 2: return USER;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch(this) {
            case ADMIN: return "Admin";
            case USER: return "User";
            default:
                return "<>";
        }
    }

}
