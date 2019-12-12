package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	VendingMachineItem a,b,c,d;
	VendingMachine v;

	@Before
	public void setUp() throws Exception {
		a = new VendingMachineItem( "Chips", .75 );
		v = new VendingMachine();
	}

	@Test
	public void addItemTest() {
		v.addItem(a, "A");
		assertNotNull(v.getItem("A"));
	}
	
	@Test(expected = VendingMachineException.class)
	public void addItemTestExcep() {
		v.addItem(a, "A");
		v.addItem(a, "A");
	}
	
	@Test
	public void removeItemTest() {
		v.addItem(a, "A");
		v.removeItem("A");
		assertNull(v.getItem("A"));
	}
	
	@Test(expected = VendingMachineException.class)
	public void removeItemExcepTest() {
		v.removeItem("A");
	}
	
	@Test(expected = VendingMachineException.class)
	public void removeItemExcepTest2() {
		v.removeItem("Z");
	}
	
	
	@Test
	public void getBalanceTest() {
		v.insertMoney(10);
		assertEquals(10, v.getBalance(), .00001);
	}
	
	@Test
	public void insertMoneyTest() {
		v.insertMoney(10);
		assertEquals(v.getBalance(), 10, .00001);
	}
	
	@Test
	public void insertMoneyTestPostCond() {
		v.insertMoney(10);
		v.insertMoney(10);		
		assertEquals(v.getBalance(), 20, .00001);
	}
	
	@Test(expected = VendingMachineException.class)
	public void insertMoneyTestExcep() {
		v.insertMoney(-3);
	}
	
	@Test
	public void makePurchaseTest() {
		v.addItem(a, "A");
		v.insertMoney(10);
		assertTrue(v.makePurchase("A"));
	}
	
	@Test
	public void makePurchaseTestondPostC() {
		v.addItem(a, "A");
		v.insertMoney(10);
		v.makePurchase("A");
		assertEquals(9.25, v.getBalance(), .00001);
	}
	
	
	@Test
	public void returnChangeTest() {
		v.insertMoney(10);
		v.addItem(a, "A");
		v.makePurchase("A");
		assertEquals(9.25, v.returnChange(), .00001);
	}
	@Test
	public void returnChangeTestPostCond() {
		v.insertMoney(10);
		v.addItem(a, "A");
		v.makePurchase("A");
		v.returnChange();
		assertEquals(0, v.getBalance(), .00001);
	}
	
	@Test
	public void getSlotIndexTest() {
		v.addItem(a, v.A_CODE);
		v.addItem(a, v.B_CODE);
		v.addItem(a, v.C_CODE);
		v.addItem(a, v.D_CODE);
		int k=0;
		for(char i ='A'; i < 'E'; i++) {
			assertEquals(k, v.getSlotIndex(String.valueOf(i)), .0001);
			k++;
		}
	}
	@Test (expected = VendingMachineException.class)
	public void getSlotIndexExcep() {
		v.getSlotIndex("Z");
		
		
	}

	@Test(expected = VendingMachineException.class)
	public void construcTest() {
		a = new VendingMachineItem("Chips", -1.0);
	}
	
	@Test
	public void getNameTest() {
		assertTrue(a.getName() == "Chips");
	}

	@Test
	public void getPriceTest() {
		assertTrue( a.getPrice() == .75);
	}
	
	@Test
	public void constrTest() {
		assertNotNull(v);
	}
	

	@After
	public void tearDown(){
		a = null;
		v = null;
	}

}
