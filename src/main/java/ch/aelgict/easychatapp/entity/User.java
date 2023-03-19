/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easychatapp.entity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author da_so
 */
public class User {
    
    private String uuid;
    private String password;
    private String benutzerName;
    private String vorname;
    private String nachname;
    private String email;
    
    private List<Nachricht> nachrichten;
    
    public User(String password, String benutzerName, String vorname, String nachname, String email, String uuid) {
        if(uuid == null){
            uuid = UUID.randomUUID().toString();
        }
        this.uuid = uuid;
        this.password = password;
        this.benutzerName = benutzerName;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.nachrichten = new ArrayList<Nachricht>();
    }

    public User(String password, String benutzerName) {
        this.password = password;
        this.benutzerName = benutzerName;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBenutzerName() {
        return benutzerName;
    }
    
    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }
    
    public List<Nachricht> getNachrichten() {
        return nachrichten;
    }
    
    public void addNachricht(Nachricht nachricht) {
        nachrichten.add(nachricht);
    }
    
    public void setNachrichten(List<Nachricht> nachrichten) {
        this.nachrichten = nachrichten;
    }
    
    public String getUserId() {
        return uuid;
    }
    
    public void setUserId(String uuid) {
        this.uuid = uuid;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getVorname() {
        return vorname;
    }
    
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    public String getNachname() {
        return nachname;
    }
    
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String createUserJSON(){
        JSONObject object = new JSONObject();
        object.put("useruid", uuid);
        object.put("username", benutzerName);
        object.put("password", password);
        object.put("prename", vorname);
        object.put("lastname", nachname);
        object.put("email", email);
        return object.toJSONString();
    }

    public static User fromJSON(String json) {
        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try{
            object = (JSONObject) jsonParser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if(object.containsKey("message")){
            return null;
        }
        User user = new User(object.get("password").toString(), object.get("username").toString(), object.get("prename").toString(), object.get("lastname").toString(), object.get("email").toString(), object.get("useruid").toString());
        System.out.println("User: "+user);
        return user;
    }

    public static ArrayList<User> fromJSONList(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        Object jsonObject = jsonParser.parse(json);
        if(jsonObject instanceof JSONObject){
            System.out.println("There are no users");
            return null;
        }
        JSONArray object = null;
        try{
            object = (JSONArray) jsonParser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object.toJSONString());
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < object.size(); i++){
            JSONObject elem = (JSONObject) object.get(i);
            users.add(new User(elem.get("password").toString(), elem.get("username").toString(), elem.get("prename").toString(), elem.get("lastname").toString(), elem.get("email").toString(), elem.get("useruid").toString()));
        }
        return users;
    }
    @Override
    public String toString(){
        return createUserJSON();
    }
}
