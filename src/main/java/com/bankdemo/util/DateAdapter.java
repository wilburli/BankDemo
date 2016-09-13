package com.bankdemo.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public Date unmarshal(String v) throws Exception {
        return sdf.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return sdf.format(v);
    }
}
