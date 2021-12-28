package com.jpmc.data.customervalidator.controller;

import com.google.gson.Gson;
import com.jpmc.data.customervalidator.model.AccountRequest;
import com.jpmc.data.customervalidator.model.Result;
import com.jpmc.data.customervalidator.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


/**
 * Author Satya Mamidi
 */
@RestController
@RequestMapping("v1/customer/info")
public class AccountValidationController {
    @Autowired
    Gson gson;

    @Autowired
    AccountService accountService;

    Logger logger = Logger.getLogger(AccountValidationController.class.getName());

    /**
     * @param accountRequest
     * @return ResponseEntity
     * This is the controller method which takes user input
     */
    @PostMapping(value = "/validateInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> validateUserInfo(@RequestBody AccountRequest accountRequest) {
        //logger.info("In validateUserInfo method with request " + gson.toJson(accountRequest));
        return accountService.validateCustomerInfo(accountRequest);
    }

}
