package com.qna.habr;

import com.github.cliftonlabs.json_simple.JsonException;

import com.github.cliftonlabs.json_simple.Jsoner;
import com.google.gson.*;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Objects;

public class StarWarsTest {

    @Test
    public void FirstTest() throws InterruptedException, ProtocolException {

        //  String query = "access_token=" + URLEncoder.encode(AUTH_TOKEN, CHARSET);

        Boolean findPadmé = false;

        String Address="https://swapi.co/api/people/";

        while (findPadmé==false) {

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
            for (int i = 0; i < arr.length(); i++) {
                String name = arr.getJSONObject(i).getString("name");
                if (name.equals("Padmé Amidala")) {
                    findPadmé = true;
                }
            }

            if (findPadmé == false) {
                System.out.println("Padme not found");
                System.out.println(jsonObject.get("next").toString());
                Address = jsonObject.get("next").toString();

                if(jsonObject.get("next").toString()==null){
                    break;
                }
            }else {
                System.out.println("Padme found");
                break;
            }
        }
    }


}
