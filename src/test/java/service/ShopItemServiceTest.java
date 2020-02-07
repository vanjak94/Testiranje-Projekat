/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import db.DeliveryServiceDAO;
import db.ShopItemDAO;
import db.TransactionDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import model.Client;
import model.DeliveryService;
import model.ShopItem;
import model.Transaction;
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
public class ShopItemServiceTest {
    private static ShopItemService service;
    private static int i;
    private static SimpleDateFormat sdf;
    
    public ShopItemServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        i = 0;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        service = new ShopItemService();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of postItem method, of class ShopItemService.
     */
    @Test
    public void testPostItem() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        boolean result = service.postItem("Artikal", 50.0f, 20);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(true, result);
        assertEquals("Artikal", gottenItem.getName());
        assertEquals(50.0f, gottenItem.getPrice(),0.2);
        assertEquals(20, gottenItem.getAmount());
    }
    @Test
    public void testPostItem2() {
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        boolean result = service.postItem("", 50.0f, 20);
        assertEquals(false, result);
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == i+1 && x.getName().equals("")){
                System.out.println(i);
                System.out.println(x.getId());
                System.out.println(x.getName());
                result = true;
            }
        }
        if(result == true){ i ++; }
        assertEquals(false, result);
    }
    @Test
    public void testPostItem3() {
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        boolean result = service.postItem("Artikal", 50.0f, 0);
        assertEquals(true, result);
        
        result = false;
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == i+1 && x.getAmount() == 0){
                result = true;
            }
        }
        if(result == true){ i ++; }
        assertEquals(true, result);
    }
    @Test
    public void testPostItem4() {
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        boolean result = service.postItem("Artikal", 0.0f, 20);
        assertEquals(true, result);
        
        result = false;
        for(ShopItem x: sdao.getAll()){
            if(x.getId() == i+1 && x.getPrice() == 0.0f){
                result = true;
            }
        }
        if(result == true){ i ++; }
        assertEquals(true, result);
    }

    /**
     * Test of removeItem method, of class ShopItemService.
     */
    @Test
    public void testRemoveItem() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        ShopItem newItem = new ShopItem(i, "Artikal", 20.0f, 20);
        sdao.insertOne(newItem);
        
        boolean result = service.removeItem(newItem);
        assertEquals(true, result);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(null, gottenItem);
    }
    @Test //bug
    public void testRemoveItem2() {
        ShopItem newItem = new ShopItem(44, "Artikal", 20.0f, 20);
        boolean result = service.removeItem(newItem);
        assertEquals(false, result);
    }
    @Test // bug
    public void testRemoveItem3() {
        ShopItem newItem = null;
        boolean result = service.removeItem(newItem);
        assertEquals(false, result);
    }

    /**
     * Test of buy method, of class ShopItemService.
     */
    @Test //Bug
    public void testBuy() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        ShopItem newItem = new ShopItem(i, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, 50);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(0, gottenItem.getAmount());
    }
    @Test
    public void testBuy2() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        ShopItem newItem = new ShopItem(i, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, 51);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(50, gottenItem.getAmount());
    }
    @Test//Bug negativenumber
    public void testBuy3() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        ShopItem newItem = new ShopItem(i, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, -1);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(50, gottenItem.getAmount());
    }
    @Test
    public void testBuyZero() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        ShopItem newItem = new ShopItem(i, "Artikal", 20.0f, 50);
        sdao.insertOne(newItem);
        service.buy(newItem, 0);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(50, gottenItem.getAmount());
    }

    /**
     * Test of stockUp method, of class ShopItemService.
     */
    @Test
    public void testStockUp() {
        i++;
        ShopItemService instance = new ShopItemService();
        ShopItemDAO sdao = new ShopItemDAO();
        ShopItem newItem = new ShopItem(i, "Artikal", 20.0f, 0);
        sdao.insertOne(newItem);
        service.stockUp(newItem, 1);
        ShopItem gottenItem = sdao.getOne(i);
        assertEquals(1, gottenItem.getAmount());
    }

    /**
     * Test of checkIfPopular method, of class ShopItemService.
     */
    @Test
    public void testCheckIfPopular() {
        i++;
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(i, "PopularniArtikal", 301.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 6100.0f, 61, new Date(), 1, i, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(true, result);
    }
    @Test
    public void testCheckIfPopular2() {
        i++;
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(i, "PopularniArtikal", 301.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 6000.0f, 60, new Date(), 1, i, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckIfPopular3() {
       i++;
       ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(i, "PopularniArtikal2", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 8100.0f, 81, new Date(), 1, i, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckIfPopular4() {
        i++;
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(i, "PopularniArtikal2", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 8000.0f, 80, new Date(), 1, i, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    @Test //bug 
    public void testCheckIfPopular5() throws ParseException {
        i++;
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(i, "PopularniArtikal", 301.0f, 100);
        sdao.insertOne(newItem);
        Date date = sdf.parse("2017-1-1");
        Transaction t = new Transaction(1, 6100.0f, 61, date, 1, i, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }
    
    @Test // bug 
    public void testCheckIfPopular6() throws ParseException {
        i++;
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(i, "PopularniArtikal", 299.0f, 100);
        sdao.insertOne(newItem);
        Date date = sdf.parse("2017-1-1");
        Transaction t = new Transaction(1, 8000.0f, 81, date , 1, i, 1, 30.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        boolean result = service.checkIfPopular(newItem);
        assertEquals(false, result);
    }

    /**
     * Test of getTrendingIndex method, of class ShopItemService.
     */
    @Test
    public void testTrending()  {
        i++;
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        
        LocalDate localDate = LocalDate.now().minusDays(1);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(i, "PopularniArtikal", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 1000.0f, 10, date , 1, i, 1, 0.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        float result = service.getTrendingIndex(newItem);
        assertEquals(result, 1000.0f,0.2);
    }
    
    @Test // bug 
    public void testTrending1()  {
        i++;
        
        ShopItemDAO sdao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tdao = new TransactionDAO();
        
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(i, "PopularniArtikal", 299.0f, 100);
        sdao.insertOne(newItem);
        Transaction t = new Transaction(1, 1000.0f, 10, date , 1, i, 1, 0.0f);
        cdao.insertOne(new Client(1,"a","a","a"));
        dsdao.insertOne(new DeliveryService(1,"name",20.0f,20.0f));
        tdao.insertOne(t);
        float result = service.getTrendingIndex(newItem);
        assertEquals(result, 1000.0f,0.2);
    }
    

    
}
