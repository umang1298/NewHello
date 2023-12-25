@SBCSGQHP
Feature: SBC - Small Group QHP Members 

Scenario Outline: Reg No - <Regression case No> - Small Group QHP - SBC Links verification for <MemberType> Member - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Benefits"
And user clicks option "My Coverage"
And user selects "My Costs"
And user validates the SBC Document Links for Members

@SGQHPMEMBERPEGAFLOWSBCLINKSSTAGE03 @DAILYREGRESSIONRUNTST03 @SBC03
Examples:
| Regression case No | MemberIdValue|Password|MemberType|Endpoint|
| SBCSGQHP01 | 10031273 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| SBCSGQHP02 | 10409740 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@SGQHPMEMBERPEGAFLOWSBCLINKSSTAGE02 @DAILYREGRESSIONRUNTST02 @SBC02
Examples:
| Regression case No | MemberIdValue|Password|MemberType|Endpoint|
| SBCSGQHP01 | 10031273 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| SBDSGQHP02 | 10409740 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

#Scenario Outline: Small Group QHP Old Flow- Member- SBC Links verification for <MemberType> - <MemberIdValue>
#
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
#Given user sets the "MemberId" key to value "<MemberIdValue>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal HAP application with user id "MemberId" and password "<Password>"
#Then user navigates to the "My Benefits"
#And user clicks option "My Coverage"
#And user selects "My Costs"
#And user validates the SBC Document Links for Members
#
#@SGQHPMEMBEROLDFLOWSBCLINKSSTAGE03 @LINKSSTAGE03 @GUISTAGE03 @QHPSBCSTAGE03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | MemberIdValue| Password |MemberType|Endpoint|
#| SOBQHP02 | 10409740 | Today1234 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| 10418714 | Today123 | PPO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#
#@SGQHPMEMBEROLDFLOWSBCLINKSSTAGE02 @LINKSSTAGE02 @GUISTAGE02 @QHPSBCSTAGE02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | MemberIdValue| Password |MemberType|Endpoint|
#| SOBQHP02 | 10409740 | Today1234 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#| 10418714 | Today123 | PPO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
#
#
#
#PEGA FLOW
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
#
#OLDFLOW
#Given user sets the SOAP request xml for MemberService:
#|Member Id| As of date | Path to write XML |
#|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
#Given user sends a SOAP request with the MemberService To get Product IDs:
#| Endpoint | Path to XML| XPathToMedicalProduct | XPathToPharmacyProduct | KeyToStoreMedicalProduct | KeyToStorePharmacyProduct | 
#| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].ProductId | MedicalProductID | PharmacyProductID |
#
