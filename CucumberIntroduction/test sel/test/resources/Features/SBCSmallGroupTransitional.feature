@SBCSGTRANS
Feature: SBC - Small Group Transitional Members  

Scenario Outline: Reg No - <Regression case No> - Small Group Transitional Members- SBC Links verification for <MemberType> Member - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document Links for Members

@SGTRANSITIONALMEMBERSBCLINKSSTAGE03 @DAILYREGRESSIONRUNTST03 @SBC03
Examples:
| Regression case No | Usertype|MemberIdValue|Password|MemberType|Endpoint|
| SBCSGTRANS01 | Member| 10683886 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS02 | Member| 4266635 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS03 | Member| 3917742 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS04 | Member| 952823 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS05 | Member| 10302939 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS06 | Member| 4234591 | Today1234 | EPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@SGTRANSITIONALMEMBERSBCLINKSSTAGE02 @DAILYREGRESSIONRUNTST02 @SBC02
Examples:
|Regression case No | Usertype|MemberIdValue|Password|MemberType|Endpoint|
| SBCSGTRANS01 | Member| 10683886 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS02 | Member| 4266635 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS03 | Member| 3917742 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS04 | Member| 952823 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS05 | Member| 10302939 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCSGTRANS06 | Member| 4234591 | Today1234 | EPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


#Given user sets the SOAP request xml for MemberService:
#|Member Id| As of date | Path to write XML |
#|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
#Given user sends a SOAP request with the MemberService To get Product IDs:
#| Endpoint | Path to XML| XPathToMedicalProduct | XPathToPharmacyProduct | KeyToStoreMedicalProduct | KeyToStorePharmacyProduct | 
#| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].ProductId | MedicalProductID | PharmacyProductID |
