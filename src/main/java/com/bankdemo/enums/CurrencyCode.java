package com.bankdemo.enums;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public enum CurrencyCode {

    EUR(1),
    USD(2),
    KZT(3),
    RUB(4);

    private final int code;

    CurrencyCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        switch(this) {
            case EUR: return "EUR";
            case USD: return "USD";
            case KZT: return "KZT";
            case RUB: return "RUB";
            default:
                return "<>";
        }
    }

}
