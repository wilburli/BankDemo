package com.bankdemo.util;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 * Auto registration endpoint beans for soap ws
 */
@Target({ElementType.FIELD , ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface CxfAutoRegistrationEndpointBean {
}
