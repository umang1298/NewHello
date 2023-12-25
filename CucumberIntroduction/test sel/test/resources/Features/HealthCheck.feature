@HEALTHCHECKSCENARIOS
Feature: Health Check Scenarios

Scenario Outline: Reg No - <Regression case No> -Health Check Sceanrio for Facets SOAP Webservices - GetAllContractsbySubscriberID - <Environment>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "SubscriberID" key to value "<SubscriberID>"
Given user launches the portal "Portal"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Then user sends a SOAP request and validates response success or Not:
| Endpoint | Path to Load XML|
| <EndPoint> | //XMLs//GetContractBySubscriberID.xml |

@FACETSSUBSCRIBERIDSERVICE
Examples:
| Regression case No |SubscriberID|EndPoint|Environment|
|HealthCheck01|100023784|http://facetstest1api.hap.org/Memberservice/Memberservice.asmx|TEST01|
|HealthCheck02|100035407|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|TEST02|
|HealthCheck03|100023784|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|TEST03|

Scenario Outline: Reg No - <Regression case No> -Health Check Sceanrio for Facets SOAP Webservices - GetAllContractsbyMemberRecordNumber - <Environment>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "MemberId" key to value "<MemberId>"
Given user launches the portal "Portal"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Then user sends a SOAP request and validates response success or Not:
| Endpoint | Path to Load XML|
| <EndPoint> | //XMLs//GetContractByMemberRecordNumber.xml |

@FACETSMEMBERRECORDNUMBERSERVICE
Examples:
| Regression case No |MemberId|EndPoint|Environment|
|HealthCheck04|2316884|http://facetstest1api.hap.org/Memberservice/Memberservice.asmx|TEST01|
|HealthCheck05|1441593|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|TEST02|
|HealthCheck06|2316884|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|TEST03|


Scenario Outline: Reg No - <Regression case No> -Health Check Sceanrio for Facets SOAP Webservices - DeductibleService - <Environment>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "MemberId" key to value "<MemberId>"
Given user launches the portal "Portal"
Given user sets up the SOAP request xml for member deductible service:
|ProductID|Contrived Key | As of date | Path to write XML |
|<ProductId>|<ContrivedKey> | DateVal | XMLs/GetMemberDeductible.xml |
Then user sends a SOAP request and validates response success or Not:
| Endpoint | Path to Load XML|
| <EndPoint> | //XMLs//GetMemberDeductible.xml |

@FACETSDEDUCTIBLESERVICE
Examples:
| Regression case No |ProductId|ContrivedKey|EndPoint|Environment|
|HealthCheck07|XR002331| 6116650 |http://facetstest1api.hap.org/MemberDeductibleService_v2/MemberDeductibleService_v2.asmx|TEST01|
|HealthCheck08|MA000243| 18493700 |https://facetstest2api.hap.org/MemberDeductibleService_v2/MemberDeductibleService_v2.asmx|TEST02|
|HealthCheck09|MA000243|18493700 |http://facetstest3api.hap.org/MemberDeductibleService_v2/MemberDeductibleService_v2.asmx|TEST03|


Scenario Outline: Reg No - <Regression case No> -Health Check Sceanrio for Facets SOAP Webservices - Remit Advice Service - <Environment>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "MemberId" key to value "<MemberId>"
Given user launches the portal "Portal"
Given user sets up the SOAP request xml for Remit Advice service:
|PaymentNo| As of date | Path to write XML |
|<PaymentNo>| DateVal | XMLs/PaymentSearch.xml |
Then user sends a SOAP request and validates response success or Not:
| Endpoint | Path to Load XML|
| <EndPoint> | //XMLs//PaymentSearch.xml |

@FACETSREMITADVICESERVICE
Examples:
| Regression case No |PaymentNo|EndPoint|Environment|
|HealthCheck10|95023220| http://facetstest3api.hap.org/RemitAdvice_v2/RemitAdvice_v2.asmx|TEST03|
|HealthCheck11|85813182| https://facetstest2api.hap.org/RemitAdvice_v2/RemitAdvice_v2.asmx|TEST02|
|HealthCheck12|95023220| http://facetstest1api.hap.org/RemitAdvice_v2/RemitAdvice_v2.asmx|TEST01|

Scenario Outline: Reg No - <Regression case No> -Health Check Sceanrio for PEGA service  - GetBenefitDocumentsMetaDataQHPMember
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "Portal"
Given user sets the SOAP Request xml for Pega Service for Health Check:
| As of date | Path to write XML | GroupIDtoSet | SubGroupIDtoSet | MedicalproductIDtoSet | PharmacyProducttoSet |
| DateVal | XMLs/GetBenefitDocumentsMetaDataPAQHPMember.xml | <GroupID> | <SubGroupID> | <MedicalProductID> | <PharmacyProductID> |
Given user sends the REST POST request using the following and validates response success or not:
| BaseURL | BasePath | PayLoadPath | 
| <BaseURLPEGA> | /member/benefitDoc/getProductBenefitDocuments | //XMLs//GetBenefitDocumentsMetaDataPAQHPMember.xml | 

@PEGASERVICETOGETBENEFITMETADATA
Examples:
| Regression case No |BaseURLPEGA| GroupID | SubGroupID | MedicalProductID | PharmacyProductID |
|HealthCheck13|https://memberservice.ecnonprodapps.hap.org|10001187|1102|PPQ00012|XRQ00031|

Scenario Outline: Reg No - <Regression case No> -Third party application - Care Affiliate through Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "Authorizations" and validates the CareAffiliate page is displayed

@CAREAFFILIATE
Examples:
| Regression case No | username | password |
|HealthCheck14| 1871575704_idadmin | Today1234 |

Scenario Outline: Reg No - <Regression case No> -Third party application - Clear Claims Connections through Provider Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Provider" application with user id "ProviderId" and password "<password>"
Then user navigates to the "More"
And user clicks "Code Editing Explanation"
Then user verifies terms and conditions are opened in new tab

@CLEARCLAIMSCONNECTIONS
Examples:
| Regression case No | username | password |
|HealthCheck15| 1871575704_idadmin | Today1234 |

Scenario Outline:Reg No - <Regression case No> -Third party application - Callidus connection through Agent Portal for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "MemberId" and password "<password>"
Then user navigates to Agen Portal "Callidus Cloud"
Then user Verifies Agent Portal menu Link

@CALLIDUS  
Examples:
| Regression case No | User Type	 | username | password |
| HealthCheck16 |Agent|jadams|Today1234|

Scenario Outline: Reg No - <Regression case No> -Third party application - Colibrium connection through Agent Portal for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "MemberId" and password "<password>"
Then user navigates to Agen Portal "HAP Connect"
Then user Verifies Agent Portal menu Link

@COLIBRIUM 
Examples:
| Regression case No | User Type	 | username | password |
| HealthCheck17 |Agent|mvanden9|Today1234|

Scenario Outline: Reg No - <Regression case No> -Third party application - Mybenefits/ EyeMed link <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to the "My Benefits"
And user clicks option "EyeMed"
Then user verifies EyeMed page

@EYEMED  
Examples:
|Regression case No| User Type	 | username | password|
|HealthCheck18|EyeMed|10000884600|Today1234|


