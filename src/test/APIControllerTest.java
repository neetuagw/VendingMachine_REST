package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeMap;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.oracle.rest.APIController;
import com.oracle.rest.crud.APIResponse;
import com.oracle.rest.crud.Repository;
import com.oracle.rest.entity.Coin;

/*
 * The AAA (Arrange-Act-Assert) pattern used
 * Arrange all necessary preconditions and inputs.
 * Act on the object or method under test.
 * Assert that the expected results have occurred.
 * */

class APIControllerTest {

	APIController controller;
	Coin coin = new Coin();
	Coin deposite = new Coin();

	public APIControllerTest() {
		controller = new APIController();

		// Setup Post request parameters for testing
		coin.fiveP = 5;
		coin.tenP = 5;
		coin.twentyP = 5;
		coin.fiftyP = 5;
		coin.one = 5;
		coin.two = 5;
	}

	@Test
	void getAvailableCoins_WhenCalledBeforeInitialise_ReturnsZeroCoins() {

		// Arrange
		APIResponse apiResponse = new APIResponse();
		Repository.coinsInMachine.clear();

		// Act
		Response jaxResponse = controller.getAvailableCoins();
		apiResponse = (APIResponse) jaxResponse.getEntity();

		// Assert
		assertEquals(0, apiResponse.coins.fiveP);
	}

	@Test
	void getAvailableCoins_OnSuccessfull_ReturnsCreatedStatus() {
		// Arrange
		APIResponse apiResponse = new APIResponse();

		// Act
		Response jaxResponse = controller.postInitialiseMachine(coin);

		// Assert
		assertEquals(201, jaxResponse.getStatus());
	}

	@Test
	void getAvailableCoins_WhenCalled_ReturnsInitialisedCoins() {
		// Arrange
		APIResponse apiResponse = new APIResponse();

		// Act
		Response jaxResponse = controller.postInitialiseMachine(coin);
		apiResponse = (APIResponse) jaxResponse.getEntity();

		// Assert
		assertEquals(5, apiResponse.coins.fiveP);
	}

	@Test
	void postInitialiseMachine_WhenCalled_ReturnsCoinsAvailable() {
		// Arrange
		APIResponse apiResponse = new APIResponse();

		// Act
		Response jaxResponse = controller.postInitialiseMachine(coin);
		apiResponse = (APIResponse) jaxResponse.getEntity();

		// Assert
		assertEquals(5, apiResponse.coins.fiveP);
		assertEquals(5, apiResponse.coins.tenP);
		assertEquals(5, apiResponse.coins.twentyP);
		assertEquals(5, apiResponse.coins.fiftyP);
		assertEquals(5, apiResponse.coins.one);
		assertEquals(5, apiResponse.coins.two);
	}

	@Test
	void postUserPayment_PaidAmountGreaterThanItemCost_ReturnsBadRequestResponse() {
		// Arrange
		deposite.itemCost = 200;
		deposite.paidAmount = 100;
		deposite.one = 1;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);

		// Assert
		assertEquals(406, jaxResponse.getStatus());
	}

	@Test
	void postUserPayment_PaidAmountMissing_ReturnsBadRequestResponse() {
		// Arrange
		deposite.itemCost = 120;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);

		// Assert
		assertEquals(406, jaxResponse.getStatus());
	}

	@Test
	void postUserPayment_ItemCostMissing_ReturnsBadRequestResponse() {
		// Arrange
		deposite.paidAmount = 200;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);

		// Assert
		assertEquals(400, jaxResponse.getStatus());
	}

	@Test
	void postUserPayment_WhenCalledWithCorrectParameters_ReturnsOKStatus() {
		// Arrange
		deposite.itemCost = 145;
		deposite.paidAmount = 200;
		deposite.two = 1;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);

		// Assert
		assertEquals(200, jaxResponse.getStatus());
	}

	@Test
	void postUserPayment_WhenCalledWithCorrectParameters_ReturnsUserChange() {
		// Arrange
		deposite.itemCost = 145;
		deposite.paidAmount = 200;
		deposite.two = 1;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);
		APIResponse apiResponse = new APIResponse();
		apiResponse = (APIResponse) jaxResponse.getEntity();

		// Assert
		assertEquals(1, apiResponse.coins.fiftyP);
		assertEquals(1, apiResponse.coins.fiveP);
		assertEquals(0, apiResponse.coins.tenP);
	}
	
	@Test
	void postUserPayment_WhenCalled_RemovedChangeCoinsFromMachine() {
		// Arrange
		deposite.itemCost = 145;
		deposite.paidAmount = 200;
		deposite.two = 1;
		controller.postInitialiseMachine(coin);

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);
		APIResponse apiResponse = new APIResponse();
		apiResponse = (APIResponse) jaxResponse.getEntity();
		TreeMap<Integer, Integer> repository = Repository.coinsInMachine;

		// Assert
		assertEquals(4, repository.get(50).intValue());
		assertEquals(4, repository.get(5).intValue());
		assertEquals(5, repository.get(10).intValue());
	}

}
