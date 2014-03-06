package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class Author implements Serializable {

    private final String value;

    public Author(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
