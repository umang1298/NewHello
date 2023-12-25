@SBCPATRANS
Feature: SBC - Personal Alliance Transitional Members

Scenario Outline: Reg No - <Regression case No> - Personal Alliance Transitional -SBC Links verification for <MemberType> - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document Links for Members

@PATRANSITIONALMEMBERSBCLINKSSTAGE03  @DAILYREGRESSIONRUNTST03 @SBC03
Examples:
| Regression case No | Usertype|MemberIdValue|Password|MemberType|Endpoint|
#| SBCPATRANS01 | Member| 904251 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCPATRANS02 | Member| 10047400 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCPATRANS03 | Member| 10047441 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCPATRANS04 | Member|3779611 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@PATRANSITIONALMEMBERSBCLINKSSTAGE02  @DAILYREGRESSIONRUNTST02 @SBC02
Examples:
| Regression case No | Usertype|MemberIdValue|Password|MemberType|Endpoint|
#| SBCPATRANS01 | Member| 904251 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCPATRANS02 | Member| 10047400 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCPATRANS03 | Member| 10047441 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCPATRANS04 | Member|3779611 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


		
#Given user sets the SOAP request xml for MemberService:
#|Member Id| As of date | Path to write XML |
#|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
#Given user sends a SOAP request with the MemberService:
#| Endpoint | Path to XML| XpathtoProductID | XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreProductID |
#| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPeriods.BenefitPeriod.BenefitEndDate | RenewalMonthKey | BenefitStartDate | BenefitEndDate | ProductID |


