package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class PatrolTitle implements Serializable {

    private final String value;

    public PatrolTitle(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}