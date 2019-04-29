/**
 * 
 */
package com.mitesh.billingsystem.common.enums;

/**
 * @author miteshanand
 *
 */
public enum CustomerType {
	EMPLOYEE(.3),
	AFFILIATE(.1),
	CUSTOMERFROMLONGTERM(.05);
	
	private double discount;
	
	private CustomerType(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}

}
