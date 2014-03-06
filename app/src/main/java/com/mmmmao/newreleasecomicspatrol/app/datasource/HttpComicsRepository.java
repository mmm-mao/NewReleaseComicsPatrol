package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.util.Log;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Amount;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Author;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Isbn;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PublicationDate;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Publisher;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Title;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Url;
import com.mmmmao.newreleasecomicspatrol.app.library.RequestAmazon;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

public class HttpComicsRepository {

    public PatrolComics searchComicsByTitleAndKeyword(String... params) {

        Map<String, String> addSearchData = new HashMap<String, String>();
        addSearchData.put("Title", params[0]);
        addSearchData.put("Keywords", params[1]);

        RequestAmazon requestAmazon = new RequestAmazon();
        return createPatrolComics(requestAmazon.httpToXml(addSearchData), params[0]);

    }

    private PatrolComics createPatrolComics(XmlPullParser xmlPullParser, String inputTitle) {

        int eventType;
        boolean itemFlag = false;
        boolean bindingFlag = false;
        Title title = null;
        Author author = null;
        Amount amount = null;
        Isbn isbn = null;
        PublicationDate publicationDate = null;
        Publisher publisher = null;
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

                            if (tag.equals("Title")) {
                                title = new Title(inputTitle);
                            } else if (tag.equals("Author")) {
                                author = new Author(xmlPullParser.nextText());
                                System.out.println(author.getValue());
                            } else if (tag.equals("Amount")) {
                                amount = new Amount(Integer.parseInt(xmlPullParser.nextText()));
                                System.out.println(amount.getValue());
                            } else if (tag.equals("PublicationDate")) {
                                publicationDate = new PublicationDate(xmlPullParser.nextText());
                                System.out.println(publicationDate.getValue());
                            } else if (tag.equals("ISBN")) {
                                isbn = new Isbn(xmlPullParser.nextText());
                                System.out.println(isbn.getValue());
                            } else if (tag.equals("Publisher")) {
                                publisher = new Publisher(xmlPullParser.nextText());
                                System.out.println(publisher.getValue());
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
                                return new PatrolComics(null, title, author, publisher);
                            }

                            itemFlag = false;
                            bindingFlag = false;
                            title = null;
                            author = null;
                            amount = null;
                            isbn = null;
                            publicationDate = null;
                            publisher = null;
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

