/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.DeliveryServiceDAO;
import model.DeliveryService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanja
 */
public class DeliveryServiceServiceTest {
    private static int i;
    
    public DeliveryServiceServiceTest() {
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
     * Test of register method, of class DeliveryServiceService.
     */
    @Test
    public void testRegister() {
        i++;
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        boolean result = dss.register("DeliveryService1",200.0f,50.0f);
        assertEquals(true, result);
        assertEquals(i,(int)ddao.getOne(i).getId());
        assertEquals(200.0f,(float)ddao.getOne(i).getStartingPrice(),0.2);
        assertEquals(50.0f,(float)ddao.getOne(i).getPricePerKilometer(),0.2);
        assertEquals("DeliveryService1", ddao.getOne(i).getName());
    }
    @Test
    public void testRegister2() {
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        boolean result = dss.register("DeliveryService",-1.0f,200.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getPricePerKilometer() == -1.0f){
                result = false;
            }
        }
        if(!result){
            i++;
        }
        assertEquals(true, result);
    }
    @Test
    public void testRegister3() {
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        boolean result = dss.register("DeliveryService",200.0f,-1.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getStartingPrice() == -1.0f){
                result = false;
            }
        }
        if(!result){
            i++;
        }
        assertEquals(true, result);
    }
    @Test
    public void testRegister4() {
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        boolean result = dss.register("DeliveryService",0.0f,200.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getPricePerKilometer() == 0.0f){
                result = false;
            }
        }
        if(!result){
            i++;
        }
        assertEquals(true, result);
    }
    @Test 
    public void testRegister5() { 
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        boolean result = dss.register("",200.0f,0.0f);
        assertEquals(false, result);
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if( x.getName().equals("")){
                result = false;
            }
        }
        if(!result){
            i++;
        }
        assertEquals(true, result);
    }

    /**
     * Test of deleteDeliveryService method, of class DeliveryServiceService.
     */
    @Test
    public void testDelete1() {   
        i++;
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        DeliveryService newDS =  new DeliveryService(i, "Servis1", 30.0f, 35.0f);
        ddao.insertOne(newDS);
        boolean result = dss.deleteDeliveryService(newDS);
        assertEquals(true, result);
        
        for(DeliveryService x: ddao.getAll()){
            if(x.getId() == i){
                result = false;
            }
        }
        assertEquals(true, result);
    }
    @Test // bug
    public void testDelete2() {
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        boolean result = dss.deleteDeliveryService(new DeliveryService(999, "Ime", 1.0f,1.0f));
        assertEquals(false, result);
        
        result = true;
        for(DeliveryService x: ddao.getAll()){
            if(x.getName().equals("Ime")){
                result = false;
            }
        }
        assertEquals(true, result);
    }

    /**
     * Test of updateInfo method, of class DeliveryServiceService.
     */
    @Test
    public void testUpdate1() {
        i++;
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        DeliveryService newDS = new DeliveryService(i, "Servis1", 20.0f, 21.0f);
        ddao.insertOne(newDS);
        
        boolean result = dss.updateInfo(newDS, "Servis2", 25.0f, 30.0f);
        assertEquals(true, result);
        
        DeliveryService dbDS = ddao.getOne(i);
        assertEquals("Servis552", dbDS.getName());
    }
    @Test // bug
    public void testUpdate2() {
        i++;
        DeliveryServiceService dss = new DeliveryServiceService();
        DeliveryServiceDAO ddao = new DeliveryServiceDAO();
        DeliveryService newDS = new DeliveryService(i, "Servis1", 20.0f, 21.0f);
        ddao.insertOne(newDS);
        
        boolean result = dss.updateInfo(newDS, "Servis1", 25.0f, 30.0f);
        assertEquals(true, result);
        
        DeliveryService dbDS = ddao.getOne(i);
        assertEquals(25.0f, dbDS.getPricePerKilometer(), 0.2);
        assertEquals(30.0f, dbDS.getStartingPrice(), 0.2);
    }
    
}
