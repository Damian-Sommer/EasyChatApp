/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.model;

import ch.aelgict.easyChatApp.entity.Nachricht;
import ch.aelgict.easyChatApp.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author da_so
 */
public class VolatileModelTest {

    public VolatileModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUserList method, of class VolatileModel.
     */
    @Test
    public void testGetUserList() {
        System.out.println("getUserList");
        User user = new User("1234", "damian", "Damian", "Sommer", "damain@sommer5.ch");
        VolatileModel instance = new VolatileModel();
        instance.addUser(user);
        List<User> expResult = new ArrayList();
        expResult.add(user);
        List<User> result = instance.getUserList();
        assertEquals(expResult.get(0), result.get(0));
    }

    /**
     * Test of addUser method, of class VolatileModel.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = new User("1234", "damian", "Damian", "Sommer", "damain@sommer5.ch");
        VolatileModel instance = new VolatileModel();
        instance.addUser(user);
        assertEquals(1, instance.getUserList().size());
    }

    /**
     * Test of proveUser method, of class VolatileModel.
     */
    @Test
    public void testProveUserNull() {
        System.out.println("proveUser : null");
        User user = null;
        VolatileModel instance = new VolatileModel();
        int expResult = -3;
        int result = instance.proveUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of createNewUser method, of class VolatileModel.
     */
    @Test
    public void testCreateNewUser() {
        System.out.println("createNewUser: Alles Richtig");
        String username = "damian";
        String password = "1234";
        String passwordRepeat = "1234";
        String vorname = "Damian";
        String nachname = "Sommer";
        String email = "damian@sommer5.ch";
        VolatileModel instance = new VolatileModel();
        int[] expResult = new int[]{0, 0, 0, 0, 0};
        int[] result = instance.createNewUser(username, password, passwordRepeat, vorname, nachname, email);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createNewUser method, of class VolatileModel.
     */
    @Test
    public void testCreateNewUserEmailInvalid() {
        System.out.println("createNewUser: Email invalid");
        String username = "damian";
        String password = "1234";
        String passwordRepeat = "1234";
        String vorname = "Damian";
        String nachname = "Sommer";
        String email = "damian@sommer5";
        VolatileModel instance = new VolatileModel();
        int[] expResult = new int[]{0, 0, 0, 0, -1};
        int[] result = instance.createNewUser(username, password, passwordRepeat, vorname, nachname, email);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createNewUser method, of class VolatileModel.
     */
    @Test
    public void testCreateNewUserBenutzernameNull() {
        System.out.println("createNewUser: Benutzername null");
        String username = null;
        String password = "1234";
        String passwordRepeat = "1234";
        String vorname = "Damian";
        String nachname = "Sommer";
        String email = "damian@sommer5.ch";
        VolatileModel instance = new VolatileModel();
        int[] expResult = new int[]{-1, 0, 0, 0, 0};
        int[] result = instance.createNewUser(username, password, passwordRepeat, vorname, nachname, email);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createNewUser method, of class VolatileModel.
     */
    @Test
    public void testCreateNewUserBenutzernameKurz() {
        System.out.println("createNewUser: Benutzername zu kurz");
        String username = "dan";
        String password = "1234";
        String passwordRepeat = "1234";
        String vorname = "Damian";
        String nachname = "Sommer";
        String email = "damian@sommer5.ch";
        VolatileModel instance = new VolatileModel();
        int[] expResult = new int[]{-2, 0, 0, 0, 0};
        int[] result = instance.createNewUser(username, password, passwordRepeat, vorname, nachname, email);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createNewUser method, of class VolatileModel.
     */
    @Test
    public void testCreateNewUserPasswordInvalidUsernameToShort() {
        System.out.println("createNewUser: Benutzername zu kurz");
        String username = "damian1";
        String password = "1234";
        String passwordRepeat = "12345";
        String vorname = "Damian";
        String nachname = "Sommer";
        String email = "damian@sommer5.ch";
        VolatileModel instance = new VolatileModel();
        int[] expResult = new int[]{0, -2, 0, 0, 0};
        int[] result = instance.createNewUser(username, password, passwordRepeat, vorname, nachname, email);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getAllNachrichten method, of class VolatileModel.
     */
    @Test
    public void testGetAllNachrichtenNull() {
        System.out.println("getAllNachrichten");
        User user = new User("1234", "damian", "Damian", "Sommer", "damain@sommer5.ch");
        VolatileModel instance = new VolatileModel();
        List<Nachricht> result = instance.getAllNachrichten(user);
        assertEquals(0, result.size());
    }

    /**
     * Test of getRightUser method, of class VolatileModel.
     */
    @Test
    public void testGetRightUser() {
        System.out.println("getRightUser");
        String username = "damian";
        String password = "1234";
        User user = new User("1234", "damian", "Damian", "Sommer", "damain@sommer5.ch");
        VolatileModel instance = new VolatileModel();
        instance.addUser(user);
        int expResult = 0;
        int result = instance.getRightUser(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRightUser method, of class VolatileModel.
     */
    @Test
    public void testGetRightUserUserNotExisting() {
        System.out.println("getRightUser User not existing");
        String username = "damian";
        String password = "1234";
        VolatileModel instance = new VolatileModel();
        int expResult = -1;
        int result = instance.getRightUser(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class VolatileModel.
     */
    @Test
    public void testGetUserNull() {
        System.out.println("getUser");
        String username = "damian";
        VolatileModel instance = new VolatileModel();
        User expResult = null;
        User result = instance.getUser(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class VolatileModel.
     */
    @Test
    public void testGetUserFalse() {
        System.out.println("getUser");
        String username = "damian";
        User user = new User("1234", "damian", "Damian", "Sommer", "damain@sommer5.ch");
        VolatileModel instance = new VolatileModel();
        instance.addUser(user);
        boolean ret = instance.addUser(user);
        boolean expResult = false;
        assertEquals(expResult, ret);
    }

    /**
     * Test of getUser method, of class VolatileModel.
     */
    @Test
    public void testGetUserTrue() {
        System.out.println("getUser");
        String username = "damian";
        User user = new User("1234", "damian", "Damian", "Sommer", "damain@sommer5.ch");
        VolatileModel instance = new VolatileModel();
        boolean ret = instance.addUser(user);
        boolean expResult = true;
        assertEquals(expResult, ret);
    }

    /**
     * Test of addNachricht method, of class VolatileModel.
     */
    @Test
    public void testAddNachrichtFalse() {
        System.out.println("addNachricht : False");
        Nachricht nachricht = null;
        VolatileModel instance = new VolatileModel();
        boolean ret = instance.addNachricht(nachricht);
        boolean expRet = false;
        assertEquals(ret, expRet);
    }

    /**
     * Test of addNachricht method, of class VolatileModel.
     */
    @Test
    public void testAddNachrichtTrue() {
        System.out.println("addNachricht : True");

        User me = new User("1234", "me", "damian", "sommer", "damian@aelgict.ch");
        User user = new User("1234", "user", "jeawioj", "jieoawo", "info@aelgict.ch");
        Nachricht nachricht = new Nachricht("Hallo", me, user);
        
        VolatileModel instance = new VolatileModel();
        
        instance.addUser(me);
        instance.addUser(user);
        
        boolean ret = instance.addNachricht(nachricht);
        boolean expRet = true;
        assertEquals(ret, expRet);
    }

    /**
     * Test of getNachrichtenBetween method, of class VolatileModel.
     */
    @Test
    public void testGetNachrichtenBetween() {
        System.out.println("getNachrichtenBetween");
        
        User me = new User("1234", "me", "damian", "sommer", "damian@aelgict.ch");
        User user = new User("1234", "user", "jeawioj", "jieoawo", "info@aelgict.ch");
        
        VolatileModel instance = new VolatileModel();
        ArrayList<Nachricht> expResult = null;
        ArrayList<Nachricht> result = instance.getNachrichtenBetween(me, user);
        assertEquals(0, result.size());
    }

}
