@ASOOLDARCH
Feature: ASO - Old Architechture - Member & Employer

Scenario Outline: Reg No - <Regression case No> - ASO - Employer - OLD ARCH -Links Verification for <MemberType> - <GroupIdValue> 
	Given user sets the "GroupId" key to value "<GroupIdValue>" 
	Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
	Given user launches the portal "Portal" 
	Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
	Then User Clicks on "My Tools" in Employer Portal Landing Page 
	Then User Clicks on "Contracts, Benefit Guides & Riders" in Employer Portal My Tools Page 
	Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
	Then user verifies the links based on the "GroupId", "countofSubgroupids" , "RenwalMonthKey", "<MemberType>" and "DateVal" 
	Then user logouts the employer portal 
	
@ASOOLDEMPLOYERSTAGE03 #@DAILYREGRESSIONRUNTST03
	Examples: 
		| Regression case No | GroupIdValue | MemberType | Password |
		| ASOEMPOLD01 | 10000666 | HMO | Today1234 |

@ASOOLDEMPLOYERSTAGE02 #@DAILYREGRESSIONRUNTST02
	Examples: 
		| Regression case No | GroupIdValue | MemberType | Password |
		| ASOEMPOLD01 | 10000666 | HMO | Today1234 |

Scenario Outline: Reg No - <Regression case No> - ASO - Member - OLD Arch - Links verification for <MemberType> - <MemberIdValue>
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

@ASOOLDMEMBERSTAGE03 @DAILYREGRESSIONRUNTST03 @FORMS03
Examples:
| Regression case No | MemberIdValue| Password |MemberType|EndpointMemberService|EndpointBenefitService|
| ASOMEMOLD01 | 3591249 | Today1234 | ASO |https://facetstest3api.hap.org/Memberservice/Memberservice.asmx|https://facetstest3api.hap.org/BenefitService/benefitService.asmx|

@ASOOLDMEMBERSTAGE02 @DAILYREGRESSIONRUNTST02 @FORMS02
Examples:
| Regression case No | MemberIdValue| Password |MemberType|EndpointMemberService|EndpointBenefitService|
| ASOMEMOLD01 | 3591249 | Today1234 | ASO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|https://facetstest2api.hap.org/BenefitService/benefitService.asmx|
