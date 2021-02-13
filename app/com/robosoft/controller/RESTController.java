package com.robosoft.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.robosoft.commands.CommandParams;
import com.robosoft.constants.ResponseConstants;
import com.robosoft.dto.requestInputs.GenericInputPathParam;
import com.robosoft.responses.GenericErrorResponse;
import com.robosoft.router.RequestRouter;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.ExecutionContextExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;

public class RESTController extends Controller {

	private Result sendResponse(JsonNode jsonNode, RequestRouter requestRouter) {
		if (requestRouter.getStatus() == ResponseConstants.STATUS_OK) {
			return ok(jsonNode);
		} else {
			return getFailureResult(jsonNode, requestRouter.getStatus());
		}
	}

	private <T> Result sendResponseInBytes(T jsonNode, RequestRouter requestRouter) {
		if (jsonNode == null)
			return internalServerError();
		if (requestRouter.getStatus() == ResponseConstants.STATUS_OK) {
			if (jsonNode.getClass() == ObjectNode.class)
				return ok((ObjectNode) jsonNode);
			else if (jsonNode.getClass() == ArrayNode.class)
				return ok((ArrayNode) jsonNode);
			else
				return getFailureResult((JsonNode) jsonNode, requestRouter.getStatus());
		} else {
			return getFailureResult((JsonNode) jsonNode, requestRouter.getStatus());
		}
	}

	@SuppressWarnings("unchecked")
	private <T> CompletionStage<Result> sendPromiseResponse(T result, ExecutionContextExecutor exec, RequestRouter requestRouter) {
		if (result == null) {
			CompletionStage<JsonNode> tempResult = CompletableFuture.completedFuture(new GenericErrorResponse(-1, SERVER_ERROR_STCODE, "Error while processing").asJson());
			return tempResult.thenApply(Results::internalServerError);
		}
		if (requestRouter.getStatus() == ResponseConstants.STATUS_OK) {
			if (result.getClass() == ObjectNode.class) {
				return CompletableFuture.completedFuture((ObjectNode) result).thenApplyAsync(Results::ok, exec);
			} else if (result.getClass() == ArrayNode.class) {
				return CompletableFuture.completedFuture((ArrayNode) result).thenApplyAsync(Results::ok, exec);
			} else if (result.getClass() == CompletableFuture.class) {
				if (!requestRouter.isByteArray()) {
					return ((CompletionStage<JsonNode>) result).thenApplyAsync(Results::ok, exec);
				} else {
					response().setHeader("Content-Type", "image/png; image/jpeg;");
					return ((CompletionStage<byte[]>) result).thenApplyAsync(Results::ok, exec);
				}
			} else {
				return ((CompletionStage<JsonNode>) result).thenApplyAsync(Results::ok, exec);
			}
		} else {
			if (result.getClass() == ObjectNode.class) {
				CompletionStage<JsonNode> tempResult = CompletableFuture.completedFuture((ObjectNode) result);
				return getFailureResultAsync(tempResult, requestRouter.getStatus());
			}
			return getFailureResultAsync(((CompletionStage<JsonNode>) result), requestRouter.getStatus());
		}
	}
	
	
	private Result getFailureResult(JsonNode jsonNode, int status) {
		switch (status) {
			case ResponseConstants.STATUS_BAD_REQUEST :
				return badRequest(jsonNode);

			case ResponseConstants.STATUS_INTERNAL_SERVER_ERROR :
				return internalServerError(jsonNode);

			case ResponseConstants.STATUS_UNAUTHORIZED :
				return forbidden(jsonNode);

			case ResponseConstants.FAILURE_STATUS :
				return internalServerError(jsonNode);
			default :
				break;
		}
		return internalServerError(jsonNode);
	}

	private CompletionStage<Result> getFailureResultAsync(CompletionStage<JsonNode> result, int status) {
		switch (status) {
			case ResponseConstants.STATUS_BAD_REQUEST :
				return result.thenApply(Results::badRequest);

			case ResponseConstants.STATUS_INTERNAL_SERVER_ERROR :
				return result.thenApply(Results::internalServerError);

			case ResponseConstants.STATUS_UNAUTHORIZED :
				return result.thenApply(Results::forbidden);

			case ResponseConstants.FAILURE_STATUS :
				return result.thenApply(Results::internalServerError);
			default :
				break;
		}
		return result.thenApply(Results::internalServerError);
	}

	protected Result callRouteRequest() {
		CommandParams params = new CommandParams();
		params.setRequest(request());
		RequestRouter requestRouter = new RequestRouter();
		return sendResponse(requestRouter.routeRequest(params), requestRouter);
	}

	protected Result callRouteRequestForBytes() {
		CommandParams params = new CommandParams();
		params.setRequest(request());
		RequestRouter requestRouter = new RequestRouter();
		return sendResponseInBytes(requestRouter.routeRequest(params), requestRouter);
	}

	protected CompletionStage<Result> callRouteRequestForAsync(ExecutionContextExecutor exec) {
		CommandParams params = new CommandParams();
		params.setRequest(request());
		RequestRouter requestRouter = new RequestRouter();
		return sendPromiseResponse(requestRouter.routeRequest(params), exec, requestRouter);
	}
	
	protected CompletionStage<Result> callRouteRequestForAsyncARN(ExecutionContextExecutor exec) {
		CommandParams params = new CommandParams();
		params.setRequest(request());
		RequestRouter requestRouter = new RequestRouter();
		return sendPromiseResponse(requestRouter.routeRequest(params, null), exec, requestRouter);
	}
	
	protected CompletionStage<Result> callRouteRequestForAsync(ExecutionContextExecutor exec, GenericInputPathParam genReqParams) {
        CommandParams params = new CommandParams();
        params.setRequest(request());
        RequestRouter requestRouter = new RequestRouter(); 
        return sendPromiseResponse(requestRouter.routeRequest(params, genReqParams), exec, requestRouter);
    }
}
