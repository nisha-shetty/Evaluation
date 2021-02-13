package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.robosoft.controller.RESTController;
import play.libs.ws.WSClient;
import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;

import com.robosoft.service.commons.RRMBWSClient;

public class SecurityKeyController extends RESTController {

	private final ExecutionContextExecutor exec;

	@Inject
	public SecurityKeyController(ExecutionContextExecutor exec, WSClient ws) {
		this.exec = exec;
		RRMBWSClient.ws = ws;
	}
	
	public CompletionStage<Result> fetchSecurityKey(){
		return callRouteRequestForAsync(exec);
	}

}
