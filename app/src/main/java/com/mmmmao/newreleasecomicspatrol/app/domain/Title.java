package com.mmmmao.newreleasecomicspatrol.app.domain;

import java.io.Serializable;

public class Title implements Serializable {

    private final String value;

    public Title(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}