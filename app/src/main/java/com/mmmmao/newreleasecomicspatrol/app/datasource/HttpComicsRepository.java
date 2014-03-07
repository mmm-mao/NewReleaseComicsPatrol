package com.mmmmao.newreleasecomicspatrol.app.datasource;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Author;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Publisher;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Title;
import com.mmmmao.newreleasecomicspatrol.app.library.RequestAmazon;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

public class HttpComicsRepository {

    public PatrolComics searchPatrolComics(String... params) {

        Map<String, String> addSearchData = new HashMap<String, String>();
        addSearchData.put("Title", params[0]);
        addSearchData.put("Keywords", params[1]);

        RequestAmazon requestAmazon = new RequestAmazon();
        XmlPullParser xmlPullParser = requestAmazon.httpToXml(addSearchData);

        PatrolComicsMapper patrolComicsMapper = new PatrolComicsMapper(xmlPullParser, new Title(params[0]));
        return patrolComicsMapper.create();

    }

    public NewReleaseComics searchNewReleaseComics(PatrolComics patrolComics) {

        Map<String, String> addSearchData = new HashMap<String, String>();
        addSearchData.put("Title", patrolComics.getTitle().getValue());
        addSearchData.put("Author", patrolComics.getAuthor().getValue());
        addSearchData.put("Publisher", patrolComics.getPublisher().getValue());
        addSearchData.put("Sort", "daterank");
        addSearchData.put("MaximumPrice", "800");

        RequestAmazon requestAmazon = new RequestAmazon();
        XmlPullParser xmlPullParser = requestAmazon.httpToXml(addSearchData);

        NewReleaseComicsMapper newReleaseComicsMapper = new NewReleaseComicsMapper(xmlPullParser);
        return newReleaseComicsMapper.create();

    }

}

