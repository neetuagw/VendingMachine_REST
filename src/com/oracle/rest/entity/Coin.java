package com.oracle.rest.entity;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

//@Produces("application/json")
//@XmlRootElement
public class Coin {

	/**
	 * All the possible values of coin 50 = 50p, 200 = £2
	 */
	public static final int[] POSSIBLE_VALUES = { 5, 10, 20, 50, 100, 200 };

	public Coin() {
		
	}

	public int itemCost;
	public int paidAmount;
	public int change;
	
	public int fiveP;
	public int tenP;
	public int twentyP;
	public int fiftyP;
	public int one;
	public int two;

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public int getFiveP() {
		return fiveP;
	}

	public void setFiveP(int fiveP) {
		this.fiveP = fiveP;
	}

	public int getTenP() {
		return tenP;
	}

	public void setTenP(int tenP) {
		this.tenP = tenP;
	}

	public int getTwentyP() {
		return twentyP;
	}

	public void setTwentyP(int twentyP) {
		this.twentyP = twentyP;
	}

	public int getFiftyP() {
		return fiftyP;
	}

	public void setFiftyP(int fiftyP) {
		this.fiftyP = fiftyP;
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}
}
