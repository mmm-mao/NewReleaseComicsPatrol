package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;

import java.util.List;

public class ComicsList {

    private final List<PatrolComics> list;

    public ComicsList(List<PatrolComics> list){
        this.list = list;
    }

    public List<PatrolComics> getList(){
        return list;
    }

    public int getComicsIdByDesignationComics(int position){
        PatrolComics patrolComics =  list.get(position);
        return patrolComics.getPatrolComicsId();
    }

    public PatrolComics getPatrolComicsByDesignationComics(int position) {
        return list.get(position);
    }

    public void remove(int position){
        list.remove(position);
    }

}
