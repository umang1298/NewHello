@REGISTRATIONS
Feature: Regression Test Scenaios - Registrations

Scenario Outline: Reg No - <Regression case No> - Member Registration Userid- "<MEME_RECORD_NO>"
Given user launches the portal "MemberRegistrationPortal" 
Given user sets the "memrecordnumber" key to value "<MEME_RECORD_NO>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|memrecordnumber| DateVal | XMLs/GetContractByMemberRecordNumber.xml |
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <EndpointBenefit> | //XMLs//GetContractByMemberRecordNumber.xml | XMLs/GetAllContractsBymemrecordnumberResponse.xml |
Then user creates member from "XMLs/GetAllContractsBymemrecordnumberResponse.xml" for "<MEME_RECORD_NO>" and "<password>"
Given user sets the "memrecordnumber" key to value "<MEME_RECORD_NO>"
Given user launches the portal "ForgotPasswordPage"
Then user selects "yes" for the question in the forgot password page
Then user enters all required values in forgot password page based on "memrecordnumber" and response xml "XMLs/GetAllContractsBymemrecordnumberResponse.xml"
Then user enters wrong security answer
@MEMBERCREATIONS03 @DAILYREGRESSIONRUNTST03
Examples:
|Regression case No|MEME_RECORD_NO|EndpointBenefit|password|
|MemberRegistration01|10333686|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|Today1234|
#|MemberRegistration01|10677079|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|Today1234|

#10677079
@MEMBERCREATIONS02 @DAILYREGRESSIONRUNTST02
Examples:
|Regression case No|MEME_RECORD_NO|EndpointBenefit|password|
|MemberRegistration01|10584955|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|Today1234|
#10584955

Scenario Outline:  Reg No - <Regression case No> - ProspectiveRegistration Userid- "<MEME_RECORD_NO>"
Given user launches the portal "ProspectiveRegistrationPage" 
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Then user clicks on Register Button
Then user enters required values to create member based on "<username>" and "<lastname>" and "<firstname>" and "<SSN>" and "<DOB>" and "<password>"
Then user close browser
Given user launches the portal "ForgotPasswordPage"
Then user selects "no" for the question in the forgot password page
Then user enters Registerd username "<username>"
Then user enters wrong security answer to unregister prospective member "<DOB>" and "<SSN>"

@PROSPECTIVEMEMBERCREATIONS03 #@DAILYREGRESSIONRUNTST03
Examples:
|Regression case No|username|lastname|firstname|SSN|DOB|password|
|ProspectiveRegistration01|Test99993|Test|Automation|4234|08/06/1960|Today1234|

@PROSPECTIVEMEMBERCREATIONS02 #@DAILYREGRESSIONRUNTST02
Examples:
|Regression case No|username|lastname|firstname|SSN|DOB|password|
|ProspectiveRegistration01|Test99993|Test|Automation|4234|08/06/1960|Today1234|

Scenario Outline: Reg No - <Regression case No> - Producer Registration - Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ProducerRegistrationPage"
Then user enters the "<username>" and "<FirstName>" and  "<LastName>" and "<SSN>" and "<NPN>" 
Then user enters "<EmailAddress>" and "<password>"
Then user creates challenge question & answer and verifies the producer user registration  
Given user launches the portal "ForgotPasswordPage"
Then user selects "no" for the question in the forgot password page
Then user enters Registerd username "<username>"
Then user enters "<EmailAddress>" and "<SSN>" and wrong security answer to unregister the producer

@PRODUCERREGISTRATION03 @DAILYREGRESSIONRUNTST03
Examples:
| Regression case No |     username     | FirstName | LastName | EmailAddress | password  |  SSN  | NPN      | 
|    ProducerRegistration01      | TestAutomation99 |  John  |  Lee | test@hap.org | Today1234 |  2869 | 9706478 | 
|    ProducerRegistration02      | TestAutomation19 |  Trisha  |  Williams | test@hap.org | Today1234 |  1185 | 17085792 |
|    ProducerRegistration03      | TestAutomation29 |  Bruce  |  Boyles | test@hap.org | Today1234 |  6823 | 1142287 |
|    ProducerRegistration04      | TestAutomation39 |  Arleen  |  Coffee | test@hap.org | Today1234 |  6186 | 19453327 |
|    ProducerRegistration05      | TestAutomation49 |  David  |  Deaton | test@hap.org | Today1234 |  4403 | 17520984 |

@PRODUCERREGISTRATION02 @DAILYREGRESSIONRUNTST02
Examples:
| Regression case No |     username     | FirstName | LastName | EmailAddress | password  |  SSN  | NPN      | 
|    ProducerRegistration01      | TestAutomation99 |  John  |  Lee | test@hap.org | Today1234 |  2869 | 9706478 | 
|    ProducerRegistration02      | TestAutomation19 |  Trisha  |  Williams | test@hap.org | Today1234 |  1185 | 17085792 |
|    ProducerRegistration03      | TestAutomation29 |  Bruce  |  Boyles | test@hap.org | Today1234 |  6823 | 1142287 |
|    ProducerRegistration04      | TestAutomation39 |  Arleen  |  Coffee | test@hap.org | Today1234 |  6186 | 19453327 |
|    ProducerRegistration05      | TestAutomation49 |  David  |  Deaton | test@hap.org | Today1234 |  4403 | 17520984 |



Scenario Outline: Reg No - <Regression case No> - Provider Registration - <Type> - Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ProviderRegistrationPage"
Then user selects option for Tell us about organisation:
| ContractedToHAP | OfficeType | RemittanceAdvice |
| <ContractedToHAP> | <OfficeType> | <RemittanceAdvice> | 
Then user fill details for your information section with "<NPI>" and "<TaxID>" and "<VendorId>" based on "<Type>"
Then user enters details for ID Administration Information and Consent
Then user deletes the record from Manage Users Table for "<NPI>" and "<VendorId>"

#@PROVIDERREGISTRATION02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | Type | ContractedToHAP | OfficeType | RemittanceAdvice |NPI | TaxID | VendorId |
#| Provider&VendorReg01 | Individual with RA | contracted | individualprovider | rayes | 1730508953 | 386006309 | 50101349 |
#| Provider&VendorReg02 | Individual with RA | contracted | individualprovider | rayes | 1326085820 | 383474766 | 50077022 |
#| Provider&VendorReg03 | Group with RA | contracted | physiciangroup | rayes | 1285676692 | 760376595 | 50143679 |
#| Provider&VendorReg04 | Ancillary with RA | contracted | ancillary | rayes | 1902847833 | 760376595 | 50143679 |
#| Provider&VendorReg05 | Hospital with RA | contracted | hospitalfacility | rayes | 1346241775 | 382971642 | 87044A |
#| VendorReg01 | Billing Office | contracted | billingoffice | NA | 1447804604 | 381908328 | 50081464 |
#| VendorReg02 | Non Contracted with RA | nonContracted | NA | rayes2 | 1447804604 | 381908328 | 50081464 |
#| ProviderReg01 | Individual without RA | contracted | individualprovider | rano | 1730508953 | 386006309 | 50101349 |
#| ProviderReg02 | Individual without RA | contracted | individualprovider | rano | 1326085820 | 383474766 | 50077022 |
#| ProviderReg03 | Group without RA | contracted | physiciangroup | rano | 1285676692 | 760376595 | 50143679 |
#| ProviderReg04 | Ancillary without RA | contracted | ancillary | rano | 1902847833 | 760376595 | 50143679 |
#| ProviderReg05 | Hospital without RA | contracted | hospitalfacility | rano | 1346241775 | 382971642 | 87044A |
#
#
#@PROVIDERREGISTRATION03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | Type | ContractedToHAP | OfficeType | RemittanceAdvice |NPI | TaxID | VendorId |
#| Provider&VendorReg01 | Individual with RA | contracted | individualprovider | rayes | 1730508953 | 386006309 | 50101349 |
#| Provider&VendorReg02 | Individual with RA | contracted | individualprovider | rayes | 1326085820 | 383474766 | 50077022 |
#| Provider&VendorReg03 | Group with RA | contracted | physiciangroup | rayes | 1285676692 | 760376595 | 50143679 |
#| Provider&VendorReg04 | Ancillary with RA | contracted | ancillary | rayes | 1902847833 | 760376595 | 50143679 |
#| Provider&VendorReg05 | Hospital with RA | contracted | hospitalfacility | rayes | 1346241775 | 382971642 | 87044A |
#| VendorReg01 | Billing Office | contracted | billingoffice | NA | 1447804604 | 381908328 | 50081464 |
#| VendorReg02 | Non Contracted with RA | nonContracted | NA | rayes2 | 1447804604 | 381908328 | 50081464 |
#| ProviderReg01 | Individual without RA | contracted | individualprovider | rano | 1730508953 | 386006309 | 50101349 |
#| ProviderReg02 | Individual without RA | contracted | individualprovider | rano | 1326085820 | 383474766 | 50077022 |
#| ProviderReg03 | Group without RA | contracted | physiciangroup | rano | 1285676692 | 760376595 | 50143679 |
#| ProviderReg04 | Ancillary without RA | contracted | ancillary | rano | 1902847833 | 760376595 | 50143679 |
#| ProviderReg05 | Hospital without RA | contracted | hospitalfacility | rano | 1346241775 | 382971642 | 87044A |
#
#@MEMBERCREATIONEXCEL
#Scenario: Member Registration Userid- "<MEME_RECORD_NO>"
#Given user launches the portal "MemberRegistrationPortal" 
#Then get number of members present in spreadsheet 0
#Then user registers the member for the number of rows 0 in data sheet

