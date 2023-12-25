@PROVIDERPORTAL
Feature: Regression Test Scenaios - Provider Portal


Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations on Provider Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
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
Then user waits for 30000 ms
Then user validates results are successfully displayed for all the dependants based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"

@MEMBERELIGIBILITY03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |Endpoint|
| Provider01 |Automationpcp1 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#100000322442idpcp

@MEMBERELIGIBILITY02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |Endpoint|
| Provider01 | Automationpcp1  | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations (Display Contract History) on Provider Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user navigates to the "Member Eligibility"
Then user enters "MemberID" and checks Display Contract history and Clicks on submit
Then user gets "SubscriberID" and "MemberSuffix" from "MemberID"
Given user sets the SOAP request xml for MemberService Get Member COB and Eligibility and Provider by Subscriber id:
|SubscriberID| MemberSuffix | Path to write XML |
|SubscriberID| MemberSuffix | XMLs/GetMemberCOBandEligAndProviderBySubscriberId.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetMemberCOBandEligAndProviderBySubscriberId.xml | XMLs/GetMemberCOBandEligAndProviderBySubscriberIdResponse.xml | 
Then user does the Member Eligibilty Contract History validation for "MemberID" based on the response XML "/XMLs/GetMemberCOBandEligAndProviderBySubscriberIdResponse.xml"
Then user clicks on Member Name Link
Then user does the Contract History validation on Member Eligibility Details page based on the response XML "/XMLs/GetMemberCOBandEligAndProviderBySubscriberIdResponse.xml"

@MEMBERELIGIBILITYCONTRACTHISTORY03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |Endpoint|
| Provider01 | AutomationPCP1 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MEMBERELIGIBILITYCONTRACTHISTORY02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |Endpoint|
| Provider01 | Automationpcp1  | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|



Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations for Multiple MemberIDs on Provider Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets three MemberIDs from search results:
| Mem1 | Mem2 | Mem3 |
| MemberID1 | MemberID2 | MemberID3 |
Then user navigates to the "Member Eligibility"
Then user enters "MemberID1" and "MemberID2" and "MemberID3" and clicks on submit
Then user verifies the eligibility results are displayed for "MemberID1" and "MemberID2" and "MemberID3"

@MEMBERELIGIBILITYWITHMULTIPLEMEMBERS03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |Endpoint|
| Provider02 | Automationpcp1 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MEMBERELIGIBILITYWITHMULTIPLEMEMBERS02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |Endpoint|
| Provider02 | Automationpcp1 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Provider Claims Validations on Provider Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "Claims"
Then user verifies provider information is displayed 
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

@PROVIDERCLAIMS03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |Endpoint|
| Provider03 | Automationpcp1 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 1992725352_idadmin | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@PROVIDERCLAIMS02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |Endpoint|
| Provider03 | Automationpcp1 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#| 1992725352_idadmin | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Provider Claims Search Functionality Validations on Provider Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user navigates to the "Claims"
Then user verifies provider information is displayed
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




@PROVIDERCLAIMSSEARCH03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider04 | Automationpcp1| Today1234 |

@PROVIDERCLAIMSSEARCH02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider04 | Automationpcp1 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Provider HCC and HEDIS Gaps Functionality Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user navigates to the "HCC & HEDIS Program"
Then user enters the "MemberID" in text box and clicks search button
Then user verifies HHC & HEDIS Program result is displayed successfully for the "MemberID"
Then user verifies print function on HCC & HEDIS Program results page for the "MemberID"
#Then user selects Medical record attachment for "HCC" and uploads the document
#Then user selects Medical record attachment for "HEDIS" and uploads the document


@PROVIDERHCCANDHEDIS03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider05 | Automationpcp1 | Today1234 |


@PROVIDERHCCANDHEDIS02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider05 | Automationpcp1 | Today1234 |



Scenario Outline: Reg No - <Regression case No> - Provider HCC and HEDIS Gaps Panel Management Report Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Panel Managaement Report Link
Then user Selects Provider and "hedis" and Clicks on Submit and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Panel Managaement Report Link
Then user Selects Provider and "hcc" and Clicks on Submit and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Panel Managaement Report Link
Then user Selects Provider and "both" and Clicks on Submit and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Panel Managaement Report Link
Then user Selects Provider and "hedis" and Clicks on Download and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Panel Managaement Report Link
Then user Selects Provider and "hcc" and Clicks on Download and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Panel Managaement Report Link
Then user Selects Provider and "both" and Clicks on Download and Validates Expected results


@PANELMANAGEMENTREPORT03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider06 | Automationpcp1 | Today1234 |

@PANELMANAGEMENTREPORT02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider06 | Automationpcp1 | Today1234 |

Scenario Outline: Reg No - <Regression case No> - Provider Authorizations Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "Authorizations" and validates the CareAffiliate page is displayed

@AUTHORIZATIONS03  @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider07 | Automationpcp1 | Today1234 |

@AUTHORIZATIONS02  @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider07 | Automationpcp1 | Today1234 |

Scenario Outline: Reg No - <Regression case No> - Provider Referral Authorizations Records Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "More"
And user clicks "Referral Authorization Records"
Then user selects the Status from the dropdown and searches Referrals for one year
Then user validates Referral search results are displayed
Then user clicks on "Referral Number" and stores "PatientName"
And user validates the Referral details is displayed correctly for "PatientName"

@REFERRALAUTHORIZATIONSRECORDS03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider08 | Automationpcp1 | Today1234 |


@REFERRALAUTHORIZATIONSRECORDS02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider08 | Automationpcp1 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Provider Code Editing Explanation Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "More"
And user clicks "Code Editing Explanation"
Then user verifies terms and conditions are opened in new tab

@CODEEDITINGEXPLANATION03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider09 | Automationpcp1 | Today1234 |

@CODEEDITINGEXPLANATION02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider09 | Automationpcp1 | Today1234 |



Scenario Outline: Reg No - <Regression case No> - Provider Coordination of Benefits Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user selects provider from the drop down and Clicks Search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user navigates to the "More"
And user clicks "Coordination of Benefits"
Then user searches COB informations for "MemberID"
Then user enters the COB information in the Add COB Information page if "COB Available"
Then user verifies the COB informations are displayed correctly if "COB Available"


@COORDINATIONOFBENEFITS03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider10 | Automationpcp1 | Today1234 |

@COORDINATIONOFBENEFITS02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider10 | Automationpcp1 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Provider Benefit Admin Manual Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "More"
And user clicks "Benefit Admin Manual"
Then user searches Policy by text and validates search results are displayed
Then user searches Policy by Policy Index and Validates the search results are displayed 
Then user searches Policy by Categorical Index and validates the Searched results are displayed
Then user searches Policy by Recent Changes and validate searched Results are displayed

@BENEFITADMINMANUAL03 @DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider11 | Automationpcp1 | Today1234 |

@BENEFITADMINMANUAL02 @DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider11 | Automationpcp1 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Provider Static Contents Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user clicks on Quick Links option in the Portal
Then user verifies "Authorizations - CareAffiliate Help" link is present in the Links Panel
Then user verifies "Billing Manual" link is present in the Links Panel
Then user verifies "Clinical Information Forms" link is present in the Links Panel
Then user verifies "Fee Schedules" link is present in the Links Panel
Then user verifies "HCC & HEDIS Training Materials" link is present in the Links Panel
Then user verifies "HCM On-Call Schedule" link is present in the Links Panel
Then user verifies "ID Admin/Manage Users Help" link is present in the Links Panel
Then user verifies "Important Contact Information" link is present in the Links Panel
Then user verifies "Medication Request Forms" link is present in the Links Panel
Then user verifies "naviHealth provider resources" link is present in the Links Panel
Then user verifies "P4P for PCPs" link is present in the Links Panel
Then user verifies "Procedure Reference Lists" link is present in the Links Panel
Then user verifies "Provider Newsroom" link is present in the Links Panel
Then user verifies "Payment Integrity Department" link is present in the Links Panel
Then user Close the Links Panel
Then user navigates to the "Member Eligibility"
Then user Clicks on Related Links option in the Portal
Then user verifies "Contracts, Benefit Guides and Riders" link is present in the Links Panel
#Then user verifies "Coordination of Benefits" link is present in the Links Panel
Then user verifies "Medicaid and MI Health Link Member Benefits" link is present in the Links Panel
Then user verifies "Member Eligibility Help Guide" link is present in the Links Panel
Then user Close the Related Links Panel
Then user navigates to the "Claims"
Then user Clicks on Related Links option in the Portal
Then user verifies "835 Companion Guide" link is present in the Links Panel
Then user verifies "Cigna Claims Change" link is present in the Links Panel
Then user verifies "Negative Balance on the Remittance Advice and Balance Credits" link is present in the Links Panel
Then user verifies "Fee Schedules." link is present in the Links Panel
Then user verifies "Provider Billing Manual" link is present in the Links Panel
Then user verifies "Sign up for electronic funds" link is present in the Links Panel
Then user verifies "Online Claims Appeals Application" link is present in the Links Panel
Then user Close the Related Links Panel
Then user navigates to the "HCC & HEDIS Program"
Then user Clicks on Related Links option in the Portal
Then user verifies "HEDIS Gaps" link is present in the Links Panel
Then user verifies "HEDIS Reference Guide" link is present in the Links Panel
Then user Close the Related Links Panel
Then user navigates to the "More"
And user clicks "Coordination of Benefits"
Then user Clicks on Related Links option in the Portal
Then user verifies "Coordinating with Other Coverage" link is present in the Links Panel
Then user Close the Related Links Panel
Then user navigates to the "More"
And user clicks "Benefit Admin Manual"
Then user Clicks on Related Links option in the Portal
Then user verifies "Benefits Administration Help Guide" link is present in the Links Panel
Then user Close the Related Links Panel
Then user navigates to the "More"
And user clicks "Contracts, Benefit Guides & Riders"
Then user Clicks on Related Links option in the Portal
Then user verifies "Contracts, Benefit Guides and Riders Help" link is present in the Links Panel
Then user Close the Related Links Panel
#Then user Clicks on Resources link in the Home Page
#Then user expands "memberheading"
#Then user validates "Billing Information" is present in the Resources page
#Then user validates "Credentialing Process" is present in the Resources page
#Then user validates "Plans and Products - ID Card Samples" is present in the Resources page
#Then user validates "Policies and Procedures" is present in the Resources page
#Then user validates "Procedure Reference Lists" is present in the Resources page
#Then user validates "Provider Newsroom" is present in the Resources page
#Then user expands "contractheading"
#Then user validates "Behavioral Health" is present in the Resources page
#Then user validates "Care Management Program" is present in the Resources page
#Then user validates "HAP Criteria for Inpatient Admissions 2020" is present in the Resources page
#Then user validates "HAP’s Palliative Care Program" is present in the Resources page
#Then user validates "Healthy Living Rewards Program for Eligible Medicare Members" is present in the Resources page
#Then user validates "Highly Preferred Skilled Nursing Facilities" is present in the Resources page
#Then user validates "iStrive for Better Health" is present in the Resources page
#Then user validates "Member Programs, Tools and Resources" is present in the Resources page
#Then user validates "Post-Acute Care – Partnership with naviHealth" is present in the Resources page
#Then user expands "copayheading"
#Then user validates "Drug Formulary Lists" is present in the Resources page
#Then user validates "Filling Your Prescription" is present in the Resources page
#Then user validates "Medicare Part D Coverage Determination Request" is present in the Resources page
#Then user validates "Medication Request Forms" is present in the Resources page
#Then user validates "Prior Authorization Criteria" is present in the Resources page
#Then user validates "Specialty Pharmaceuticals" is present in the Resources page


@PROVIDERSTATICCONTENTS02	@DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider12 | Automationpcp1 | Today1234 |

@PROVIDERSTATICCONTENTS03	@DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider12 | Automationpcp1 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Provider Manage Users Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to "Manage Users"
Then user clicks on the View all Users link 
Then user searches by NPI 
Then user selects the First user id and clicks on Update user link 
Then user updates the "information" and clicks on the Update button
Then user navigates to "Manage Users"
Then user clicks on the View all Users link
Then user searches by NPI 
Then user validates "information" is updated successfully


@PROVIDERMANAGAEUSERS02	@DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider13 | Automationpcp1 | Today1234 |

@PROVIDERMANAGAEUSERS03	@DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider13 | Automationpcp1 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Provider Update Profile functionality for child users Validations on Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to "Update Profile" 
Then user updates the Contact Details "information" and clicks on the save button
Then user waits for 5000 ms
Then user navigates to "Update Profile" 
Then user validates Contact Details "information" is updated successfully for child user

@PROVIDERUPDATEPROFILEFORCHILDUSER02	@DAILYREGRESSIONRUNTST02 @PROVIDER02
Examples:
| Regression case No | username | password |
| Provider14 | Automationpcp1  | Today1234 |

@PROVIDERUPDATEPROFILEFORCHILDUSER03	@DAILYREGRESSIONRUNTST03 @PROVIDER03
Examples:
| Regression case No | username | password |
| Provider14 | Automationpcp1 | Today1234 |


#Scenario Outline: Reg No - <Regression case No> - Provider Update Profile functionality for Admin users Validations on Provider Portal for Userid - <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "ProviderId" key to value "<username>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
#Then user navigates to "Update Profile"
#Then user validates update profile page is displayed for admin user is displayed
#Then user selects the None of these radio button and clicks on Submit button
#Then user validates the updated informations message is displayed
#
#@PROVIDERUPDATEPROFILEFORADMINUSER02	@DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username | password |
#| Provider15 | Automationpcp1 | Today1234 |
#
#@PROVIDERUPDATEPROFILEFORADMINUSER03	@DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username | password |
#| Provider15 | Automationpcp1 | Today1234 |
#
#
#
#Scenario Outline: Reg No - <Regression case No> - Provider Update Profile functionality for Admin users Validations on Provider Portal for Userid - <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "ProviderId" key to value "<username>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
#Then user navigates to "Update Profile"
#Then user enters the "<password>" and updates the "<NewPassword>" and "<ConfirmPassword>"
#Then user verifies the HAP logo and the LogOut Button
#Then user close browser
#Given user launches the portal "Portal"
#Given user logs in to the SPortal HAP application with user id "ProviderId" and password "<NewPassword>"
#Then user navigates to "Update Profile" 
#Then user enters the "<NewPassword>" and updates the "<password>" and "<password>"
#
#@PROVIDERCHANGEPASSWORD02	@DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username | password | NewPassword | ConfirmPassword |
#| Provider15 | Automationpcp1 | Today1234 | Today123 | Today123 |
#
#@PROVIDERCHANGEPASSWORD03	@DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username | password | NewPassword | ConfirmPassword |
#| Provider15 | Automationpcp1 | Today1234 | Today123 | Today123 |
