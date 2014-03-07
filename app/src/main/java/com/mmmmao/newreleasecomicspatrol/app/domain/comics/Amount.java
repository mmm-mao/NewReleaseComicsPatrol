package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class Amount implements Serializable {

    private final int value;

    public Amount(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean verify(){

        if(value > 1000){
            return false;
        }

        return true;
    }

}
