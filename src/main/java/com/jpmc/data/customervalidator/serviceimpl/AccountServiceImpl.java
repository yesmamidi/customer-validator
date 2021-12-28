package com.jpmc.data.customervalidator.serviceimpl;

import com.google.gson.Gson;
import com.jpmc.data.customervalidator.exception.BadRequestException;
import com.jpmc.data.customervalidator.model.AccountRequest;
import com.jpmc.data.customervalidator.model.AccountResponse;
import com.jpmc.data.customervalidator.model.Result;
import com.jpmc.data.customervalidator.service.AccountService;
import com.jpmc.data.customervalidator.utility.DataProviderConfigs;
import com.jpmc.data.customervalidator.utility.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Author Satya Mamidi
 */
@Component
public class AccountServiceImpl implements AccountService {
    Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    @Autowired
    DataProviderConfigs dataProviderConfigs;

    /**
     * @param accountRequest
     * @return ResponseEntity
     * This method takes user information which hold account number and providers
     * and calls providers
     */
    @Override
    public ResponseEntity<Result> validateCustomerInfo(AccountRequest accountRequest) {
        logger.info("In validateCustomerInfo method with request" + new Gson().toJson(accountRequest));
        Result result = new Result();
        List<AccountResponse> accountResponseList;
        RestTemplate restTemplate = new RestTemplate();
        /*This if condition checks the account number for null/empty and 0 condition*/
        if (!StringUtils.isNotBlank(accountRequest.getAccountNumber()) || accountRequest.getAccountNumber().equals("0")) {
            logger.info("Account number null or invalid");
            throw new BadRequestException(ErrorCode.INVALID_ACCOUNT_NUMBER.getCode(), "Invalid Account Number", ErrorCode.INVALID_ACCOUNT_NUMBER.getErrorMessage());
        } /*This else condition checks if providers are null or not given it calls all the providers*/ else {
            if (accountRequest.getProviders() == null || accountRequest.getProviders().isEmpty()) {
                accountResponseList = new ArrayList<>();
                dataProviderConfigs.getProviders()
                        .forEach((k, v) -> {
                            HttpEntity<String> request = new HttpEntity<>(accountRequest.getAccountNumber());
                            boolean response = /*restTemplate.postForObject(v, request, boolean.class)*/true;
                            AccountResponse accountResponse = new AccountResponse();
                            if (response) {
                                accountResponseList.add(new AccountResponse().builder().provider(k).isValid(true).build());
                            } else {
                                accountResponseList.add(new AccountResponse().builder().provider(k).isValid(false).build());
                            }
                        });
                result.setResult(accountResponseList);
                /*Since according to requirement we only have 2 providers i am throwing error if more than 2 providers comes from input payload*/
            } else if (accountRequest.getProviders().size() > 2) {
                throw new BadRequestException(ErrorCode.INVALID_DATA_PROVIDER.getCode(), "Invalid data provider", ErrorCode.INVALID_DATA_PROVIDER.getErrorMessage());
            } else {
                accountResponseList = new ArrayList<>();
                StringBuffer stringBuffer = new StringBuffer();
                /*For Future proof to dynamically add more providers no need to touch the code this will handle error conditions*/
                for (String provider : accountRequest.getProviders()) {
                    String providerValue = dataProviderConfigs.getProviders().get(provider);
                    if (providerValue == null) {
                        stringBuffer.append(" , ");
                        stringBuffer.append(provider);
                    }
                }
                if (!stringBuffer.isEmpty()) {
                    throw new BadRequestException(ErrorCode.INVALID_DATA_PROVIDER_BY_USER.getCode(), "Invalid data provider", ErrorCode.INVALID_DATA_PROVIDER_BY_USER.getErrorMessage() + stringBuffer);
                }
                for (String provider : accountRequest.getProviders()) {
                    HttpEntity<String> request = new HttpEntity<>(accountRequest.getAccountNumber());
                    String providerValue = dataProviderConfigs.getProviders().get(provider);
                    boolean response = /*restTemplate.postForObject(providerValue, request, boolean.class)*/true;
                    if (response) {
                        accountResponseList.add(new AccountResponse().builder().provider(provider).isValid(true).build());

                    } else {
                        accountResponseList.add(new AccountResponse().builder().provider(provider).isValid(false).build());
                    }
                }
                result.setResult(accountResponseList);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
