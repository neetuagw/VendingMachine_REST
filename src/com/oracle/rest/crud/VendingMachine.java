package com.oracle.rest.crud;

import java.util.Map;

import com.oracle.rest.entity.Coin;

/** * Declare api methods for Vending Machine *
 *  @author Neetu Agrawal */

public interface VendingMachine {

	public APIResponse getCoinsInMachine();
	public APIResponse initialiseMachine(Coin coin);
	public APIResponse userDeposited(Coin coin);
	public Map<Integer, Integer> generateChange(int number);
	public void removeCoinsFromMachine(Map<Integer, Integer> change);
	public void generateResponse();
	public void generateChangeResponse(Map<Integer, Integer> changeCoins);
}
