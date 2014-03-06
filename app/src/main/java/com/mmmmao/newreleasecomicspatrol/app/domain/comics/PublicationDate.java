package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class PublicationDate implements Serializable {

    private final String value;

    public PublicationDate(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

