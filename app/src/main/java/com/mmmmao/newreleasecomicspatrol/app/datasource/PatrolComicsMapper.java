package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.util.Log;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Author;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Publisher;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolTitle;

import org.xmlpull.v1.XmlPullParser;

class PatrolComicsMapper {

    private final XmlPullParser xmlPullParser;
    private final PatrolTitle patrolTitle;

    public PatrolComicsMapper(XmlPullParser xmlPullParser, PatrolTitle patrolTitle){
        this.xmlPullParser = xmlPullParser;
        this.patrolTitle = patrolTitle;
    }



    public PatrolComics create() {

        int eventType;
        boolean itemFlag = false;
        boolean bindingFlag = false;
        Author author = null;
        Publisher publisher = null;
        try {
            while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
                String tag = null;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();
                        if (tag.equals("Item")) {
                            itemFlag = true;
                        }

                        if (tag.equals("Binding") && xmlPullParser.nextText().equals("コミック")) {
                            bindingFlag = true;
                        }

                        if (itemFlag == true) {

                            if (tag.equals("Author")) {
                                author = new Author(xmlPullParser.nextText());
                                System.out.println(author.getValue());
                            } else if (tag.equals("Publisher")) {
                                publisher = new Publisher(xmlPullParser.nextText());
                                System.out.println(publisher.getValue());
                            }

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();
                        if (tag.equals("Item")) {
                            if (bindingFlag == true) {
                                return new PatrolComics(null, patrolTitle, author, publisher);
                            }

                            itemFlag = false;
                            bindingFlag = false;
                            author = null;
                            publisher = null;
                        }
                        break;
                }

            }
        } catch (Exception e) {
            Log.d("XmlPullParserSampleUrl", e.getMessage());
        }

        return null;

    }
}
