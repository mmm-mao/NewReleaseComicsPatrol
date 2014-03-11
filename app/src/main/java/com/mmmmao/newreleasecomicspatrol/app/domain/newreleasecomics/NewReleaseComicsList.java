package com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics;

import java.util.List;

public class NewReleaseComicsList {

    private final List<NewReleaseComics> list;

    public NewReleaseComicsList(final List<NewReleaseComics> list){
        this.list = list;
    }

    public List<NewReleaseComics> getList(){
        return list;
    }
}
