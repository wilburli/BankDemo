package com.bankdemo.external_connectors.cbr.daily_rate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Ilyas.Kuanyshbekov on 21.09.2016.
 */
public class ValueAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) throws Exception {
        String s = v.replaceAll(",",".");
        return Double.parseDouble(s);
    }

    @Override
    public String marshal(Double v) throws Exception {
        return String.valueOf(v);
    }
}
