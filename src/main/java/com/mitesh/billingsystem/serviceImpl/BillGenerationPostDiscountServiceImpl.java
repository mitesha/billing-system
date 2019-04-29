package com.mitesh.billingsystem.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitesh.billingsystem.common.Basket;
import com.mitesh.billingsystem.common.Product;
import com.mitesh.billingsystem.common.enums.ProductType;
import com.mitesh.billingsystem.service.BillGenerationPostDiscountService;
import com.mitesh.billingsystem.user.Customer;

public class BillGenerationPostDiscountServiceImpl implements BillGenerationPostDiscountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillGenerationPostDiscountServiceImpl.class);

	/**
	 * method is used to generate bill for user after discounts.
	 * 
	 * @param user
	 * @return
	 */
	public double generateBillPostDiscount(Customer user) {
		if(null == user) {
			LOGGER.error("Invalid input!");
			return 0;
		}
		
		Basket basket = user.getBasket();
		double discount = user.getDiscount();
		LOGGER.info("Calulate Bill for basket {}, with discount {}", basket, discount);
		if(basket == null || basket.getItems().isEmpty()) {
			return 0;
		}
		
		double totalBill = 0;
		Map<Product, Integer> basketData = basket.getItems();
		List<Double> list = new ArrayList<>();
		basketData.entrySet().stream().forEach(k -> {
			Product product = k.getKey();
			if(product.getType() != ProductType.GROCERY) {
				list.add((product.getPrice() * k.getValue()) * (1 - discount)); 
			} else {
				list.add(product.getPrice() * k.getValue()); 
			}
		});
		
		totalBill = list.stream().mapToDouble(Double::doubleValue).sum();
		
		/**
		 * divide by 100 to discount 5 on every 100.
		 */
		int divByHundred = (int) (totalBill / 100); 
		return (totalBill - (divByHundred * 5));
	}
}
