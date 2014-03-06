package com.mmmmao.newreleasecomicspatrol.app.domain.comicslist;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ComicsList {

    private final List<PatrolComics> list;

    public ComicsList(List<PatrolComics> list){
        this.list = list;
    }

    public List<PatrolComics> getList(){
        return this.list;
    }

    public int getComicsIdByDesignationComics(int position){
        PatrolComics patrolComics =  list.get(position);
        return patrolComics.getComicsId();
    }


}
