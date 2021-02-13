package com.robosoft.utils;

import static com.robosoft.constants.StringConstants.CONTENT_TYPE_JSON;
import static com.robosoft.constants.StringConstants.STR_API_DETAIL;
import static com.robosoft.constants.StringConstants.STR_ERROR;
import static com.robosoft.constants.StringConstants.STR_POST_REQUEST;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonNode;
import com.robosoft.responses.GenericErrorResponse;
import com.robosoft.service.ErrorLogServices;
import com.robosoft.service.commons.RRMBWSClient;

import play.Logger;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
public class RRMBClient{

	private static RRMBClient instance;

	public static RRMBClient getInstance() {
		if (instance == null)
			instance = new RRMBClient();
		return instance;
	}
	

    public RRMBClient() {
		super();
	}

//	@SuppressWarnings("deprecation")
//	public CompletionStage<JsonNode> sendPOSTRequest(String inUrl, JsonNode inRequest) {
//		CompletionStage<WSResponse> responsePromise = null;
//		CompletionStage<JsonNode> recoverPromise = null;
//		final Long timeTaken = System.currentTimeMillis();
//		try {
//			Logger.info(STR_POST_REQUEST + Thread.currentThread().getName());
//			WSRequest request = RRMBWSClient.ws.url(inUrl);
//			WSRequest complexRequest = request.setRequestTimeout(60000);
//			request.addHeader(USER_ID_KEY, appSettings.get(KARVY_API_USER_ID));
//			request.addHeader(CBS_PASS_KEY, appSettings.get(KARVY_API_PASS));
//			request.setContentType(CONTENT_TYPE_JSON);
//			responsePromise = complexRequest.post(inRequest);
//			LogUtil.log(STR_API_DETAIL + inUrl + " " + inRequest.toString());
//			recoverPromise = responsePromise.handle((result, error) -> {
//				
//				if (error != null) {
//					LogUtil.log("Post API: Failed, " + error.getMessage());
//					return CompletableFuture.completedFuture(new GenericErrorResponse(-1, -101, STR_ERROR).asJson());
//				} else {
//					LogUtil.log("response status ****** " + result.getStatus());
//					if(result.getBody().toString() != null && result.getStatus() == 200) {
//						
//						LogUtil.log("Post API: Success, " + result.getBody().toString());
//						return CompletableFuture.completedFuture(result).thenApply(WSResponse::asJson);
//					} else {
//						return CompletableFuture.completedFuture(new GenericErrorResponse(-1, -101, STR_ERROR).asJson());
//					}	
//				}
//			}).thenCompose(Function.identity());
//			return recoverPromise;
//		} catch (Exception e) {
//			ErrorLogServices.logException(e, "sendPOSTRequest");
//			LogUtil.log(STR_BANK_API_FAILED);
//			Logger.error("sendPOSTRequest" + e);
//			return null;
//		}
//	}

    @SuppressWarnings("deprecation")
	public CompletionStage<JsonNode> sendPOSTRequest(String inUrl, JsonNode inRequest) {
		CompletionStage<WSResponse> responsePromise = null;
		CompletionStage<JsonNode> recoverPromise = null;
		final Long timeTaken = System.currentTimeMillis();
		try {
			Logger.info(STR_POST_REQUEST + Thread.currentThread().getName());
			WSRequest request = RRMBWSClient.ws.url(inUrl);
			WSRequest complexRequest = request.setRequestTimeout(60000);
//			request.addHeader(USER_ID_KEY, appSettings.get(KARVY_API_USER_ID));
//			request.addHeader(CBS_PASS_KEY, appSettings.get(KARVY_API_PASS));
			request.setContentType(CONTENT_TYPE_JSON);
			responsePromise = complexRequest.post(inRequest);
			LogUtil.log(STR_API_DETAIL + inUrl + " " + inRequest.toString());
			recoverPromise = responsePromise.handle((result, error) -> {
				
				if (error != null) {
					LogUtil.log("Post API: Failed, " + error.getMessage());
					return CompletableFuture.completedFuture(new GenericErrorResponse(-1, -101, STR_ERROR).asJson());
				} else {
					LogUtil.log("response status ****** " + result.getStatus());
					if(result.getBody().toString() != null && result.getStatus() == 200) {
						
						LogUtil.log("Post API: Success, " + result.getBody().toString());
						return CompletableFuture.completedFuture(result).thenApply(WSResponse::asJson);
					} else {
						return CompletableFuture.completedFuture(new GenericErrorResponse(-1, -101, STR_ERROR).asJson());
					}	
				}
			}).thenCompose(Function.identity());
			return recoverPromise;
		} catch (Exception e) {
			ErrorLogServices.logException(e, "sendPOSTRequest");
//			LogUtil.log(STR_BANK_API_FAILED);
			Logger.error("sendPOSTRequest" + e);
			return null;
		}
	}


}
