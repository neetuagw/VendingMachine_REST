package com.oracle.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import com.oracle.rest.crud.APIResponse;
import com.oracle.rest.crud.VendingMachineImp;
import com.oracle.rest.entity.Coin;
import com.sun.istack.NotNull;

@Path("api")
public class APIController {

	VendingMachineImp machine = new VendingMachineImp();
	APIResponse response = new APIResponse();;

	@GET
	@Produces("application/json")
	public Response getAvailableCoins() {

		response = machine.getCoinsInMachine();
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@Path("/initialise")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response postInitialiseMachine(Coin coin) {
		response = new APIResponse();
		response = machine.initialiseMachine(coin);
		return Response.status(Response.Status.CREATED).entity(response).build();
	}

	@Path("/deposite")
	@POST
	@Produces("application/json")
	public Response postUserPayment(Coin coin) {
		if (coin.itemCost > coin.paidAmount) {
			String error = "PaidAmount canot be less than Item's cost";
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(error).build();
		}

		if (coin.paidAmount <= 0 || coin.itemCost <= 0) {
			String error = "ItemCost and PaidAmount are mandatory and their values should be greater than 0";
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
		response = new APIResponse();
		response = machine.userDeposited(coin);
		return Response.status(Response.Status.OK).entity(response).build();
	}

}
