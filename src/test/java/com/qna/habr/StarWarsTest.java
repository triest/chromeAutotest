package com.qna.habr;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

import static org.apache.commons.lang3.ArrayUtils.add;

public class StarWarsTest {

    @Test
    public void FirstTest() throws InterruptedException, ProtocolException {
        System.out.println(FindHeroUrl());
        String heroUrl = FindHeroUrl();
        if (heroUrl == null) {
            Assert.fail("Hero url Not Found");
        }

        ArrayList<String>  url= shackSteamshipArray(heroUrl);

    }

    public ArrayList<String> shackSteamshipArray(String Url) {

        URL url = null;
        try {
            url = new URL(Url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setRequestProperty("Accept", "application/json");
        int status = 0;
        try {
            status = con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine = null;
        StringBuffer content = new StringBuffer();
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            content.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = content.toString();
        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray arr = jsonObject.getJSONArray("starships");
        System.out.println(arr);
        String[] name = new String[]{};
             ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            //list.add(arr2.getJSONObject(i).getString("name"));
            System.out.println(arr.getJSONObject(i).getString("name"));
            list.add(arr.getJSONObject(i).getString("name"));
        }

        return list;

    }


    public String FindHeroUrl() throws InterruptedException, ProtocolException {

        //  String query = "access_token=" + URLEncoder.encode(AUTH_TOKEN, CHARSET);

        Boolean findPadmé = false;

        String Address = "https://swapi.co/api/people/";
        int count=0;
        while (findPadmé == false || count<20) {
            count++;
            URL url = null;
            try {
                url = new URL(Address);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;

            try {
                con = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                con.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            con.setRequestProperty("Accept", "application/json");
            int status = 0;
            try {
                status = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader in = null;
            try {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputLine = null;
            StringBuffer content = new StringBuffer();
            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                content.append(inputLine);
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String responseString = content.toString();
            JSONObject jsonObject = new JSONObject(responseString);
            JSONArray arr = jsonObject.getJSONArray("results");
            int Key = 0;
            for (int i = 0; i < arr.length(); i++) {
                String name = arr.getJSONObject(i).getString("name");
                if (name.equals("Padmé Amidala")) {
                    findPadmé = true;
                    Key = i;
                }
            }

            if (findPadmé == false) {
                Address = jsonObject.get("next").toString();

                if (jsonObject.get("next").toString() == null) {
                    break;
                }
            } else {
                System.out.println("Padme found");
                String urlHero = arr.getJSONObject(Key).getString("url");
                return urlHero;
            }
        }
        return null;
    }


}
