package com.robosoft.commands;

import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.JsonNode;

public interface Command {
	public boolean validate();
	public int execute();
	public JsonNode getResult();
	default CompletionStage<byte[]> getResultByteChunks() {
		return null;
	}
	default CompletionStage<JsonNode> getPromiseResult() {
		return null;
	}

	default CompletionStage<byte[]> getBytePromiseResult() {
		return null;
	}
	default boolean authenticate() {
		return true;
	}
	
	
}
