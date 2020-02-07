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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
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
public class TransactionServiceTest {
    private static int i;
    private static int b;
    private static TransactionService service;
    private static ShopItemService itemService;
    
    
    public TransactionServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        i = 0;
        b = 0;
        itemService = new ShopItemService();
        service = new TransactionService(itemService);
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
     * Test of completeTransaction method, of class TransactionService.
     */
    @Test
    public void testCompleteTransaction() {
        i++;
        b++;
        Client c = new Client(b,"Vanja","Vanja","Vanja");
        DeliveryService d = new DeliveryService(b,"DS",20.0f,25.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 250.0f, 30);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);
        Transaction transaction = tsdao.getOne(i);
        assertEquals(transaction.getClientId(), (int) c.getId());
        assertEquals(transaction.getDeliveryServiceId(), (int) d.getId());
        assertEquals(transaction.getShopItemId(), (int) s.getId());
    }
    
    @Test(expected = IllegalArgumentException.class) //bug 
    public void testCompleteTransaction2() {
        b++;
        Client c = new Client(null,"Vanja2","Vanja2","Vanja2");
        DeliveryService d = new DeliveryService(b,"DS",20.0f,25.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 250.0f, 30);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);

    }
    @Test(expected = IllegalArgumentException.class)//bug 
    public void testCompleteTransaction3() {       
        b++;
        Client c = new Client(b,"Vanja3","Vanja3","Vanja3");
        DeliveryService d = new DeliveryService(null,"DS",20.0f,25.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 250.0f, 30);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);

    }
    @Test(expected = IllegalArgumentException.class) //bug 
    public void testCompleteTransaction4() {       
        b++;
        Client c = new Client(b,"Vanja4","Vanja4","Vanja4");
        DeliveryService d = new DeliveryService(b,"DS",20.0f,25.0f);
        ShopItem s = new ShopItem(null,"ItemShop", 250.0f, 30);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);
    }
    @Test
    public void testCompleteTransaction5() {
        i++;
        b++;
        Client c = new Client(b,"Vanja5","Vanja5","Vanja5");
        DeliveryService d = new DeliveryService(b,"DS",20.0f,25.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 250.0f, 30);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30,1.0f);
        Transaction transaction = tsdao.getOne(i);
        assertEquals(transaction.getClientId(), (int) c.getId());
        assertEquals(transaction.getDeliveryServiceId(), (int) d.getId());
        assertEquals(transaction.getShopItemId(), (int) s.getId());
    }
    @Test
    public void testCompleteTransaction6() {
        i++;
        b++;
        Client c = new Client(b,"Vanja6","Vanja6","Vanja6");
        DeliveryService d = new DeliveryService(b,"DS",20.0f,25.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 250.0f, 30);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 1, 100.0f);
        Transaction transaction = tsdao.getOne(i);
        assertEquals(transaction.getClientId(), (int) c.getId());
        assertEquals(transaction.getDeliveryServiceId(), (int) d.getId());
        assertEquals(transaction.getShopItemId(), (int) s.getId());
    }
    
    @Test //bug
    public void calculatePrice60() {
        i++;
        b++;
        Client c = new Client(b,"Vanja8","Vanja8","Vanja8");
        DeliveryService d = new DeliveryService(b,"DS",10.0f,10.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 100.0f, 300);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 60, 100.0f); 
        Transaction transaction = tsdao.getOne(b);
        float expectedPrice = 103750.0f;
        assertEquals(expectedPrice,transaction.getTotalPrice(),0.2);
    }
    @Test //bug
    public void calculatePrice10() {
        i++;
        b++;
        Client c = new Client(b,"Vanja9","Vanja9","Vanja9");
        DeliveryService d = new DeliveryService(b,"DS",10.0f,10.0f);
        ShopItem s = new ShopItem(b,"ItemShop", 100.0f, 300);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 10, 100.0f);
        Transaction transaction = tsdao.getOne(i);
        float expectedPrice = 99250.0f;
        assertEquals(expectedPrice,transaction.getTotalPrice(),0.2);
    }

    /**
     * Test of getRecentTransactions method, of class TransactionService.
     */
    @Test
    public void getRecentTransactions() {
        i++;
        b++;
        LocalDate localDate = LocalDate.now().minusDays(31);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(b, "PopularniArtikal", 299.0f, 100);
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        sidao.insertOne(newItem);
        Transaction t = new Transaction(i, 1000.0f, 10, date , b, b, b, 0.0f);
        cdao.insertOne(new Client(b,"Vanja11","Vanja11","Vanja11"));
        dsdao.insertOne(new DeliveryService(b,"name",20.0f,20.0f));
        tsdao.insertOne(t);
        boolean result = true;
        ArrayList<Transaction> transactionList = service.getRecentTransactions();
        for( Transaction x: transactionList){
            if(x.getId() != null){
                if(Objects.equals(x.getId(), t.getId())){
                    System.out.println(x.getId() + " ID Transakcije");
                    System.out.println(x.getDate()+ " Date Transakcije");
                    result = false;
                }
            }
            
        }
        assertEquals(true, result);
    }
    @Test
    public void getRecentTransactions2(){
        i++;
        b++;
        LocalDate localDate = LocalDate.now().minusDays(1);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        ShopItemDAO sidao = new ShopItemDAO();
        ClientDAO cdao = new ClientDAO();
        DeliveryServiceDAO dsdao = new DeliveryServiceDAO();
        TransactionDAO tsdao = new TransactionDAO();
        ShopItem newItem = new ShopItem(b, "PopularniArtikal", 299.0f, 100);
        sidao.insertOne(newItem);
        Transaction t = new Transaction(i, 1000.0f, 10, date , b, b, b, 0.0f);
        cdao.insertOne(new Client(b,"Vanja12","Vanja12","Vanja12"));
        dsdao.insertOne(new DeliveryService(b,"name",20.0f,20.0f));
        tsdao.insertOne(t);
        boolean result = false;
        ArrayList<Transaction> transactionList = service.getRecentTransactions();
        for( Transaction x: transactionList){
            if(Objects.equals(x.getId(), t.getId())){
                result = true;
            }
        }
        assertEquals(true, result);
    }
    
}
