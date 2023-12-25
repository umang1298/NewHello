 @PATOLDARCH
Feature: Personal Alliance Transitional - Old Architechture - Member & Employer - HMO/EPO/PPO


#
#Scenario Outline: Personal Alliance Transitional -Member- Document verification for <MemberType> - <MemberIdValue>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
#Given user sets the "MemberId" key to value "<MemberIdValue>"
#Given user sets the SOAP request xml for MemberService:
#|Member Id| As of date | Path to write XML |
#|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
#Given user sends a SOAP request with the MemberService:
#| Endpoint | Path to XML| XpathtoProductID | XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreProductID |
#| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitEndDate | RenewalMonthKey | BenefitStartDate | BenefitEndDate | ProductID |
#Given user launches the portal "Portal"
#Given user logs in to the SPortal HAP application with user id "MemberId" and password "<Password>"
#Then user navigates to the "My Forms & Documents"
#And user clicks "My Plan Documents"
#Then verify the Benefit Year based on the key "BenefitStartDate" and "BenefitEndDate"
#Then user Clicks on "Health Insurance Policy" and verifies the title "NON-GROUP HEALTH INSURANCE POLICY" is displayed.
#And verify all the Riders documents for "<MemberType>" based on the "RenewalMonthKey"
#
#@PATRANSITIONALMEMBERSTAGE03 @GUISTAGE03 @DOCUMENTSSTAGE03 @PATRANSITIONALSTAGE03 
#Examples:
#| MemberIdValue | Password | MemberType|Endpoint|
#| 904251 | Today1234 |PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 10047400 | Today1234 |PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 10047441 | Today1234 |PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 3779611 | Today1234 |PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#
#@PATRANSITIONALMEMBERSTAGE02 @GUISTAGE02 @DOCUMENTSSTAGE02 @PATRANSITIONALSTAGE02
#Examples:
#| MemberIdValue | Password | MemberType|Endpoint|
#| 904251 | Today1234 |PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#| 10047400 | Today1234 |PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#| 10047441 | Today1234 |PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#| 3779611 | Today1234 |PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#
#Scenario Outline: Personal Alliance Transitional - Employer- for <MemberType> - <GroupIdValue> 
#	Given user sets the "GroupId" key to value "<GroupIdValue>" 
#	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
#	Given user launches the portal "Portal"
#	Given user logs in to the SPortal HAP application with user id "GroupId" and password "<Password>" 
#	Then User Clicks on "My Tools" in Employer Portal Landing Page 
#	Then User Clicks on "Contracts, Benefit Guides & Riders" in PA Employer Portal My Tools Page 
#	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
  #Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "RenwalMonthKey", "<MemberType>", "DateVal" and Checks "NON-GROUP HEALTH INSURANCE POLICY" is displayed  in Health Insurance Policy 
#	Then user logouts the employer portal 
#	
#@PATRANSITIONALEMPLOYERSTAGE03 @JENKINSECONDRUNSTAGE03 @PATRANSITIONALSTAGE03 
#Examples: 
#	| GroupIdValue | MemberType | Password |
#	| 10000001 |PPO| Today1234 |
#
#@PATRANSITIONALEMPLOYERSTAGE02 @JENKINSECONDRUNSTAGE02 @PATRANSITIONALSTAGE02 
#Examples: 
#	| GroupIdValue | MemberType | Password |
#	| 10000001 |PPO| Today1234 |	

Scenario Outline: Reg No - <Regression case No> - Personal Alliance Transitional - Member - Links verification for <MemberType> - <MemberIdValue>
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
Then user verifies the document links for PAT Member based on "<MemberType>" and Response XML "/XMLs/GetRidersByProductIDResponse.xml" and "RenewalMonthKey"
Then user Logouts the session

@PATRANSITIONALMEMBERLINKSSTAGE03  #@DAILYREGRESSIONRUNTST03
Examples:
| Regression case No |MemberIdValue| Password |MemberType|EndpointMemberService|EndpointBenefitService|
#| 904251 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
| PATransitionalMEM01 | 10047400 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
| PATransitionalMEM02 | 10047441 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|
| PATransitionalMEM03 | 3779611 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|http://facetstest3api.hap.org/BenefitService/benefitService.asmx|

@PATRANSITIONALMEMBERLINKSSTAGE02  #@DAILYREGRESSIONRUNTST02
Examples:
| Regression case No |MemberIdValue| Password |MemberType|EndpointMemberService|EndpointBenefitService|
#| 904251 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
| PATransitionalMEM01 | 10047400 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
| PATransitionalMEM02 | 10047441 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
| PATransitionalMEM03 | 3779611 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
 
Scenario Outline: Reg No - <Regression case No> - Personal Alliance Transitional - Employer - Links Verification for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal" 
	Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page 
	Then User Clicks on "Contracts, Benefit Guides & Riders" in PA Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
	Then user verifies the links for PAT Employer based on the "GroupId", "countofSubgroupids" , "RenwalMonthKey", "<MemberType>" and "DateVal" 
	Then user logouts the employer portal 
	
	@PATRANSITIONALEMPLOYERLINKSSTAGE03 @PATRANSITIONALSTAGE03 
	Examples: 
		| Regression case No | GroupIdValue | MemberType | Password |
		| PATransitionalEMP04 | 10000001 | PPO | Today1234 |
		
	@PATRANSITIONALEMPLOYERLINKSSTAGE02 @PATRANSITIONALSTAGE02 
	Examples: 
	| Regression case No | GroupIdValue | MemberType | Password |
	| PATransitionalEMP04 | 10000001 | PPO | Today1234 |