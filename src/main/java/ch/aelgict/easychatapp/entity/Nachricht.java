/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easychatapp.entity;

import ch.aelgict.easychatapp.model.APIHandler;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author da_so
 */
public class Nachricht {

    private User absender;
    private User ankommer;
    private String message;
    private Date absendeDatum;

    private static final SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.GERMAN);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.GERMAN);
    public Nachricht(String message, User absender, User ankommer, Date date) {
        if(date == null){
            date = new Date();
        }
        this.absendeDatum = date;
        this.absender = absender;
        this.ankommer = ankommer;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAbsender() {
        return absender;
    }

    public void setAbsender(User absender) {
        this.absender = absender;
    }

    public User getAnkommer() {
        return ankommer;
    }

    public void setAnkommer(User ankommer) {
        this.ankommer = ankommer;
    }

    public Date getAbsendeDatum() {
        return absendeDatum;
    }

    public void setAbsendeDatum(Date absendeDatum) {
        this.absendeDatum = absendeDatum;
    }

    public String createMessageJSON(){
        JSONObject object = new JSONObject();
        object.put("idUserIncomming", ankommer.getUserId());
        object.put("idUserOutgoing", absender.getUserId());
        object.put("textMessage", message);
        object.put("sendingTime", ISO8601DATEFORMAT.format(absendeDatum));
        return object.toString();
    }
    public static Nachricht fromJSON(String json) throws ParseException, java.text.ParseException, URISyntaxException, IOException, InterruptedException {
        JSONParser parser = new JSONParser();
        JSONObject elem = (JSONObject) parser.parse(json);
        APIHandler handler = APIHandler.getInstance();
        Date date = DATE_FORMAT.parse(elem.get("sendingTime").toString());
        User incomming = handler.getUserById(elem.get("idUserIncomming").toString());
        User outgoing = handler.getUserById(elem.get("idUserOutgoing").toString());
        return new Nachricht(elem.get("textMessage").toString(), outgoing, incomming, date);
    }
    public static ArrayList<Nachricht> fromJSONArray(String json) throws java.text.ParseException, URISyntaxException, IOException, InterruptedException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object jsonObject = jsonParser.parse(json);
        if(jsonObject instanceof JSONObject){
            System.out.println("There are no messages");
            return null;
        }
        JSONArray object = null;
        try{
            object = (JSONArray) jsonParser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Nachricht> messages = new ArrayList<>();
        for (int i = 0; i < object.size(); i++){
            JSONObject elem = (JSONObject) object.get(i);
            messages.add(fromJSON(String.valueOf(elem)));
        }
        return messages;
    }

    @Override
    public String toString(){
        return createMessageJSON();
    }
}
