package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class Url implements Serializable {

    private final String value;

    public Url(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}