package com.oracle.rest.crud;

/* This class implements and defined all the CRUD functions 
 * */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.oracle.rest.entity.Coin;

public class VendingMachineImp implements VendingMachine {

	private TreeMap<Integer, Integer> mapOfCoins;
	APIResponse apiResp = new APIResponse();
	CoinsInResponse response = new CoinsInResponse();

	// Initialising a empty Map to store the coins of change left after User payment
	private Map<Integer, Integer> changeCoins = new HashMap<Integer, Integer>();
	{
		changeCoins.put(5, 0);
		changeCoins.put(10, 0);
		changeCoins.put(20, 0);
		changeCoins.put(50, 0);
		changeCoins.put(100, 0);
		changeCoins.put(200, 0);

	}

	@Override
	public APIResponse getCoinsInMachine() {
		// TODO Auto-generated method stub
		mapOfCoins = Repository.coinsInMachine;
		// response = new APIResponse();
		if (mapOfCoins.containsKey(5)) {
			generateResponse();
		}
		apiResp.message = "";
		apiResp.coins = response;
		return apiResp;
	}

	@Override
	public APIResponse initialiseMachine(Coin coin) {
		// TODO Auto-generated method stub

		mapOfCoins = new TreeMap<Integer, Integer>();
		mapOfCoins.put(5, coin.fiveP);
		mapOfCoins.put(10, coin.tenP);
		mapOfCoins.put(20, coin.twentyP);
		mapOfCoins.put(50, coin.fiftyP);
		mapOfCoins.put(100, coin.one);
		mapOfCoins.put(200, coin.two);

		Repository.coinsInMachine.putAll(mapOfCoins);
		apiResp.message = "Successfully Initialised";

		// Creating API response
		generateResponse();

		apiResp.coins = response;
		return apiResp;
	}

	@Override
	public APIResponse userDeposited(Coin coin) {
		// TODO Auto-generated method stub

		Coin entity = new Coin();
		
		//Deposite User's coins in Vending Machine
		Repository.coinsInMachine.put(5, Repository.coinsInMachine.get(5) + coin.fiveP);
		Repository.coinsInMachine.put(10, Repository.coinsInMachine.get(10) + coin.tenP);
		Repository.coinsInMachine.put(20, Repository.coinsInMachine.get(20) + coin.twentyP);
		Repository.coinsInMachine.put(50, Repository.coinsInMachine.get(50) + coin.fiftyP);
		Repository.coinsInMachine.put(100, Repository.coinsInMachine.get(100) + coin.one);
		Repository.coinsInMachine.put(200, Repository.coinsInMachine.get(200) + coin.two);

		//Getting change amount
		entity.change = coin.paidAmount - coin.itemCost;
		int change = entity.change;

		apiResp.message = "Successfull transaction! Here is your change";
		
		//Generate list of coins left after change
		generateChange(change);
		
		//Generate response with change coins
		generateChangeResponse(changeCoins);
		
		//Remove change coins from the machine
		removeCoinsFromMachine(changeCoins);
		apiResp.coins = response;
		return apiResp;
	}

	@Override
	public Map<Integer, Integer> generateChange(int change) {

		if (change != 0) {
			
			//Check if the coin already exists of the value of change
			if (Repository.coinsInMachine.containsKey(change)) {
				
				//Returns the greatest coin less than or equal to the given value
				int keyCoin = Repository.coinsInMachine.floorKey(change);
				changeCoins.put(keyCoin, 1);
			} else {
				
				//Returns the greatest coin less than or equal to the given value
				int keyCoin = Repository.coinsInMachine.floorKey(change);
				int quotient = change / keyCoin;
				change = change % keyCoin;
				changeCoins.put(keyCoin, quotient);
				generateChange(change);
			}

		}
		return changeCoins;
	}

	@Override
	public void removeCoinsFromMachine(Map<Integer, Integer> change) {
		// TODO Auto-generated method stub

		for (int item : change.keySet()) {

			if (Repository.coinsInMachine.containsKey(item)) {
				int value = Repository.coinsInMachine.get(item);
				value = value - change.get(item);
				Repository.coinsInMachine.put(item, value);
			}
		}
	}

	@Override
	public void generateResponse() {
		// TODO Auto-generated method stub
		mapOfCoins.putAll(Repository.coinsInMachine);
		response.fiveP = mapOfCoins.get(5);
		response.tenP = mapOfCoins.get(10);
		response.twentyP = mapOfCoins.get(20);
		response.fiftyP = mapOfCoins.get(50);
		response.one = mapOfCoins.get(100);
		response.two = mapOfCoins.get(200);
	}

	@Override
	public void generateChangeResponse(Map<Integer, Integer> changeCoins) {
		// TODO Auto-generated method stub
		response.fiveP = changeCoins.get(5);
		response.tenP = changeCoins.get(10);
		response.twentyP = changeCoins.get(20);
		response.fiftyP = changeCoins.get(50);
		response.one = changeCoins.get(100);
		response.two = changeCoins.get(200);
	}

}
