package com.mitesh.billingsystem.user;

import java.util.Date;

import com.mitesh.billingsystem.business.BusinessRuleDiscount;
import com.mitesh.billingsystem.common.Basket;
import com.mitesh.billingsystem.common.enums.CustomerType;

public class Customer {

	private String name;

	private Date createdDate;

	private CustomerType customerType;

	private Basket basket;

	private BusinessRuleDiscount businessRuleDiscount = new BusinessRuleDiscount();

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Customer(String name, CustomerType customerType, Date createdDate) {
		super();
		this.setName(name);
		this.customerType = customerType;
		this.createdDate = createdDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * This method is used to get discount percentage by userType.
	 * 
	 * @return double
	 */
	public double getDiscount() {
		return businessRuleDiscount.getDiscountBasedOnCustomer(this.customerType, getCreatedDate());
	}

	public String getName() {
		return name;
	}

}
