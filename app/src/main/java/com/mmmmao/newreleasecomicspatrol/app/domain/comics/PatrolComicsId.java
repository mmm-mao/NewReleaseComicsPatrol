package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class PatrolComicsId {

    private final Integer value;

    public PatrolComicsId(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}