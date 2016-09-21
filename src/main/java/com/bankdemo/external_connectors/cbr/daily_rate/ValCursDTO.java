package com.bankdemo.external_connectors.cbr.daily_rate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 21.09.2016.
 */
@XmlRootElement(name = "ValCurs")
public class ValCursDTO {

    @XmlAttribute(name = "Date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "Valute")
    private List<ValuteDTO> valuteDTOList;


    public ValCursDTO() {
    }

    public ValCursDTO(Date date, String name, List<ValuteDTO> valuteDTOList) {
        this.date = date;
        this.name = name;
        this.valuteDTOList = valuteDTOList;
    }

    @Override
    public String toString() {
        return "ValCursDTO{" +
                "date=" + date +
                ", name='" + name + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<ValuteDTO> getValuteDTOList() {
        return valuteDTOList;
    }
}
