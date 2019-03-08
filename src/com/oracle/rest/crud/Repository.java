package com.oracle.rest.crud;

/* This class implements a TreeMap data structure to store coins of Vending Machine. 
 * We use TreeMap because it allows you to find the just smallest key using floorkey() method
 * */

import java.util.TreeMap;

public class Repository {
	
	public static TreeMap<Integer, Integer> coinsInMachine = new TreeMap<Integer, Integer>();

}
