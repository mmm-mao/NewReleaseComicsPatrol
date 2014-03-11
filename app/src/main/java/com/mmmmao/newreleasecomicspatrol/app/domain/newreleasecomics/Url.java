package com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics;

import java.io.Serializable;

public class Url implements Serializable {

    private final String value;

    public Url(String value){
        this.value = value;
    }

    public String createHtmlLink(NewReleaseTitle newReleaseTitle) {

        return "<a href=" + value + ">" + newReleaseTitle.getValue() + "</a>";
    }

    public String getValue(){
        return value;
    }

}