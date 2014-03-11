package com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;

public class PublicationDate implements Serializable {

    private final String value;

    public PublicationDate(String value){

        this.value = value.replaceAll("-", "");
    }

    public String getValue() {

        return value;
    }

    public String getValueForView(){
        return "発売日 : " + value.substring(0, 4) + "/" + value.substring(4, 6) + "/" + value.substring(6);
    }

    public boolean verify(){

        DateTime publicationDate = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(value);
        DateTime nowDate = new DateTime();
        return publicationDate.isAfter(nowDate);

    }
}

