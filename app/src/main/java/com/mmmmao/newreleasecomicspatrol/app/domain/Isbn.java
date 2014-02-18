package com.mmmmao.newreleasecomicspatrol.app.domain;

import java.io.Serializable;

public class Isbn implements Serializable {

    private final String value;

    public Isbn(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
