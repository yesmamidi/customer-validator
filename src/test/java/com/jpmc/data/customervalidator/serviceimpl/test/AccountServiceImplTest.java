package com.jpmc.data.customervalidator.serviceimpl.test;

import com.jpmc.data.customervalidator.exception.BadRequestException;
import com.jpmc.data.customervalidator.model.AccountRequest;
import com.jpmc.data.customervalidator.serviceimpl.AccountServiceImpl;
import com.jpmc.data.customervalidator.utility.DataProviderConfigs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "dev")
@TestPropertySource("classpath:application.properties")
public class AccountServiceImplTest {


    @InjectMocks
    @Spy
    AccountServiceImpl accountService;
    @Mock
    RestTemplate restTemplate;
    @Mock
    HttpEntity httpEntity;
    @Mock
    private DataProviderConfigs dataProviderConfigs;

    @Test
    public void testValidateCustomerInfoWithEmptyProviders() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber("1819");
        accountService.validateCustomerInfo(accountRequest);
    }

    @Test(expected = BadRequestException.class)
    public void testValidateCustomerInfoInvalidAccountNumber() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber("0");
        accountService.validateCustomerInfo(accountRequest);
    }

    @Test(expected = BadRequestException.class)
    public void testUserInputProviders() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber("1819");
        List<String> list = new ArrayList<>();
        list.add("provider1");
        list.add("provider2");
        accountRequest.setProviders(list);
        accountService.validateCustomerInfo(accountRequest);
    }

}
