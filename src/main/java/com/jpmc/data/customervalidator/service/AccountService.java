package com.jpmc.data.customervalidator.service;

import com.jpmc.data.customervalidator.model.AccountRequest;
import com.jpmc.data.customervalidator.model.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public ResponseEntity<Result> validateCustomerInfo(AccountRequest accountRequest);
}
