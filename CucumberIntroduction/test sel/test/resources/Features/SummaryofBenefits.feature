
Feature: Summary of Benefits - SG & PA - Member & Employer - HMO/EPO/PPO 


Scenario Outline: Small Group Transitional- SBC Document verification for <MemberType> - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Given user sends a SOAP request with the MemberService To get Product IDs:
| Endpoint | Path to XML| XPathToMedicalProduct | XPathToPharmacyProduct | KeyToStoreMedicalProduct | KeyToStorePharmacyProduct | 
| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].ProductId | MedicalProductID | PharmacyProductID |
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document for "MedicalProductID" and "PharmacyProductID"

#@SGTRANSITIONALMEMBERSBCSTAGE03 @GUISTAGE03 @DOCUMENTSSTAGE03 @TRANSITIONALSBCSTAGE03 
#Examples:
#|Usertype|MemberIdValue|Password|MemberType|Endpoint|
#|Member| 10683886 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 4266635 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 3917742 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 952823 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 10302939 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 4234591 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@SGTRANSITIONALMEMBERSBCSTAGE02 @GUISTAGE02 @DOCUMENTSSTAGE02 @TRANSITIONALSBCSTAGE02 
Examples:
|Usertype|MemberIdValue|Password|MemberType|Endpoint|
|Member| 10683886 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 4266635 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 3917742 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 952823 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 10302939 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 4234591 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Personal Alliance Transitional -SBC Document verification for <MemberType> - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Given user sends a SOAP request with the MemberService:
| Endpoint | Path to XML| XpathtoProductID | XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreProductID |
| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitEndDate | RenewalMonthKey | BenefitStartDate | BenefitEndDate | ProductID |
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document for "MedicalProductID" and "PharmacyProductID"

#@PATRANSITIONALMEMBERSBCSTAGE03 @GUISTAGE03 @DOCUMENTSSTAGE03 @TRANSITIONALSBCSTAGE03 
#Examples:
#|Usertype|MemberIdValue|Password|MemberType|Endpoint|
#|Member| 904251 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 10047400 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member| 10047441 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#|Member|3779611 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@PATRANSITIONALMEMBERSBCSTAGE02 @GUISTAGE02 @DOCUMENTSSTAGE02 @TRANSITIONALSBCSTAGE02 
Examples:
|Usertype|MemberIdValue|Password|MemberType|Endpoint|
|Member| 904251 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 10047400 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member| 10047441 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
|Member|3779611 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

	Scenario Outline: Small Group Transitional SBC Document verification- Employer- for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal"
	Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page 
	Then User Clicks on "Summary of Benefit and Coverage (SBCs)" in Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids"
	Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "MedicalKey","PharmacyKey", "<MemberType>" and "DateVal"
	Then user logouts the employer portal  
	
	@SGTRANSITIONALEMPLOYERSBC @GUISTAGE @DOCUMENTSSTAGE		@TRANSITIONALSBCSTAGE 
	Examples: 
		| GroupIdValue | MemberType | Password |
		| 10003855 |PPO| Today1234 |
		| 10004296 | PPO | Today1234 |
		| 10003219 | PPO | Today1234 |
		| 10004094 | HMO | Today1234 |
		| 10002674 | HMO | Today1234 |
		| 10001231 | EPO | Today1234 |



Scenario Outline: Small Group Transitional - Employer- for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal"
	Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page
	Then User Clicks on "Summary of Benefit and Coverage (SBCs)" in Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids"
	Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "MedicalKey","PharmacyKey", "<MemberType>" and "DateVal"
	Then user logouts the employer portal  

@PATRANSITIONALEMPLOYERSBCSTAGE @JENKINSECONDRUNSTAGE @TRANSITIONALSBCSTAGE 
Examples: 
	| GroupIdValue | MemberType | Password |
	| 10000001 |PPO| Today1234 |
	
	






	


		