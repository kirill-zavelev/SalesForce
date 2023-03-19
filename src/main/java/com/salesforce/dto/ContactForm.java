package com.salesforce.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactForm {

    private String firstName;
    private String mobile;
    private String accountName;
    private String email;
    private String phone;
    private String title;
    private String mailingStreet;
    private String fax;
    private String department;
    private String otherPhone;
    private String leadSource;
}
