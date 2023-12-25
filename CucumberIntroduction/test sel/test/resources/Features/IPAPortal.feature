@IPAPORTAL
Feature: Regression Test Scenaios - IPA Portal


Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations on IPA Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "IPAId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user searches Provider with last name "muslah" and selects one and click on search
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

@IPAMEMBERELIGIBILITY03 @DAILYREGRESSIONRUNTST03 @IPA03
Examples:
| Regression case No | username | password |Endpoint|
| IPA01 | OH1_ADMIN | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@IPAMEMBERELIGIBILITY02 @DAILYREGRESSIONRUNTST02 @IPA02
Examples:
| Regression case No | username | password |Endpoint|
| IPA01 | tautomation9993 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations for Multiple MemberIDs on IPA Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "IPAId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user searches Provider with last name "muslah" and selects one and click on search
Then user gets three MemberIDs from search results:
| Mem1 | Mem2 | Mem3 |
| MemberID1 | MemberID2 | MemberID3 |
Then user navigates to the "Member Eligibility"
Then user enters "MemberID1" and "MemberID2" and "MemberID3" and clicks on submit
Then user verifies the eligibility results are displayed for "MemberID1" and "MemberID2" and "MemberID3"



@IPAMEMBERELIGIBILITYWITHMULTIPLEMEMBERS03 @DAILYREGRESSIONRUNTST03 @IPA03
Examples:
| Regression case No | username | password |Endpoint|
| IPA02 |  OH1_ADMIN| Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@IPAMEMBERELIGIBILITYWITHMULTIPLEMEMBERS02 @DAILYREGRESSIONRUNTST02 @IPA02
Examples:
| Regression case No | username | password |Endpoint|
| IPA02 | tautomation9993 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|



Scenario Outline: Reg No - <Regression case No> - HCC and HEDIS Gaps Panel Management Report for Provider/Site Validations on IPA Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "IPAId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PS"
Then user searches Provider with last name "muslah" and selects one
Then user Selects gap as "hedis" and Clicks on Submit and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PS"
Then user searches Provider with last name "muslah" and selects one
Then user Selects gap as "hcc" and Clicks on Submit and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PS"
Then user searches Provider with last name "muslah" and selects one
Then user Selects gap as "both" and Clicks on Submit and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PS"
Then user searches Provider with last name "muslah" and selects one
Then user Selects gap as "hedis" and Clicks on Download and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PS"
Then user searches Provider with last name "muslah" and selects one
Then user Selects gap as "hcc" and Clicks on Download and Validates Expected results
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PS"
Then user searches Provider with last name "muslah" and selects one
Then user Selects gap as "both" and Clicks on Download and Validates Expected results

@IPAPANELMANAGEMENTREPORTPS03 @DAILYREGRESSIONRUNTST03 @IPA03
Examples:
| Regression case No | username | password |
| IPA03 | OH1_ADMIN | Today1234 |

@IPAPANELMANAGEMENTREPORTPS02 @DAILYREGRESSIONRUNTST02 @IPA02
Examples:
| Regression case No | username | password |
| IPA03 | tautomation9993 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - HCC and HEDIS Gaps Panel Management Report for Physician Organisation Validations on IPA Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "IPAId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PO"
Then user Selects gap as "hedis" and Clicks on Download and Validates Expected results for Physician Organisation
Then user navigates to the "HCC & HEDIS Program"
Then user selects Reports for radio button "PO"
Then user Selects gap as "hcc" and Clicks on Download and Validates Expected results for Physician Organisation

@IPAPANELMANAGEMENTREPORTPO03 @DAILYREGRESSIONRUNTST03 @IPA03
Examples:
| Regression case No | username | password |
| IPA04 | tautomation9993 | Today1234 |

@IPAPANELMANAGEMENTREPORTPO02 @DAILYREGRESSIONRUNTST02 @IPA02
Examples:
| Regression case No | username | password |
| IPA04 | tautomation9993 | Today1234 |


#Scenario Outline: Reg No - <Regression case No> - IPA Update Profile functionality for child users Validations  for Userid - <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "IPAId" key to value "<username>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
#Then user navigates to "Update Profile" 
#Then user validates the Change Password Functionality in the IPA Portal
#Then user navigates to "Update Profile" 
#Then user changes password to the old password 
#
#
#@IPAUPDATEPROFILEFORCHILDUSER02	@DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username | password |
#| IPA05 | tautomation9993 | Today1234 |
#
#@IPAUPDATEPROFILEFORCHILDUSER03	@DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username | password |
#| IPA05 | tautomation9993 | Today1234 |
#
#
#Scenario Outline: Reg No - <Regression case No> - IPA Update Profile functionality for Admin users Validations  for Userid - <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "IPAId" key to value "<username>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
#Then user navigates to "Update Profile" 
#Then user validates the Change Password Functionality in the IPA Portal
#Then user navigates to "Update Profile" 
#Then user changes password to the old password
#
#@IPAUPDATEPROFILEFORADMINUSER02	@DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username | password |
#| IPA06 | os1_admin | Today1234 |
#
#@IPAUPDATEPROFILEFORADMINUSER03	@DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username | password |
#| IPA06 | os1_admin | Today1234 |
#
#Scenario Outline: Reg No - <Regression case No> - IPA Manage Users Validations on IPA Portal for Userid - <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "IPAId" key to value "<username>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
#Then user navigates to "Manage Users"
#Then user clicks on the View all Users link 
#Then user selects the First user id and clicks on Update user link 
#Then user stores the list of "Available Applications" for the user
#Then user updates the "information" and clicks on the Update button
#Then user navigates to "Manage Users"
#Then user clicks on the View all Users link
#Then user validates "information" is updated successfully
#Then user selects the First user id and clicks on Update user link
#Then user validates the "Available Applications" remains unchanged for the user
#
#
#@IPAMANAGEUSER02	@DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username | password |
#| IPA07 | tautomation9993 | Today1234 |
#
#@IPAMANAGEUSER03	@DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username | password |
#| IPA07 | tautomation9993 | Today1234 |


Scenario Outline: Reg No - <Regression case No> - IPA Coordination of Benefits Validations on IPA Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "IPAId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
Then user clicks on "Click here to view Member Roster" link
Then user searches Provider with last name "sachdeva" and selects one and click on search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user navigates to the "More"
And user clicks "Coordination of Benefits"
Then user searches COB informations for "MemberID"
Then user enters the COB information in the Add COB Information page if "COB Available"
Then user verifies the COB informations are displayed correctly if "COB Available"


@IPACOORDINATIONOFBENEFITS03 @DAILYREGRESSIONRUNTST03 #@IPA03
Examples:
| Regression case No | username | password |
| IPA08 | Os1_admin | Today1234 |

@IPACOORDINATIONOFBENEFITS02 @DAILYREGRESSIONRUNTST02 #@IPA02
Examples:
| Regression case No | username | password |
| IPA08 | Os1_admin | Today1234 |

Scenario Outline: Reg No - <Regression case No> - IPA Authorizations Validations on IPA Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "IPAId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "IPAId" and password "<password>"
Then user navigates to the "Authorizations" and validates the CareAffiliate page is displayed

@IPAAUTHORIZATIONS03  @DAILYREGRESSIONRUNTST03  #@IPA03
Examples:
| Regression case No | username | password |
| IPA09 | Os1_admin | Today1234 |

@IPAAUTHORIZATIONS02  @DAILYREGRESSIONRUNTST02 #@IPA02
Examples:
| Regression case No | username | password |
| IPA09 | Os1_admin | Today1234 |