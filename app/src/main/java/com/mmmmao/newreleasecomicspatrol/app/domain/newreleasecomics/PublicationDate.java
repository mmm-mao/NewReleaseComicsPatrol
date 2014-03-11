package com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;

public class PublicationDate implements Serializable {

    private final String value;

    public PublicationDate(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean verify(){

        DateTime publicationDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(value);
        DateTime nowDate = new DateTime();

        Log.d("発売日", publicationDate.toString());
        return publicationDate.isAfter(nowDate);
    }
}

