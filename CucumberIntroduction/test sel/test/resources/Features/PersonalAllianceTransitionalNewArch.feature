@PATRANSNEWARCH
Feature: PA Transitional - New Architechture - Member & Employer - HMO/EPO/PPO 


Scenario Outline: Reg No - <Regression case No> - PA Transitional - New Arch - Links verification - Member - for <MemberType> - <MemberIdValue>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the "MemberId" key to value "<MemberIdValue>"
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberId| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Given user sends a SOAP request with the MemberService For QHP:
| Endpoint | Path to XML| XPathtoRenewalMonth | XpathToBenefitStartDate | XpathToBenefitEndDate | XpathToMedicalProduct | XpathToPharmacyProduct | XpathToGroupID | XpathToSubGroupID | KeyToStoreRenewalMonth | KeyToStoreBenefitStartDate | KeyToStoreBenfitEndDate | KeyToStoreMedicalProduct | KeyToStorePharmacyProduct | KeyToStoreGroupID | KeyToStoreSubGroupID |
| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].RenewalMonth | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].BenefitPeriods.BenefitPeriod.BenefitStartDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].BenefitPeriods.BenefitPeriod.BenefitEndDate | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].ProductId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].GroupId | Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].SubgroupId | RenewalMonthKey | BenefitStartDate | BenefitEndDate | MedicalProductID | PharmacyProductID | GroupID | SubGroupID |
Given user sets the SOAP Request xml for Pega Service for PA QHP member:
| As of date | Path to write XML | GroupIDtoSet | SubGroupIDtoSet | MedicalproductIDtoSet | PharmacyProducttoSet |
| DateVal | XMLs/GetBenefitDocumentsMetaDataPAQHPMember.xml | GroupID | SubGroupID | MedicalProductID | PharmacyProductID |
Given user sends the REST POST request using the following:
| BaseURL | BasePath | PayLoadPath | WriteResponseXMLpath |
| BaseURLPEGA | /member/benefitDoc/getProductBenefitDocuments | //XMLs//GetBenefitDocumentsMetaDataPAQHPMember.xml | /XMLs/ResponsePEGAGetBenifit.xml | 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<Password>"
Then user navigates to the "My Forms & Documents"
And user clicks "My Plan Documents"
Then verify the Benefit Year based on the key "BenefitStartDate" and "BenefitEndDate"
Then verify Contract documents in the View forms and Documents page based on the Response XML "/XMLs/ResponsePEGAGetBenifit.xml"

@PATRANSMEMBERSTAGE03 @DAILYREGRESSIONRUNTST03 @FORMS03
Examples:
| Regression case No |MemberIdValue| Password |MemberType|Endpoint|
| PATRANSMEM01 | 2665981 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
| PATRANSMEM02 | 10031273 | Today1234 | HMO |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@PATRANSMEMBERSTAGE02 @DAILYREGRESSIONRUNTST02  @FORMS02
Examples:
| Regression case No | MemberIdValue| Password |MemberType|Endpoint|
| PATRANSMEM01 | 2665981 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|
| PATRANSMEM02 | 10031273 | Today1234 | HMO |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - PA Transitional - New Arch - Links verification- Employer- for <MemberType> - <GroupIdValue>

Given user sets the "GroupId" key to value "<GroupIdValue>" 
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Employer" application with user id "GroupId" and password "<Password>" 
Then User Clicks on "My Tools" in Employer Portal Landing Page 
Then User Clicks on "Contracts, Benefit Guides & Riders" in Employer Portal My Tools Page 
Then user gets the count of Subgroup ids and stores it to a key "countofSubgroupids" 
Then verify Group Contract documents in the View forms and Documents page based on the Response XML "GroupId", "countofSubgroupids" , "MedicalKey","PharmacyKey", "<MemberType>" and "DateVal" 

@PATRANSEMPLOYERSTAGE03 #@DAILYREGRESSIONRUNTST03
Examples:
		| Regression case No | GroupIdValue | MemberType | Password |
		| PATRANSEMP01 | 10000001 | PPO | Today1234 |
		

@PATRANSEMPLOYERSTAGE02 #@DAILYREGRESSIONRUNTST02
Examples:
		| Regression case No | GroupIdValue | MemberType | Password |
		| PATRANSEMP01 | 10000001 | PPO | Today1234 |
		
