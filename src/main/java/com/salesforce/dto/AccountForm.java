package com.salesforce.dto;

import com.salesforce.wrapper.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountForm {

    private String accountName;
    private String phone;
    private String fax;
    private String website;
    private String employees;
    private String billingStreet;
    private String shippingStreet;
    private String type;
    private String industry;
}
