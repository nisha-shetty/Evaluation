package com.robosoft.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.robosoft.dto.ResponseData;
import com.robosoft.dto.ServiceResult;
import com.robosoft.dto.requestInputs.user.UserReqInput;
import com.robosoft.models.Service;
import com.robosoft.service.ErrorLogServices;
import com.robosoft.service.FileUploadServices;
import com.robosoft.service.UserServices;
import com.robosoft.utils.LogUtil;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.robosoft.constants.ErrorResponseCodes.INVALID_INPUTS_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;
import static com.robosoft.constants.Messages.VALIDATION_ERROR_JSON_PARSING;
import static com.robosoft.constants.ResponseConstants.STATUS_INTERNAL_SERVER_ERROR;
import static com.robosoft.constants.ResponseConstants.STATUS_OK;

public class AddUserCommand extends BaseCommand implements Command{

    private CommandParams mParams;
    JsonNode result;
    CompletionStage<JsonNode> promiseResult;
    Service mService;
    Integer mStatusCode;
    String mStatusMsg;
    String AESEncKey;
    String guid;
    UserReqInput mRequestInput;
    String fileName;
    String fileNameAfterReplace;
    String contentType;
    File file = null;

    public AddUserCommand(Service service, CommandParams params) {
        mParams = params;
        mService = service;
    }

    @Override
    public boolean validate() {
        boolean isValid = false;
        try {
           Http.MultipartFormData<?> body = mParams.getRequest().body().asMultipartFormData();
            Http.MultipartFormData.FilePart<?> image = body.getFile("file");

            if (image != null) {
                fileName = image.getFilename();
                fileNameAfterReplace = fileName.replaceAll(" ", "_");
                LogUtil.log("file name is ****************   >>>>>>>>>>  "+fileNameAfterReplace);
                contentType = image.getContentType();
                LogUtil.log("content type is ****************   >>>>>>>>>>  "+contentType);
                file = (File) image.getFile();
                LogUtil.log("file is ****************   >>>>>>>>>>  "+file);
                isValid = FileUploadServices.getInstance().isValidFile(
                        contentType,file);
            }
//            mRequestInput = new ObjectMapper().treeToValue(mParams.getRequest().body().asJson(), UserReqInput.class);
//            isValid = validate(mService.requestFields, mRequestInput);

        } catch (Exception e) {
            Logger.info(VALIDATION_ERROR_JSON_PARSING + e.getMessage());
        } finally {
            if (!isValid) {
                mStatusCode = INVALID_INPUTS_STCODE;
            }
        }
        return isValid;
    }

    @Override
    public int execute() {
        try {
            promiseResult = CompletableFuture.supplyAsync(() -> {

                ServiceResult serviceResult = UserServices.getInstance().addUserDetails(mRequestInput,file);

                ResponseData responseData = prepareJsonReponseEncrypted(serviceResult, guid, isAPIEncryptionEnabled(), AESEncKey, getReferenceNumber());
                JsonNode result = responseData.getResult();
                return result;
            });
            return STATUS_OK;

        } catch (Exception e) {
            mStatusCode = SERVER_ERROR_STCODE;
            ErrorLogServices.logException(e, "addUserDetails");
            return STATUS_INTERNAL_SERVER_ERROR;
        }

    }

    @Override
    public JsonNode getResult() {
        if (result == null && promiseResult == null) {
            result = prepareJsonReponseEncrypted(mStatusCode, mStatusMsg, isAPIEncryptionEnabled(), AESEncKey);
        }
        return result;
    }

    @Override
    public CompletionStage<JsonNode> getPromiseResult() {
        return promiseResult;
    }


    @Override
    public boolean authenticate() {

        return true;
    }



}
