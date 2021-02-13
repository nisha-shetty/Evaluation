package com.robosoft;
import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class ActionCreator implements play.http.ActionCreator {
    @Override
    public Action<Void> createAction(Http.Request request, Method actionMethod) {
        return new Action.Simple() {
            @Override
            public CompletionStage<Result> call(Http.Context ctx) {
            	Logger.info("----Before Request----");
                return delegate.call(ctx);
            }
        };
    }
}