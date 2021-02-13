package com.robosoft;


import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Provider;

import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

	@Inject
	public ErrorHandler(Config config, Environment environment, OptionalSourceMapper sourceMapper,
			Provider<Router> routes) {
		super(config, environment, sourceMapper, routes);
	}
	
	protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
		return CompletableFuture.completedFuture(Results.internalServerError(getResponseData("A server error occurred: " + exception.getMessage())));
	}
	

	protected CompletionStage<Result> onForbidden(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden(getResponseData("Access denied.")));
	}

	protected CompletionStage<Result> onNotFound(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden(getResponseData("Access denied.")));
	}

	protected CompletionStage<Result> onServerError(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden(getResponseData("A server error occurred.")));
	}

	protected CompletionStage<Result> onBadRequest(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden(getResponseData("Bad Request."+message)));
	}

	protected CompletionStage<Result> onClientError(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden(getResponseData("Client Error.")));
	}

	protected CompletionStage<Result> onDevServerError(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden(getResponseData("A server error occurred.")));
	}
	

	private JsonNode getResponseData(String messsage) {
		ObjectNode result = Json.newObject();
		result.put("status", SERVER_ERROR_STCODE);
		result.put("message", messsage);
		return result;
	}
}
