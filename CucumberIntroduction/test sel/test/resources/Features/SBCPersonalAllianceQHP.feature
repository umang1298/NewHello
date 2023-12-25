@SBCPAQHP
Feature: SBC - Personal Alliance QHP Members

Scenario Outline: Reg No - <Regression case No> - Personal Alliance QHP - SBC Links verification for <MemberType> Member - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document Links for Members

@PAQHPMEMBERSBCLINKSSTAGE03  @DAILYREGRESSIONRUNTST03 @SBC03
Examples:
| Regression case No | MemberIdValue| Password |MemberType|Endpoint|
| SBCPAQHP01 |  11101551 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCPAQHP02 | 11029649 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCPAQHP03 | 10555543 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| SBCPAQHP04 | 10256450 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|


@PAQHPMEMBERSBCLINKSSTAGE02 @DAILYREGRESSIONRUNTST02 @SBC02
Examples:
| Regression case No | MemberIdValue| Password |MemberType|Endpoint|
| SBCPAQHP01 | 11101551 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCPAQHP02 | 11029649 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCPAQHP03 | 10555543 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBCPAQHP04 | 10256450 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

#Given user sets the SOAP request xml for MemberService:
#|Member Id| As of date | Path to write XML |
#|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
#Given user sends a SOAP request with the MemberService For QHP:
#| Endpoint | Path to XML| XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | XpathToMedicalProduct | XpathToPharmacyProduct | XpathToGroupID | XpathToSubGroupID | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreMedicalProduct | KeyToStorePharmacyProduct | KeyToStoreGroupID | KeyToStoreSubGroupID |
#| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].BenefitPeriods.BenefitPeriod.BenefitEndDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].GroupId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].SubgroupId | RenewalMonthKey | BenefitStartDate | BenefitEndDate | MedicalProductID | PharmacyProductID | GroupID | SubGroupID |
#Given user sets the SOAP Request xml for Pega Service for PA QHP member:
#| As of date | Path to write XML | GroupIDtoSet | SubGroupIDtoSet | MedicalproductIDtoSet | PharmacyProducttoSet |
#| DateVal | XMLs/GetBenefitDocumentsMetaDataPAQHPMember.xml | GroupID | SubGroupID | MedicalProductID | PharmacyProductID |
#Given user sends the REST POST request using the following:
#|BaseURL | BasePath | PayLoadPath | WriteResponseXMLpath |
#|BasePathPEGAUAT| /member/benefitDoc/getProductBenefitDocuments | //XMLs//GetBenefitDocumentsMetaDataPAQHPMember.xml | /XMLs/ResponsePEGAGetBenifit.xml | 
