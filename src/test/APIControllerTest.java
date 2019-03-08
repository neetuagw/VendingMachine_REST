package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.oracle.rest.APIController;
import com.oracle.rest.crud.APIResponse;
import com.oracle.rest.crud.Repository;
import com.oracle.rest.entity.Coin;

/*
 * The AAA (Arrange-Act-Assert) pattern
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
	void GetCoinsInMachine_WhenCalledBeforeInitialise_ReturnsZeroCoins() {

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
	void GetCoinsInMachine_OnSuccessfull_ReturnsCreatedStatus() {
		// Arrange
		APIResponse apiResponse = new APIResponse();

		// Act
		Response jaxResponse = controller.postInitialiseMachine(coin);

		// Assert
		assertEquals(201, jaxResponse.getStatus());
	}

	@Test
	void GetCoinsInMachine_WhenCalled_ReturnsInitialisedCoins() {
		// Arrange
		APIResponse apiResponse = new APIResponse();

		// Act
		Response jaxResponse = controller.postInitialiseMachine(coin);
		apiResponse = (APIResponse) jaxResponse.getEntity();

		// Assert
		assertEquals(5, apiResponse.coins.fiveP);
	}

	@Test
	void InitialiseMachine_WhenCalled_ReturnsCoinsAvailable() {
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
	void Deposite_PaidAmountGreaterThanItemCost_ReturnsBadRequestResponse() {
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
	void Deposite_PaidAmountMissing_ReturnsBadRequestResponse() {
		// Arrange
		deposite.itemCost = 120;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);

		// Assert
		assertEquals(400, jaxResponse.getStatus());
	}

	@Test
	void Deposite_ItemCostMissing_ReturnsBadRequestResponse() {
		// Arrange
		deposite.paidAmount = 200;

		// Act
		Response jaxResponse = controller.postUserPayment(deposite);

		// Assert
		assertEquals(400, jaxResponse.getStatus());
	}

	@Test
	void Deposite_WhenCalledWithCorrectParameters_ReturnsOKStatus() {
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
	void Deposite_WhenCalledWithCorrectParameters_ReturnsUserChange() {
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

}
