package com.mmmmao.newreleasecomicspatrol.app.domain;

import java.io.Serializable;

public class Publisher implements Serializable {

    private final String value;

    public Publisher(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}