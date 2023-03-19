package ch.aelgict.easychatapp.model;

import ch.aelgict.easychatapp.entity.Nachricht;
import ch.aelgict.easychatapp.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class APIHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createNewUser() {

        User user = new User("1234", "testUser", "Damian", "Sommer", "damian@sommer5.ch", null);
        System.out.println(user);
        APIHandler apiHandler = new APIHandler();
        boolean ret = false;
        boolean exp = true;
        try{
            ret = apiHandler.createNewUser(user);
        }catch (IOException | URISyntaxException | InterruptedException e){

        }
        assertEquals(exp, ret);
    }

    @Test
    void getUserByNameAndPassword() {
        APIHandler apiHandler = new APIHandler();
        User ret = null;
        String expUUID = "jiewjajfaweogfmwekoaji";
        try{
            ret = apiHandler.getUserByNameAndPassword("damian", "1234");
        }catch (IOException | URISyntaxException | InterruptedException e){

        }
        assertEquals(expUUID, ret.getUserId());
    }

    @Test
    void getUserById() {
        APIHandler apiHandler = new APIHandler();
        User ret = null;
        String expUUID = "jiewjajfaweogfmwekoaji";
        try{
            ret = apiHandler.getUserById("jiewjajfaweogfmwekoaji");
        }catch (IOException | URISyntaxException | InterruptedException e){

        }
        assertEquals(expUUID, ret.getUserId());
    }

    @Test
    void getAllUsers() {
        APIHandler apiHandler = new APIHandler();
        ArrayList<User> ret = new ArrayList<User>();
        boolean exp = false;
        try{
            ret = apiHandler.getAllUsers();
        }catch (IOException | URISyntaxException | InterruptedException e){

        }
        assertEquals(exp, ret.isEmpty());
    }

    @Test
    void createNewMessage() {
        User user = new User("1234", "testUser", "Damian", "Sommer", "damian@sommer5.ch", null);
        System.out.println(user);
        Nachricht nachricht = new Nachricht("testNachricht", user, user, null);
        System.out.println(nachricht);
        APIHandler apiHandler = new APIHandler();
        boolean ret = false;
        boolean exp = true;
        try{
            apiHandler.createNewUser(user);
            ret = apiHandler.createNewMessage(nachricht);
        }catch (IOException | URISyntaxException | InterruptedException e){

        }
        assertEquals(exp, ret);
    }

    @Test
    void getMessagesBetweenTwoUsers() {
        APIHandler apiHandler = new APIHandler();
        boolean exp = false;
        ArrayList<Nachricht> nachrichten = null;
        try{
            nachrichten = apiHandler.getMessagesBetweenTwoUsers("jiewjajfawioshiseesrhaeogfmwekoaji", "jiewjajfaweogfmwekoaji");
        }catch (IOException | URISyntaxException | InterruptedException | ParseException |
                org.json.simple.parser.ParseException e){

        }
        assertEquals(exp, nachrichten.isEmpty());
    }

    @Test
    void getAllMessagesOfUser() {
        APIHandler apiHandler = new APIHandler();
        boolean exp = false;
        ArrayList<Nachricht> nachrichten = null;
        try{
            nachrichten = apiHandler.getAllMessagesOfUser("jiewjajfaweogfmwekoaji");
        }catch (IOException | URISyntaxException | InterruptedException | ParseException |
                org.json.simple.parser.ParseException e){
        }
        assertEquals(exp, nachrichten.isEmpty());
    }
}