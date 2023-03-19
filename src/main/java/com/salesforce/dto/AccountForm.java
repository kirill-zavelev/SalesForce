package com.salesforce.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
