package com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics;

import java.io.Serializable;

public class NewReleaseTitle implements Serializable {

    private final String value;

    public NewReleaseTitle(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}