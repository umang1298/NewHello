@VENDORPORTAL
Feature: Regression Test Scenaios - Vendor Portal

Scenario Outline: Reg No - <Regression case No> - Vendor Coordination of Benefits Validations on Vendor Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<providerusername>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user Logouts the session
Given user sets the "VendorId" key to value "<username>"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
#Then user navigates to the "Claims"
#Then user search the Claims for last one year
#Then user checks if claims search results are "Available"
#Then user validates the message for more than 500 results
#Then user takes "MemberID" from list of Claims 
Then user navigates to the "More"
And user clicks "Coordination of Benefits"
Then user searches COB informations for "MemberID"
Then user enters the COB information in the Add COB Information page if "COB Available"
Then user verifies the COB informations are displayed correctly if "COB Available"

@VENDORCOORDINATIONOFBENEFITS03 @DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username |providerusername | password |
| Vendor01 | 50005803idadmin |Automationpcp1 | Today1234 |

@VENDORCOORDINATIONOFBENEFITS02 @DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username |providerusername | password |
| Vendor01 | 50005803idadmin |Automationpcp1 | Today1234 |

Scenario Outline: Reg No - <Regression case No> - Vendor Code Editing Explanation Validations on Vendor Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "More"
And user clicks "Code Editing Explanation"
Then user verifies terms and conditions are opened in new tab

@VENDORCODEEDITINGEXPLANATION03 @DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username | password |
| Vendor02 | 50005803idadmin | Today1234 |
@VENDORCODEEDITINGEXPLANATION02 @DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username | password |
| Vendor02 | 50005803idadmin | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Vendor Benefit Admin Manual Validations on Vendor Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "More"
And user clicks "Benefit Admin Manual"
Then user searches Policy by text and validates search results are displayed
Then user searches Policy by Policy Index and Validates the search results are displayed 
Then user searches Policy by Categorical Index and validates the Searched results are displayed
Then user searches Policy by Recent Changes and validate searched Results are displayed

@VENDORBENEFITADMINMANUAL03 @DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username | password |
| Vendor03 | 50005803idadmin | Today1234 |

@VENDORBENEFITADMINMANUAL02 @DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username | password |
| Vendor03 | 50005803idadmin | Today1234 |

Scenario Outline: Reg No - <Regression case No> - Vendor Referral Authorizations Records Validations on Vendor Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "More"
And user clicks "Referral Authorization Records"
Then user selects the Status from the dropdown and searches Referrals for one year
Then user validates Referral search results are displayed
Then user clicks on "Referral Number" and stores "PatientName"
And user validates the Referral details is displayed correctly for "PatientName"

@VENDORREFERRALAUTHORIZATIONSRECORDS03 @DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username | password |
| Vendor04 | 50005803idadmin | Today1234 |


@VENDORREFERRALAUTHORIZATIONSRECORDS02 @DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username | password |
| Vendor04 | 50005803idadmin | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations on Vendor Portal for <User Type>- Userid- <Vendorusername>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<Providerusername>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user Logouts the session
#Then user clears the username and password field
Given user sets the "VendorId" key to value "<Vendorusername>"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "Member Eligibility"
Then user enters "MemberID" and Clicks on submit
Then user gets "SubscriberID" from "MemberID"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml | 
Then user does the validation for "MemberID" based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"
Then user clicks on Member Name Link
Then user does the validation on Member Eligibility Details page based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"
Then user navigates to the "Member Eligibility"
Then user navigates to the "Member Eligibility"
Then user navigates to the "Member Eligibility"
Then user enters "SubscriberID" and Clicks on submit
Then user validates results are successfully displayed for all the dependants based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"


@VENDORMEMBERELIGIBILITY03  @DAILYREGRESSIONRUNTST03 @VENDOR03
Examples:
| Regression case No | Vendorusername | Providerusername  | password |Endpoint|
| Vendor05 | 50005803idadmin | Automationpcp1 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|


@VENDORMEMBERELIGIBILITY02 @DAILYREGRESSIONRUNTST02 @VENDOR02
Examples:
| Regression case No | Vendorusername | Providerusername  | password |Endpoint|
| Vendor05 | 50005803idadmin | Automationpcp1 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|



Scenario Outline: Reg No - <Regression case No> - Vendor Remittance Advice Validations on Vendor Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "Remittance Advice"
Then user selects Reports for radio button "byPaymentDate"
Then user searches RA for one year
Then user validates results for RA search
Then user navigates to the "Remittance Advice"
Then user selects Reports for radio button "byDateOfService"
Then user searches RA for one year
Then user validates results for RA search
Then user takes "VendorId" and "Payment Number" from search results
Then user navigates to the "Remittance Advice"
Then user searches RA with "VendorId"
Then user searches RA for one year 
 #for vendor Id
Then user validates results for RA search
Then user navigates to the "Remittance Advice"
Then user searches RA with "Payment Number"
 #for Payment Number
Then user validates results for RA search

@VENDORREMITTANCEADVICE03 @DAILYREGRESSIONRUNTST03 @VENDOR03
Examples:
| Regression case No | username | password |
| Vendor06 | 50005803idadmin | Today1234 |

@VENDORREMITTANCEADVICE02 @DAILYREGRESSIONRUNTST02 @VENDOR02
Examples:
| Regression case No | username | password |
| Vendor06 | 50005803idadmin | Today1234 |

Scenario Outline: Reg No - <Regression case No> - Vendor Claims Validations on vendor Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "Claims"
Then user search the Claims for last one year
Then user validates the message for more than 500 results
Then user clicks on "ClaimNumber" with "Completed" status
And validates whether it takes user to Claim Details Page of "ClaimNumber"
Then user Clicks on "View Remittance Advice" Link based on "ClaimStatus"
Then user validates print option and Remittance Advice for "ClaimNumber" is loaded Correctly based on "ClaimStatus"
Then user checks the Line Item with Request Appeal status "Appeal" and Clicks on Appeal Button if there is line available "forappeal"
Then user selects the option for Appeal and clicks Next Button if there is line available "forappeal"
Then user enters Appeal Notes and clicks Next Button if there is line available "forappeal"
Then user Submits the Appeal if there is line available "forappeal"
Then user verifies whether the case ID is generated and clicks No Button if there is line available "forappeal"

@VENDORCLAIMS03 @DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username | password |Endpoint|
| Vendor07 | 50005803idadmin  | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@VENDORCLAIMS02 @DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username | password |Endpoint|
| Vendor07 | 50005803idadmin | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Vendor Claims Search Functionality Validations on Vendor Portal for <User Type>- Userid- <Vendorusername>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<Providerusername>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user Logouts the session
Then user clears the username and password field
Given user sets the "VendorId" key to value "<Vendorusername>"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "Claims"
#Then user verifies provider information is displayed
Then user search the Claims for last one year
Then user validates the message for more than 500 results
Then user takes "ClaimNumber" from search results and sets value for "PartialClaimNumber" and "FullClaimNumber"
Then user gets the Patient Account Number from search results and set the value to "Patient Account Number" 
Then user navigates to the "Claims"
Then user navigates to the "Claims"
Then user searches claims with "MemberID" and validates results are displayed Successfully
Then user searches "Claim Number" with "PartialClaimNumber" and validates results are displayed Successfully
Then user searches "Claim Number" with "FullClaimNumber" and validates results are displayed Successfully
Then user searches "Claim Number" with "IncorrectClaimNumber" and validates results are displayed Successfully
Then user searches with "Patient Account Number" and validates results are displayed Successfully

@VENDORCLAIMSSEARCH03 @DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | Vendorusername | Providerusername  | password |Endpoint|
| Vendor08 | 50005803idadmin | Automationpcp1 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@VENDORCLAIMSSEARCH02 @DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples: 
| Regression case No | Vendorusername | Providerusername  | password |Endpoint|
| Vendor08 | 50005803idadmin | Automationpcp1 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Vendor Midwest Claims Search Functionality Validations (HAP EMPOWERED) on Vendor Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to the "Claims"
Then user clicks on HAP Empowered Historical Information button
Then user enters "<BillingProviderNPI>" on HAP empowered page
Then user clicks search on HAP empowered page
Then user validates search "Result Available" are displayed for MW claims
Then user clicks on "ClaimNumber" in the MW claims search results if "Result Available"
And validates whether it takes user to Vendor MW Claim Details Page of "ClaimNumber" if "Result Available"

@VENDORMWCLAIMSSEARCH03 @DAILYREGRESSIONRUNTST03 @VENDOR03
Examples:
| Regression case No | username  | password | BillingProviderNPI | Endpoint|
| Vendor09 | 50005803idadmin  | Today1234 | 1134144801 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#1023114634idadmin
@VENDORMWCLAIMSSEARCH02 @DAILYREGRESSIONRUNTST02 @VENDOR02
Examples:
| Regression case No | username  | password |Endpoint|
| Vendor09 | 50005803idadmin | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Vendor Update Profile functionality  Validations  for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to "Update Profile" 
Then user validates the Change Password Functionality in the IPA Portal
Then user navigates to "Update Profile" 
Then user changes password to the old password 


@VENDORUPDATEPROFILEF02	@DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username | password |
| Vendor10 | 50005803idadmin | Today1234 |

@VENDORUPDATEPROFILE03	@DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username | password |
| Vendor10 | 50005803idadmin | Today1234 |

#Scenario Outline: Reg No - <Regression case No> - Vendor Midwest Trusted Health Plan Functionality Validations on Vendor Portal for <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "VendorId" key to value "<username>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal HAP application with user id "VendorId" and password "<password>"
#Then user navigates to the "Claims"
#Then user clicks on For Trusted HP button and validates user gets navigates to the Trusted Health Plan Home page
#
#
#@VENDORTRUSTEDHPCLAIMSSEARCH03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No| username  | password | BillingProviderNPI | Endpoint|
#| Vendor11 | 00166a_idadmin | Today1234 | 1134144801 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

#@VENDORTRUSTEDHPCLAIMSSEARCH02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username  | password |Endpoint|
#| Vendor11 | 00166a_idadmin | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Vendor Manage Users Validations on Vendor Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "VendorId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "VendorId" and password "<password>"
Then user navigates to "Manage Users"
Then user clicks on the View all Users link 
Then user selects the First user id and clicks on Update user link 
Then user updates the "information" and clicks on the Update button
Then user navigates to "Manage Users"
Then user clicks on the View all Users link
Then user validates "information" is updated successfully


@VENDORMANAGEUSER02	@DAILYREGRESSIONRUNTST02 #@VENDOR02
Examples:
| Regression case No | username | password |
| Vendor11 | 50005803idadmin | Today1234 |

@VENDORMANAGEUSER03	@DAILYREGRESSIONRUNTST03 #@VENDOR03
Examples:
| Regression case No | username | password |
| Vendor11 | 50005803idadmin | Today1234 |