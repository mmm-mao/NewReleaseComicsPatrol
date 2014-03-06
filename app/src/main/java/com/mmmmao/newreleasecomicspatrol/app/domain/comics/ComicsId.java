package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class ComicsId {

    private final Integer value;

    public ComicsId(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}