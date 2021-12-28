package com.jpmc.data.customervalidator.utility;

public enum ErrorCode {
    INVALID_ACCOUNT_NUMBER("CAV101", "Entered account might be invalid or null"),
    INVALID_DATA_PROVIDER("CAV102", "Either no data providers are entered or Only 2 data providers needs to be selected"),
    INVALID_DATA_PROVIDER_BY_USER("CAV103", "Please check invalid data provider");


    private String code;
    private String errorMessage;

    ErrorCode(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

