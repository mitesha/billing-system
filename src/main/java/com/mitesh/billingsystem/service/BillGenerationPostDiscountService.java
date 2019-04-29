/**
 * 
 */
package com.mitesh.billingsystem.service;

import com.mitesh.billingsystem.user.Customer;

/**
 * @author miteshanand
 *
 */
public interface BillGenerationPostDiscountService {

	/**
	 * method is used to generate bill for user after discounts.
	 * 
	 * @param user
	 * @return
	 */
	double generateBillPostDiscount(Customer user);
}
