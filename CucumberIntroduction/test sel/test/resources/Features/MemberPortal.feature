@MEMBERPORTAL
Feature: Regression Test Scenaios - Member Portal

Scenario Outline: Reg No - <Regression case No> - MemberPortal Home Page Validations for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user verifies "Deductibles and Out-of-Pocket" widget is displayed
Then user verifies "My Benefits" widget is displayed
Then user verifies "Claim Summary" widget is displayed
Then user verifies clicking "Change PCP " link takes user to "ProviderLookup Online" page
#Then user verifies clicking "view more" on "Deductibles and Out-of-Pocket" widget takes user to "My Deductibles and Out-of-Pocket" page
Then user verifies clicking "view more" on "My Benefits" widget takes user to "My Coverage" page
Then user verifies clicking "view more" on "Claim Summary" widget takes user to "My Claims" page

@MEMBERHOMEPAGE03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER01|Subscriberwith dependents(member contract drop down)|10070629200|Today1234 |
|MEMBER02|Tiered Subscriber with no dependents|10002378400|Today1234 |
|MEMBER03|Medicaid Member| 10066923000|Today1234|
|MEMBER04|Medicare Member | 10040954700 | Today1234 |
|MEMBER05| Dependent | 10085680901 | Today1234 |
|MEMBER06|Medicare-Individual|2312212|Today1234|
|MEMBER07|Medicare-EGWP|943147|Today1234|
 
@MEMBERHOMEPAGE02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER01|Subscriberwith dependents(member contract drop down)|10003540700|Today1234 |
|MEMBER02|Tiered Subscriber with no dependents|10002378400|Today1234 |
|MEMBER03|Medicaid Member| 10067549900|Today1234|
|MEMBER04|Medicare Member | 10040954700 | Today1234 |
|MEMBER05| Dependent | 10085680901 | Today1234 |
|MEMBER06|Medicare-Individual|2312212|Today1234|
|MEMBER07|Medicare-EGWP|943147|Today1234|
                                  
Scenario Outline: Reg No - <Regression case No> - My Claim Application validations for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Claims"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then verifies list of claims displayed for the "MemberName" from the "05/11/2018" 
Then user does the validation based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" for "MemberName" and checks "ClaimsPresent"
Then user gets the ClaimNumber from search results and set the value to "Claim Number" if "ClaimsPresent"
Given user sets the SOAP request xml for ClaimService
|ClaimNumber | Path to write XML |
|Claim Number| XMLs/GetClaimServiceByClaimnumber.xml |  
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetClaimServiceByClaimnumber.xml | XMLs/GetClaimServiceByClaimnumberResponse.xml | 
Given user sets the SOAP request xml for ClaimDetail
|ClaimNumber | Path to write XML |DiagnosisCode|
|Claim Number| XMLs/GetClaimDetailByClaimnumber.xml | 0| 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetClaimDetailByClaimnumber.xml | XMLs/GetClaimDetailByClaimnumberResponse.xml | 
Then user clicks on "Claim Number" link from search results if "ClaimsPresent"
#Then user gets the MemRecordNumber from "XMLs/GetAllContractsBySubscriberIDResponse.xml" set the value to "memrecordnumber" 
Then user does the service detail validation based on the ClaimServiceresponse XML "/XMLs/GetClaimServiceByClaimnumberResponse.xml" if "ClaimsPresent"
Then user does the provider information validation based on the ClaimDetaileresponse XML "XMLs/GetClaimDetailByClaimnumberResponse.xml" if "ClaimsPresent"
Then user does the Member information validation based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" for "MemberName" if "ClaimsPresent"
Then user validates the EOB Document for the value yes/no based on MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" for "MemberName" if "ClaimsPresent"


@MYCLAIMSAPPLICATION03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |Endpoint|EndpointBenefit|
|MEMBER08|Subscriberwith dependents(member contract drop down)|10003540700|Today1234 |http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER09|Medicare-Individual|10009216200|Today1234|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER10|Medicare-EGWP|10045141500|Today1234|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
##|MEMBER11|Subscriber with dependents (member contractntract drop down)|10000246700|Today1234 |http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER12|Medicare Supplemental Member|10049179000|Today1234|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER13|Medicaid Member| 10066923000|Today1234|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|    http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER14|Personal Alliance Member	|10006968701|Today1234|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MYCLAIMSAPPLICATION02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |Endpoint|EndpointBenefit|
|MEMBER08|Subscriberwith dependents(member contract drop down)|10003540700|Today1234 |https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER09|Medicare-Individual|10009216200|Today1234|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER10|Medicare-EGWP|10045141500|Today1234|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER11|Subscriber with dependents (member contract drop down)|10000246700|Today1234 |https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER12|Medicare Supplemental Member|10049179000|Today1234|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER13|Medicaid Member| 10067549900|Today1234|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|    https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER14|Personal Alliance Member	|10006968701|Today1234|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - My Claim Application validations for Dual Contracts <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Claims"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then user gets the MemRecordNumber from "XMLs/GetAllContractsBySubscriberIDResponse.xml" set the value to "memrecordnumber"
Given user sets the SOAP request xml for ClaimSummary by memrecord number:
|memrecordnumber|DOS From| DOS To | Path to write XML |
|memrecordnumber|2018-01-01 |DateVal | XMLs/claimSummaryXMLPath.xml |
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//claimSummaryXMLPath.xml |XMLs/GetClaimSummaryResponse.xml|
Then verifies list of claims displayed for the "MemberName" from the "05/11/2018" 
#Then user does the Multiple contracts validation based on the Claimsummary XML "XMLs/GetClaimSummaryResponse.xml"
Then user gets the ClaimNumber from search results and set the value to "Claim Number" if "ClaimsPresent"
Given user sets the SOAP request xml for ClaimService
|ClaimNumber | Path to write XML |
|Claim Number| XMLs/GetClaimServiceByClaimnumber.xml |  
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetClaimServiceByClaimnumber.xml | XMLs/GetClaimServiceByClaimnumberResponse.xml | 
Given user sets the SOAP request xml for ClaimDetail
|ClaimNumber | Path to write XML |DiagnosisCode|
|Claim Number| XMLs/GetClaimDetailByClaimnumber.xml | 0| 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetClaimDetailByClaimnumber.xml | XMLs/GetClaimDetailByClaimnumberResponse.xml | 
Then user clicks on "Claim Number" link from search results if "ClaimsPresent"
Then user does the service detail validation based on the ClaimServiceresponse XML "/XMLs/GetClaimServiceByClaimnumberResponse.xml" if "ClaimsPresent"
Then user does the provider information validation based on the ClaimDetaileresponse XML "XMLs/GetClaimDetailByClaimnumberResponse.xml" if "ClaimsPresent"
Then user does the Member information validation based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" for "MemberName" if "ClaimsPresent"
Then user validates the EOB Document for the value yes/no based on MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" for "MemberName" if "ClaimsPresent"


@MYCLAIMSAPPLICATIONDUAL03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |Endpoint|EndpointBenefit|
|MEMBER15|Subscriber with multiple contracts (member contract drop down)|10000623200|Today1234 |http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MYCLAIMSAPPLICATIONDUAL02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |Endpoint|EndpointBenefit|
|MEMBER15|Subscriber with multiple contracts (member contract drop down)|10000623200|Today1234 |https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - My Coverage Application validations for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
#And user validates member is having single or dual contracts based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"
And user selects "Contract Information"
Then user validates contract information based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" 
And user selects "Covered Members"
Then user validates Covered members based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml" for "<User Type>"
And user selects "My Costs"
Then user gets the MemRecordNumber from "XMLs/GetAllContractsBySubscriberIDResponse.xml" set the value to "memrecordnumber"
#for the "MemberId"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|memrecordnumber| DateVal | XMLs/GetContractByMemberRecordNumber.xml |
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractByMemberRecordNumber.xml | XMLs/GetAllContractsBymemrecordnumberResponse.xml |
#Then user validates third party links based on the MemberServiceresponse XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"
Then user validates Yourcosts details based on the MemberServiceresponse XML "XMLs/GetAllContractsBymemrecordnumberResponse.xml"

@MYCOVERAGAPPLICATION03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER16|Subscriberwith dependents(member contract drop down)|10003540700|Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER17|Dependent|10085680901|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MYCOVERAGAPPLICATION02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER16|Subscriberwith dependents(member contract drop down)|10003540700|Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER17|Dependent|10085680901|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - My Coverage Application validations for Terminated/Future <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "Contract Information"
Then user validates Terminated and future members

@MYCOVERAGAPPLICATIONTERMINATE03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |Endpoint|EndpointBenefit|
#|MEMBER18|Terminatedmember|10147277600|Password1|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER19|Futuremember|10147592300|Password1|http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER20| Dependent | 10085680901 | Today1234 |http://facetstest3api.hap.org/ClaimService/ClaimService.asmx|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MYCOVERAGAPPLICATIONTERMINATE02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |Endpoint|EndpointBenefit|
#|MEMBER18|Terminatedmember|10147277600|Password1|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER19|Futuremember|10147592300|Password1|https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER20| Dependent | 10085680901 | Today1234 |https://facetstest2api.hap.org/ClaimService/ClaimService.asmx|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline:  Reg No - <Regression case No> - My coverage Application validations for COB member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Benefits"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then user gets the MemRecordNumber from "XMLs/GetAllContractsBySubscriberIDResponse.xml" set the value to "memrecordnumber"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|memrecordnumber| DateVal | XMLs/GetContractByMemberRecordNumber.xml |
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractByMemberRecordNumber.xml | XMLs/GetAllContractsBymemrecordnumberResponse.xml |
And user clicks option "My Coverage"
Then user validates COB Details from "XMLs/GetAllContractsBymemrecordnumberResponse.xml"


@MYCOVERAGECOBMEMBER03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER21|COB member|10110729100|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MYCOVERAGECOBMEMBER02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER21|COB member|10110729100|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline:  Reg No - <Regression case No> - My Referral and Authorizations validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Care"
And user clicks option "Referrals & Prior Authorizations"
Given user sets the SOAP request xml for RefferralService Get Refferral List by Member id:
|MemberID|DOS From| DOS To |MaxReffertals|Path to write XML |
|MemberId|2019-11-05 |DateVal | 1|XMLs/GetRefferalListByMemberIDXMLPath.xml |
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetRefferalListByMemberIDXMLPath.xml | XMLs/GetRefferralListbyMemberIDResponse.xml |
Then verifies list of referral number displayed for the "MemberName" from the "05/11/2019" 
Then user validates Referrals & Prior Authorizations from "XMLs/GetRefferralListbyMemberIDResponse.xml" and "MemberName"

@MYREFERRAL03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password|Endpoint|
|MEMBER22|REFERAL|10045141500|Today1234|http://facetstest3api.hap.org/ReferralInquiryService/ReferralInquiryService.asmx?wsdl|

@MYREFERRAL02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password|Endpoint|
|MEMBER22|REFERAL|10045141500|Today1234|https://facetstest2api.hap.org/ReferralInquiryService/ReferralInquiryService.asmx?wsdl|

Scenario Outline:  Reg No - <Regression case No> - My Heath Reminders validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Health & Wellbeing"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then user gets the MemRecordNumber from "XMLs/GetAllContractsBySubscriberIDResponse.xml" set the value to "memrecordnumber"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|memrecordnumber| DateVal | XMLs/GetContractByMemberRecordNumber.xml |
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractByMemberRecordNumber.xml | XMLs/GetAllContractsBymemrecordnumberResponse.xml |
And user clicks option "My Health Reminders"
Then user validates MyHealthRemainders from "XMLs/GetAllContractsBymemrecordnumberResponse.xml"


@MYHEALTHREMINDER03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
#|MEMBER23|member|10024621900|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER24|Subscriber with dependents (member contract drop down)|10000246700|Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER25|Medicare-Individual|10009216200|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER26|Medicare-EGWP|10045141500|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@MYHEALTHREMINDER02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER23|member|10024621900|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER24|Subscriber with dependents (member contract drop down)|10000246700|Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER25|Medicare-Individual|10009216200|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER26|Medicare-EGWP|10045141500|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - My Health WellBeing menus validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Health & Wellbeing"
Then user validates all the links under My Health Wellbeing Menu for "<User Type>"

@MYHEALTHWELLBEINGMENUS03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
#|MEMBER27|PAmember|10031273|Today1234|
|MEMBER28|ActiveFit|4020314|Today1234|
#|SilverFit|3796578|Today123|
|MEMBER29|Medicare-Individual|10009216200|Today1234|
|MEMBER30|Medicare-EGWP|10045141500|Today1234|

@MYHEALTHWELLBEINGMENUS02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER27|PAmember|10031273|Today1234|
|MEMBER28|ActiveFit|4020314|Today1234|
#|SilverFit|3796578|Today123|
|MEMBER29|Medicare-Individual|10009216200|Today1234|
|MEMBER30|Medicare-EGWP|10045141500|Today1234|


Scenario Outline: Reg No - <Regression case No> - My Care menu Static links validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user validates all the static content links under My Care Menu for "<User Type>"
#Then user validates the Link "Care Management" under My Care Menu

@MYCAREMENU03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
#|MEMBER31|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|
|MEMBER32|Medicare-Individual|10009216200|Today1234|
|MEMBER33|Medicare-EGWP|10045141500|Today1234|

@MYCAREMENU02   @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
#|MEMBER31|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|
|MEMBER32|Medicare-Individual|10009216200|Today1234|
|MEMBER33|Medicare-EGWP|10045141500|Today1234|



Scenario Outline: Reg No - <Regression case No> - My Member Perks menu Static links validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user validates all the static content links under Member Perks Menu for "<User Type>"

@MEMBERPERKSMENUS03   @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER34|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|

@MEMBERPERKSMENUS02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER34|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|



Scenario Outline: Reg No - <Regression case No> - Reimbursement Account Link validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Benefits"
And user clicks option "Manage my Health Reimbursement Account"
Then user validates Reimbursement Account Link

@REIMBURSEMENTLINK03 @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER35|Subscriberwith dependents(member contract drop down)|609561|Today1234|

@REIMBURSEMENTLINK02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER35|Subscriberwith dependents(member contract drop down)|609561|Today1234|


Scenario Outline: Reg No - <Regression case No> - Benefit Coverage Policies validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Forms & Documents"
And user clicks option "Benefit Coverage Policies"
Then user searches Policy by text and validates search results are displayed
Then user searches Policy by Policy Index and Validates the search results are displayed 
Then user searches Policy by Categorical Index and validates the Searched results are displayed
Then user searches Policy by Recent Changes and validate searched Results are displayed

@BENEFITCOVERAGE03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER36|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|
|MEMBER37|Medicare-Individual|10009216200|Today1234|
|MEMBER38|Medicare-EGWP|10045141500|Today1234|
@BENEFITCOVERAGE02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER36|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|
|MEMBER37|Medicare-Individual|10009216200|Today1234|
|MEMBER38|Medicare-EGWP|10045141500|Today1234|




Scenario Outline: Reg No - <Regression case No> - Formularly Exception validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Forms & Documents"
And user clicks option "Formulary Exception Form"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then user validates Formulary Exception Form "XMLs/GetAllContractsBySubscriberIDResponse.xml"

@FORMULARYEXCEPTION03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit|
|MEMBER39|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@FORMULARYEXCEPTION02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit|
|MEMBER39|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Pay My Bill validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "Pay My Bill"
Then user clicks on Pay my Bill "Manage My Account"
And user validates Manage Auto pay option 
And user validates Manage Method of Payment
Then user clicks on Pay my Bill "Pay"
Given user sets up the request json file for Paymybill
|SubscriberID|GroupID|RequestFilePath|
|<SubscriberID>|<GroupID>| XMLs/jsonrequest.json|
Given user sends the REST POST JSON using the following 
|BaseURL|BasePath|PayloadPath|writeresponsejsonpath|
|https://apistage/HAP.API.Billing|/api/invoices/individual/search/amount-due|//XMLs//jsonrequest.json|/XMLs/ResponseMarriot.json|
And user validates Pay Invoice based on "//XMLs/ResponseMarriot.json" response


@PAYMYBILL03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username |SubscriberID|GroupID| password |
|MEMBER40|Subscriberwith|10024343100|100243431|10000006|Today1234|
|MEMBER40|Subscriberwith|10041321300|100413213|10000005|Today123|
#10045534900

@PAYMYBILL02      @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER40|Subscriberwith|10389244|Today1234|

Scenario Outline: Reg No - <Regression case No> - My deductible and Out of pocket validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Benefits"
And user clicks option "My Deductibles and Out-of-Pocket"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then user validates Innetwork DeductibleandOOP page based on "XMLs/GetAllContractsBySubscriberIDResponse.xml" and "DateVal" using deductible service
Then user validates Outnetwork DeductibleandOOP page based on "XMLs/GetAllContractsBySubscriberIDResponse.xml" and "DateVal" using deductible service
@DEDUCTIBLE03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER41|deductible |10003540700|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER42|Deduc OOP |10085692600|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER43|Medicaid Member| 10067549900|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER44| medicare | 10024621900 | Today1234 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER45|Medicare-Individual|10009216200|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
|MEMBER46|Medicare-EGWP|10045141500|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@DEDUCTIBLE02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER41|deductible |10003540700|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER42|Deduc OOP |10085692600|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER43|Medicaid Member| 10067549900|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER44| medicare | 10024621900 | Today1234 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER45|Medicare-Individual|10009216200|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#|MEMBER46|Medicare-EGWP|10045141500|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - AWD Form Request validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Forms & Documents"
And user clicks "My Forms"
Then user gets "SubscriberID" from "MemberId"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml |
Then user clicks on online forms "Automatic Withdrawal Request" link based on "XMLs/GetAllContractsBySubscriberIDResponse.xml"
And user validates AWD form Request

@AWDFORM03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER41|Medicare-Individual|10009216200|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|


@AWDFORM02  @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password|EndpointBenefit|
|MEMBER41|Medicare-Individual|10009216200|Today1234|http://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

