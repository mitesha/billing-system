package com.mitesh.billingsystem;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.mitesh.billingsystem.common.Basket;
import com.mitesh.billingsystem.common.Product;
import com.mitesh.billingsystem.common.enums.CustomerType;
import com.mitesh.billingsystem.common.enums.ProductType;
import com.mitesh.billingsystem.service.BillGenerationPostDiscountService;
import com.mitesh.billingsystem.serviceImpl.BillGenerationPostDiscountServiceImpl;
import com.mitesh.billingsystem.user.Customer;

public class BillGenerationPostDiscountServiceTest {
	
	private BillGenerationPostDiscountService discountService = new BillGenerationPostDiscountServiceImpl();
	
	@Test
	public void testCalculateBill() {
		Customer employee = new Customer("TestEmployee", CustomerType.EMPLOYEE, new Date());
		Product pShoe = new Product(2L, "Shoe", 526, ProductType.OTHERS);
		Product pWater = new Product(3L, "Water", 20, ProductType.GROCERY);
		
		Basket basket = new Basket();
		basket.addProduct(pShoe);
		basket.addProduct(pWater);
		employee.setBasket(basket);
		double clientFinalBill = discountService.generateBillPostDiscount(employee);
		Assert.assertEquals(373.2, clientFinalBill, 0.001);
		
		basket.removeProduct(pWater);
		basket.removeProduct(pShoe);
		employee.setBasket(basket);
		double res2 = discountService.generateBillPostDiscount(employee);
		Assert.assertEquals(0.0, res2, 0.001);
		
		double res3 = discountService.generateBillPostDiscount(null);
		Assert.assertEquals(0.0, res3, 0.001);
	}

}
