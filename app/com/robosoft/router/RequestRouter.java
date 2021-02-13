package com.robosoft.router;

import static com.robosoft.constants.Messages.STR_RES;
import static com.robosoft.constants.ResponseConstants.FAILURE_STATUS;
import static com.robosoft.constants.ResponseConstants.STATUS_BAD_REQUEST;
import static com.robosoft.constants.ResponseConstants.STATUS_OK;

import java.util.concurrent.CompletionStage;

import com.robosoft.models.Service;
import play.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.robosoft.commands.Command;
import com.robosoft.commands.CommandParams;
import com.robosoft.dto.requestInputs.GenericInputPathParam;
import com.robosoft.utils.LogUtil;

public class RequestRouter {
	private int status;
	private boolean isByteArray;
	public RequestRouter() {

	}

	@SuppressWarnings("unchecked")
	public <T> T routeRequest(CommandParams params) {
		Service service = ServiceConfigReader.getInstance().getService(params.getPath());
		if (service == null) {
			status = FAILURE_STATUS;
			return null;
		}

		Command command = ServiceCreatorFactory.createService(service, params);
		try {
			if (command == null) {
				status = FAILURE_STATUS;
				return null;
			}			
			if (!command.authenticate()) {
				status = STATUS_OK;
				return (T) command.getResult();
			}
			if (!command.validate()) {
				status = STATUS_BAD_REQUEST;
				return (T) command.getResult();
			}else {
			status = command.execute();
			}
		} catch (Exception e) {
				e.printStackTrace();		
			} finally {
			if (command.getResult() != null)
				LogUtil.log(new StringBuilder(STR_RES).append(params.getRequest().uri()).append(" ").append(command.getResult().toString()).append("\n").toString());
		}
		if (command.getResult() == null && command.getResultByteChunks() != null) {
			isByteArray = true;
			return (T) command.getResultByteChunks();
		}
		if (command.getResult() == null && command.getBytePromiseResult() != null) {
			isByteArray = true;
			return (T) command.getBytePromiseResult();
		}
				
		Logger.info("req router-----result:"+command.getResult());
		Logger.info("req router-----async result:"+command.getPromiseResult());
		if (command.getResult() == null && command.getResultByteChunks() == null && command.getPromiseResult() != null) {
			
			CompletionStage<JsonNode> promiseResult = command.getPromiseResult().thenApply(result->{
				LogUtil.log(new StringBuilder(STR_RES).append(params.getRequest().uri()).append(" ").append(result).append("\n").toString());
				return result;
			});
			return (T) promiseResult;
		}
		return (T) command.getResult();
	}

	@SuppressWarnings("unchecked")
	public <T> T routeRequest(CommandParams params, GenericInputPathParam genReqParams) {
		Service service = ServiceConfigReader.getInstance().getService(params.getPath());
		if (service == null) {
			status = FAILURE_STATUS;
			return null;
		}

		Command command = ServiceCreatorFactory.createService(service, params);
		try {
			if (command == null) {
				status = FAILURE_STATUS;
				return null;
			}	
			if (!command.validate()) {
				status = STATUS_BAD_REQUEST;
				return (T) command.getResult();
			}
			if (!command.authenticate()) {
				///status = STATUS_UNAUTHORIZED;
				//System.out.println( "-----------" + command.getResult() + "***************");
				status = STATUS_OK;
				return (T) command.getResult();
			}
			else {
			status = command.execute();
			}
		} catch (Exception e) {
				e.printStackTrace();		
			} finally {
			if (command.getResult() != null)
				LogUtil.log(new StringBuilder(STR_RES).append(params.getRequest().uri()).append(" ").append(command.getResult().toString()).append("\n").toString());
		}
		if (command.getResult() == null && command.getResultByteChunks() != null) {
			return (T) command.getResultByteChunks();
		}
		if (command.getResult() == null && command.getBytePromiseResult() != null) {
			isByteArray = true;
			return (T) command.getBytePromiseResult();
		}
				
		Logger.info("req router-----result:"+command.getResult());
		Logger.info("req router-----async result:"+command.getPromiseResult());
		if (command.getResult() == null && command.getResultByteChunks() == null && command.getPromiseResult() != null) {
			
			CompletionStage<JsonNode> promiseResult = command.getPromiseResult().thenApply(result->{
				LogUtil.log(new StringBuilder(STR_RES).append(params.getRequest().uri()).append(" ").append(result).append("\n").toString());
				return result;
			});
			return (T) promiseResult;
		}
		return (T) command.getResult();
	}

	
	public int getStatus() {
		return status;
	}
	
	public boolean isByteArray() {
		return isByteArray;
	}
}
