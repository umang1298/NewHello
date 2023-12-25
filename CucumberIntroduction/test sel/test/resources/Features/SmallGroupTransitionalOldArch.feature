@SGTRANSOLDARCH
Feature: Small Group Transitional - Old Arch - Member & Employer - HMO/EPO/PPO 


Scenario Outline: Small Group Transitional - Member - Document verification for <MemberType> - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |
Given user sends a SOAP request with the MemberService:
| Endpoint | Path to XML| XpathtoProductID | XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreProductID |
| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitEndDate | RenewalMonthKey | BenefitStartDate | BenefitEndDate | ProductID |
Given user launches the portal "Portal"
Given user logs in to the SPortal HAP application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Forms & Documents"
And user clicks "My Plan Documents"
Then verify the Benefit Year based on the key "BenefitStartDate" and "BenefitEndDate"
Then verify "<MemberType>" subscriber contract based on the "RenewalMonthKey"
Then verify "<MemberType>" Summary of Contract changes based on the "RenewalMonthKey"
And verify all the Riders documents for "<MemberType>" based on the "RenewalMonthKey"
Then user navigates to the "My Benefits"
Then user Logouts the session

#@SGTRANSITIONALMEMBERSTAGE03 @DOCUMENTSSTAGE03 @GUISTAGE03 @SGTRANSITIONALSTAGE03 
#Examples:
#|MemberIdValue| Password |MemberType|Endpoint|
#| 10683886 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 4266635 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 3917742 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 952823 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 10302939 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 4234591 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@SGTRANSITIONALMEMBERSTAGE02 @DOCUMENTSSTAGE02 @GUISTAGE02 @SGTRANSITIONALSTAGE02 
Examples:
|MemberIdValue| Password |MemberType|Endpoint|
| 10683886 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| 4266635 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| 3917742 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| 952823 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| 10302939 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| 4234591 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Small Group Transitional - Employer - Document verification for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal" 
	Given user logs in to the SPortal HAP application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page 
	Then User Clicks on "Contracts, Benefit Guides & Riders" in Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
	Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "RenwalMonthKey", "<MemberType>" and "DateVal" 
	Then user logouts the employer portal 
	
	@SGTRANSITIONALEMPLOYERSTAGE @DOCUMENTSSTAGE @GUISTAGE @SGTRANSITIONALSTAGE 
	Examples: 
		| GroupIdValue | MemberType | Password |
		| 10003855 |PPO| Today1234 |
		| 10004296 | PPO | Today1234 |
		| 10003219 | PPO | Today1234 |
		| 10004094 | HMO | Today1234 |
		| 10002674 | HMO | Today123 |
		| 10001231 | EPO | Today1234 |


Scenario Outline: Reg No - <Regression case No> - Small Group Transitional - Employer - OLD ARCH -Links Verification for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal" 
	Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page 
	Then User Clicks on "Contracts, Benefit Guides & Riders" in Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
	Then user verifies the links based on the "GroupId", "countofSubgroupids" , "RenwalMonthKey", "<MemberType>" and "DateVal" 
	Then user logouts the employer portal 
	
	@SGTRANSITIONALOLDEMPLOYERSTAGE03 @DAILYREGRESSIONRUNTST03 @CONTRACTSRIDERS03
	Examples: 
		| Regression case No | GroupIdValue | MemberType | Password |
		| SGTransitionalEMPOLD01 | 10002808 | HMO | Today1234 |
#		| SGTransitionalEMP02 | 10004296 | PPO | Today1234 |
#		| SGTransitionalEMP03 | 10003219 | PPO | Today1234 |
#		| SGTransitionalEMP04 | 10004094 | HMO | Today1234 |
#		| SGTransitionalEMP05 | 10002674 | HMO | Today1234 |
#		| SGTransitionalEMP06 | 10001231 | EPO | Today1234 |

@SGTRANSITIONALOLDEMPLOYERLINKSSTAGE02 @DAILYREGRESSIONRUNTST02 @CONTRACTSRIDERS02
	Examples: 
		| Regression case No | GroupIdValue | MemberType | Password |
		| SGTransitionalEMPOLD01 | 10002808 | HMO | Today1234 |
#		| SGTransitionalEMP02 | 10004296 | PPO | Today1234 |
#		| SGTransitionalEMP03 | 10003219 | PPO | Today1234 |
#		| SGTransitionalEMP04 | 10004094 | HMO | Today1234 |
#		| SGTransitionalEMP05 | 10002674 | HMO | Today1234 |
#		| SGTransitionalEMP06 | 10001231 | EPO | Today1234 |

Scenario Outline: Reg No - <Regression case No> - Small Group Transitional - Member - OLD Arch - Links verification for <MemberType> - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Given user sends a SOAP request with the MemberService:
| Endpoint | Path to XML| XpathtoProductID | XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreProductID |
| <EndpointMemberService> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitEndDate | RenewalMonthKey | BenefitStartDate | BenefitEndDate | ProductID |
Given user sets the SOAP request xml for getRidersbyProductID:
| ProductID | As of date | Path to write XML |
| ProductID | DateVal | XMLs/GetRidersByProductID.xml | 
Given user sends a SOAP request with GetRidersbyProductID:
| Endpoint | Path to XML |
| <EndpointBenefitService> | //XMLs//GetRidersByProductID.xml |
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Forms & Documents"
And user clicks "My Plan Documents"
Then verify the Benefit Year based on the key "BenefitStartDate" and "BenefitEndDate"
Then user verifies the document links based on "<MemberType>" and Response XML "/XMLs/GetRidersByProductIDResponse.xml" and "RenewalMonthKey"
Then user Logouts the session

@SGTRANSITIONALOLDMEMBERSTAGE03 @DAILYREGRESSIONRUNTST03 @FORMS03
Examples:
| Regression case No | MemberIdValue| Password |MemberType|EndpointMemberService|EndpointBenefitService|
| SGTransitionalMEMOLD01 | 2316884 | Today1234 | HMO |https://facetstest3api.hap.org/Memberservice/Memberservice.asmx|https://facetstest3api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM07 | 10683886 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM08 | 4266635 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM09 | 3917742 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM10 | 952823 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM11 | 10302939 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM12 | 4234591 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|

@SGTRANSITIONALOLDMEMBERSTAGE02 @DAILYREGRESSIONRUNTST02 @FORMS02
Examples:
| Regression case No | MemberIdValue| Password |MemberType|EndpointMemberService|EndpointBenefitService|
| SGTransitionalMEMOLD01 | 2316884 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM07 | 10683886 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM08 | 4266635 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM09 | 3917742 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM10 | 952823 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM11 | 10302939 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
#| SGTransitionalMEM12 | 4234591 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
