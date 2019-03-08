package com.oracle.rest.crud;

public class APIResponse {

	public String message;
	
	public CoinsInResponse coins;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CoinsInResponse getCoins() {
		return coins;
	}

	public void setCoins(CoinsInResponse coins) {
		this.coins = coins;
	}
	
	
}
