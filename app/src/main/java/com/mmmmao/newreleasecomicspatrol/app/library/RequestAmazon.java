package com.mmmmao.newreleasecomicspatrol.app.library;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class RequestAmazon {

    private static final String AWS_ACCESS_KEY_ID = "AKIAIS47N5GCUEFY6YSA";

    private static final String AWS_SECRET_KEY = "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";

    private static final String ENDPOINT = "ecs.amazonaws.jp";


    public XmlPullParser httpToXml(Map<String, String> addSearchData){

        try {

            SignedRequestsHelper helper;
            try {
                helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            Log.d("XmlPullParserSampleUrl", "Error");

			/*
			 * Here is an example in map form, where the request parameters are stored in a map.
			 */
            Map<String, String> searchData = new HashMap<String, String>();
            searchData.put("Service", "AWSECommerceService");
            searchData.put("AssociateTag", "mmmmao-22");
            searchData.put("Operation", "ItemSearch");
            searchData.put("SearchIndex", "Books");
            searchData.put("BrowseNode", "2278488051");
            searchData.put("Version", "2011-08-02");
            searchData.put("ResponseGroup", "ItemAttributes");
            searchData.put("Sort", "daterank");
            searchData.putAll(addSearchData);


            String requestUrl = helper.sign(searchData);
            System.out.println("Signed Request is \"" + requestUrl + "\"");

            XmlPullParser xmlPullParser = Xml.newPullParser();

            URL url = new URL(requestUrl);
            URLConnection connection = url.openConnection();
            xmlPullParser.setInput(connection.getInputStream(), "UTF-8");

            return xmlPullParser;

        } catch (Exception e) {
            Log.d("XmlPullParserSampleUrl", "Error");
        }

        return null;

    }

}

