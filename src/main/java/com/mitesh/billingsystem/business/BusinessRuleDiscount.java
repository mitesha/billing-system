package com.mitesh.billingsystem.business;

import java.util.Calendar;
import java.util.Date;

import com.mitesh.billingsystem.common.enums.CustomerType;

public class BusinessRuleDiscount {
	
	private static final int NO_OF_YEARS_FOR_CUSTOMER = 2;

	/**
	 * This method is used get discount based on CustomerType and creation date.
	 * 
	 * @param cType
	 * @param customerCreationDate
	 * @return
	 */
	public double getDiscountBasedOnCustomer(CustomerType cType, Date customerCreationDate) {
		if(cType == CustomerType.CUSTOMERFROMLONGTERM) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -NO_OF_YEARS_FOR_CUSTOMER); // to get previous year add -2
			Date twoYearsBackDate = cal.getTime();
			if(customerCreationDate.after(twoYearsBackDate)) {
				return 0;
			}
		}
		return cType.getDiscount();
	}
}
