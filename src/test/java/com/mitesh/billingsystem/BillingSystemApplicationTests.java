package com.mitesh.billingsystem;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mitesh.billingsystem.common.Basket;
import com.mitesh.billingsystem.common.Product;
import com.mitesh.billingsystem.common.enums.CustomerType;
import com.mitesh.billingsystem.common.enums.ProductType;
import com.mitesh.billingsystem.service.BillGenerationPostDiscountService;
import com.mitesh.billingsystem.serviceImpl.BillGenerationPostDiscountServiceImpl;
import com.mitesh.billingsystem.user.Customer;

public class BillingSystemApplicationTests {

	private static final String MOBILE = "MOBILE";
	private static final String CHOCOLATE = "CHOCOLATE";
	private static final String SHOE = "SHOE";
	private static final String MILK = "MILK";
	private static final String WATER = "WATER";
	
	private BillGenerationPostDiscountService discountService = new BillGenerationPostDiscountServiceImpl();
	
	private static Map<String, Product> data;
	
	@BeforeClass
	public static void dataSetUp() {
		data = new HashMap<>();
		Product pMilk = new Product();
		pMilk.setId(1L);
		pMilk.setName("Milk");
		pMilk.setPrice(26);
		pMilk.setType(ProductType.GROCERY);
		data.put(MILK, pMilk);
		data.put(SHOE, new Product(2L, "Shoe", 526, ProductType.OTHERS));
		data.put(WATER, new Product(3L, "Water", 20, ProductType.GROCERY));
		data.put(MOBILE, new Product(4L, "Mobile", 1526, ProductType.OTHERS));
		data.put(CHOCOLATE, new Product(5L, "Chocolate", 15, ProductType.GROCERY));
	}

	@Test
	public void testEmployeeUser() {
		Customer employee = new Customer("TestEmployee", CustomerType.EMPLOYEE, new Date());
		Basket basket = new Basket();
		basket.addProduct(data.get(WATER));
		basket.addProduct(data.get(WATER));
		basket.addProduct(data.get(MILK));
		employee.setBasket(basket);
		double clientFinalBill = discountService.generateBillPostDiscount(employee);
		Assert.assertEquals(66.0, clientFinalBill, 0.001);
		
		
		Customer employee2 = new Customer("TestEmployee2", CustomerType.EMPLOYEE, new Date());
		Basket basket2 = new Basket();
		basket2.addProduct(data.get(SHOE));
		basket2.addProduct(data.get(SHOE));
		basket2.addProduct(data.get(CHOCOLATE));
		employee2.setBasket(basket2);
		double res2 = discountService.generateBillPostDiscount(employee2);
		Assert.assertEquals(716.4, res2, 0.001);
	}
	
	@Test
	public void testCustomerUser() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -3); 
		Date historyDate = cal.getTime();
		
		Customer cust = new Customer("TestUser",CustomerType.CUSTOMERFROMLONGTERM, historyDate);
		Basket basket = new Basket();
		basket.addProduct(data.get(MOBILE));
		basket.addProduct(data.get(WATER));
		cust.setBasket(basket);
		double res = discountService.generateBillPostDiscount(cust);
		Assert.assertEquals(1399.7, res, 0.001);

		Basket basket2 = new Basket();
		basket2.addProduct(data.get(CHOCOLATE));
		basket2.addProduct(data.get(CHOCOLATE));
		basket2.addProduct(data.get(CHOCOLATE));
		cust.setBasket(basket2);
		double res2 = discountService.generateBillPostDiscount(cust);
		Assert.assertEquals(45, res2, 0.001);
		
		Customer cust2 = new Customer("TestUser",CustomerType.CUSTOMERFROMLONGTERM, new Date());
		Basket basket3 = new Basket();
		basket3.addProduct(data.get(CHOCOLATE));
		basket3.addProduct(data.get(CHOCOLATE));
		basket3.addProduct(data.get(CHOCOLATE));
		basket3.removeProduct(data.get(CHOCOLATE));
		cust2.setBasket(basket3);
		double res3 = discountService.generateBillPostDiscount(cust2);
		Assert.assertEquals(30.0, res3, 0.001);
	}
	
	@Test
	public void testAffiliateUser() {

		Customer cust = new Customer("TestAffilicate",CustomerType.AFFILIATE, new Date());
		Basket basket = new Basket();
		basket.addProduct(data.get(MOBILE));
		basket.addProduct(data.get(SHOE));
		basket.addProduct(data.get(SHOE));
		basket.addProduct(data.get(MILK));
		basket.addProduct(data.get(MILK));
		basket.addProduct(data.get(MILK));
		cust.setBasket(basket);
		double res = discountService.generateBillPostDiscount(cust);
		Assert.assertEquals(2283.20, res, 0.001);

		Basket basket2 = new Basket();
		basket2.addProduct(data.get(CHOCOLATE));
		basket2.addProduct(data.get(CHOCOLATE));
		basket2.addProduct(data.get(CHOCOLATE));
		cust.setBasket(basket2);
		double res2 = discountService.generateBillPostDiscount(cust);
		Assert.assertEquals(45, res2, 0.001);
	}
	
}
