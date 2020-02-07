/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import model.Client;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Vanja
 */
public class ClientServiceTest {
    private static int i;
    
    public ClientServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        i = 0;
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of login method, of class ClientService.
     */
    @Test
    @org.junit.Test
    public void testLogin1() {
        i++;
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        cdao.insertOne(new Client(i,"Vaanja", "Vanjaa", "123"));
        Client loggedClient = instance.login("Vanjaa", "123");
        assertNotEquals(null, loggedClient);
    }
    @org.junit.Test//Pronadjen bug
    public void testLogin2() {
        i++;
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        cdao.insertOne(new Client(i,"Vanjaa", "Vanja1", "123"));
        Client loggedClient = instance.login("Vanja1", "123");
        assertEquals(loggedClient.getUsername(), "Vanja1");
        assertEquals(loggedClient.getName(), "Vanjaa");
        assertEquals(loggedClient.getPassword(), "123");
        
    }

    /**
     * Test of register method, of class ClientService.
     */
    @org.junit.Test
    public void testRegistration1() {
        i++;
        System.out.println("register");
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        boolean result = instance.register("Vanja", "Vanjaa", "123");
        boolean expResult = true;
        assertEquals(expResult, result);
        Client dbClient = cdao.getOne(i);
        assertEquals("Vanja", dbClient.getName());
    }
    @org.junit.Test //bug
    public void testRegistration2() {
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        boolean result = instance.register("", "Vanjaa", "123");        
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getName().equals("")){
                result = false;
                break;
            }
        }
        if(!result){
            i++;
        }
        assertEquals(!expResult, result);
    }
    @org.junit.Test // bug. unosi podatke a ne bih trebao bez username
    public void testRegistration3() { 
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        boolean result = instance.register("Vanja", "", "123");     
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getUsername().equals("")){
                result = false;
                break;
            }
        }
        if(!result){
            i++;
        }
        assertEquals(!expResult, result);
    }
    @org.junit.Test
    public void testRegistration4() {
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        boolean result = instance.register("Vanja", "Vanjaa", "");
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getPassword().equals("")){
                result = false;
                break;
            }
        }
        
        if(!result){
            i++;
        }
        assertEquals(!expResult, result);
    }
    @org.junit.Test // Pronadjen bug,ne bih trebao da napravi dva korisnika sa istim usernamom
    public void testRegistration5() {
        i++;
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        instance.register("Vanja", "Vanja", "123");
        boolean result = instance.register("Vanja", "Vanja", "123");
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
 
    

    /**
     * Test of deleteUser method, of class ClientService.
     */
    @org.junit.Test
    public void testDeleteUser1() {
        i++;
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        Client c = new Client(i,"Vanja", "Vanjaa", "123");
        cdao.insertOne(c);
        instance.deleteUser(c);
        boolean result = true;
        for(Client x: cdao.getAll()){
            if(x.getId() == i){
                result = false;
            }
        }
        assertEquals(true, result);
    } 

    /**
     * Test of updateInfo method, of class ClientService.
     */
    @org.junit.Test //bug
    public void testUpdateInfo() {
        i++;
        ClientService instance = new ClientService();
        ClientDAO cdao = new ClientDAO();
        System.out.println(i);
        Client c = new Client(15,"Vanja", "Vanjaa", "123");
        cdao.insertOne(c);
        boolean result = false;
        instance.updateInfo(c, "Test", "test", "123");
        for(Client x: cdao.getAll()){
            if(x.getId() == i){
                if(x.getName().equals("test") && x.getPassword().equals("123"))
                result = true;
            }
        }
        assertEquals(true, result);
    }
    
}
