package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.util.Log;

import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.Isbn;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseTitle;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.PublicationDate;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolTitle;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.Url;

import org.xmlpull.v1.XmlPullParser;

class NewReleaseComicsMapper {

    private final XmlPullParser xmlPullParser;

    public NewReleaseComicsMapper(XmlPullParser xmlPullParser){
        this.xmlPullParser = xmlPullParser;
    }



    public NewReleaseComics create() {

        int eventType;
        boolean itemFlag = false;
        boolean bindingFlag = false;
        NewReleaseTitle newReleaseTitle = null;
        Isbn isbn = null;
        PublicationDate publicationDate = null;
        Url url = null;
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

                            if(tag.equals("Title")){
                                newReleaseTitle = new NewReleaseTitle(xmlPullParser.nextText());
                            } else if (tag.equals("PublicationDate")) {
                                publicationDate = new PublicationDate(xmlPullParser.nextText());
                                System.out.println(publicationDate.getValue());
                            } else if (tag.equals("ISBN")) {
                                isbn = new Isbn(xmlPullParser.nextText());
                                System.out.println(isbn.getValue());
                            } else if (tag.equals("DetailPageURL")) {
                                url = new Url(xmlPullParser.nextText());
                                System.out.println(url.getValue());
                            }

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();
                        if (tag.equals("Item")) {
                            if (bindingFlag == true) {
                                return new NewReleaseComics(null, newReleaseTitle, publicationDate, isbn, url);
                            }

                            itemFlag = false;
                            bindingFlag = false;
                            newReleaseTitle = null;
                            isbn = null;
                            publicationDate = null;
                            url = null;
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
