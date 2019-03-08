package com.oracle.rest.crud;

import java.util.Map;

import com.oracle.rest.entity.Coin;

/**
 * * Declare api methods for Vending Machine *
 * 
 * @author Neetu Agrawal
 */

public interface VendingMachine {

	public APIResponse getCoinsInMachine(); // Method to retrieve coins available in Machine

	public APIResponse initialiseMachine(Coin coin); // Method to add new coins in Machine

	public APIResponse userDeposited(Coin coin); // Method to add User coins and produce change

	public Map<Integer, Integer> generateChange(int number); // Method to generate list of coins from change

	public void removeCoinsFromMachine(Map<Integer, Integer> change); // Method to remove coins left after change from
																		// the machine

	public void generateResponse(); // Method to create response of Post InitialiseMachine request

	public void generateChangeResponse(Map<Integer, Integer> changeCoins); // Method to create response with left change
}
