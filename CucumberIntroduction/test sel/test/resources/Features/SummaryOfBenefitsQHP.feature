
Feature: Summary of Benefits - SG & PA QHP - Member & Employer - HMO/EPO/PPO 


Scenario Outline: Small Group QHP Old Flow- Member- SBC Document verification for <MemberType> - <MemberIdValue>

Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Given user sends a SOAP request with the MemberService To get Product IDs:
| Endpoint | Path to XML| XPathToMedicalProduct | XPathToPharmacyProduct | KeyToStoreMedicalProduct | KeyToStorePharmacyProduct | 
|<Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].ProductId | MedicalProductID | PharmacyProductID |
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document for "MedicalProductID" and "PharmacyProductID"

#@SGQHPMEMBEROLDFLOWSBCSTAGE03 @DOCUMENTSSTAGE03 @GUISTAGE03 @QHPSBCSTAGE03 
#Examples:
#|MemberIdValue|Password|MemberType|Endpoint|
#| 10409740 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 10418714 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@SGQHPMEMBEROLDFLOWSBCSTAGE02 @DOCUMENTSSTAGE02 @GUISTAGE02 @QHPSBCSTAGE02 
Examples:
|MemberIdValue|Password|MemberType|Endpoint|
| 10409740 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#| 10418714 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Small Group QHP Old Flow - Employer- SBC Document verificaiton for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal" 
	Given user logs in to the SPortal HAP application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page 
	Then User Clicks on "Summary of Benefit and Coverage (SBCs)" in Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
	Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "MedicalKey","PharmacyKey", "<MemberType>" and "DateVal" 
	Then user logouts the employer portal 
	
	@SGQHPEMPLOYEROLDFLOWSBCSTAGE @DOCUMENTSSTAGE @GUISTAGE @QHPSBCSTAGE 
	Examples: 
		| GroupIdValue | MemberType | Password |
		| 10004815 |PPO| Today1234 |
		| 10000309 | PPO | Today1234 |
		


  
Scenario Outline: Small Group QHP Pega Flow -SBC Links verification- Employer- for <MemberType> - <GroupIdValue>

Given user sets the "GroupId" key to value "<GroupIdValue>" 
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
Then User Clicks on "My Tools" in Employer Portal Landing Page 
Then User Clicks on "Summary of Benefit and Coverage (SBCs)" in Employer Portal My Tools Page 
Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "MedicalKey","PharmacyKey", "<MemberType>" and "DateVal" 

@SGQHPEMPLOYERPEGAFLOWSBCSTAGE @GUISTAGE @QHPSBCSTAGE 
Examples:
		| GroupIdValue | MemberType | Password |
		| 20001828 |PPO| Today1234 |
		| 10001187 | PPO | Today1234 |



Scenario Outline: Personal Alliance QHP -SBC Links verification- Employer- for <MemberType> - <GroupIdValue>

Given user sets the "GroupId" key to value "<GroupIdValue>" 
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
Then User Clicks on "My Tools" in Employer Portal Landing Page 
Then User Clicks on "Summary of Benefit and Coverage (SBCs)" in Employer Portal My Tools Page 
Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids"
Then user verifies the documents based on the "GroupId", "countofSubgroupids" , "MedicalKey","PharmacyKey", "<MemberType>" and "DateVal"

@PAQHPEMPLOYERSBCLINKSSTAGE @GUISTAGE @QHPSBCSTAGE 
Examples:
		| GroupIdValue | MemberType | Password |
		| 10000005 |PPO| Today1234 |


 

