package com.robosoft.controller;

import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class AddUserController extends RESTController {

    private final ExecutionContextExecutor exec;

    @Inject
    public AddUserController(ExecutionContextExecutor exec) {
        this.exec = exec;
    }

    public CompletionStage<Result> addUser() {
        return callRouteRequestForAsync(exec);
    }

    public CompletionStage<Result> addfavourite() {
        return callRouteRequestForAsync(exec);
    }

}
