package com.mitesh.billingsystem;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitesh.billingsystem.common.Basket;
import com.mitesh.billingsystem.common.Product;
import com.mitesh.billingsystem.common.enums.CustomerType;
import com.mitesh.billingsystem.common.enums.ProductType;
import com.mitesh.billingsystem.service.BillGenerationPostDiscountService;
import com.mitesh.billingsystem.serviceImpl.BillGenerationPostDiscountServiceImpl;
import com.mitesh.billingsystem.user.Customer;

/**
 * Main file to test jar file. 
 * User can change input and check accordingly.
 * 
 * @author miteshanand
 *
 */
public class App {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		Product pShoe = new Product(1L, "Shoe", 526, ProductType.OTHERS);
		Product pWater = new Product(2L, "Water", 20, ProductType.GROCERY);
		
		Customer employee = new Customer("TestEmployee", CustomerType.EMPLOYEE, new Date());
		Basket basket = new Basket();
		basket.addProduct(pShoe);
		basket.addProduct(pWater);
		employee.setBasket(basket);
		
		BillGenerationPostDiscountService discountService = new BillGenerationPostDiscountServiceImpl();
		double clientFinalBill = discountService.generateBillPostDiscount(employee);
		LOGGER.info("Client Bill is: {}, for basket items {}", clientFinalBill, basket.getItems());
	}
}
