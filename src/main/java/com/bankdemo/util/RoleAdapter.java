package com.bankdemo.util;

import com.bankdemo.enums.Role;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public class RoleAdapter extends XmlAdapter<String, Role> {

    @Override
    public Role unmarshal(String v) throws Exception {
        return Role.valueOf(v);
    }

    @Override
    public String marshal(Role v) throws Exception {
        return v.toString();
    }
}
