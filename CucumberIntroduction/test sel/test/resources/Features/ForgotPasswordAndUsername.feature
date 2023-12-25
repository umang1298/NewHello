@FORGOTUSERNAMEANDPASSOWRD
Feature: Regression Test Scenaios - Forgot Username and Password

Scenario Outline: Reg No - <Regression case No> - Forgot my Password for Member - Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "MemberRecordNumber" key to value "<username>"
Given user launches the portal "ForgotPasswordPage"
Then user selects "yes" for the question in the forgot password page
Given user sets the SOAP request xml for MemberService:
|Member Id| As of date | Path to write XML |
|MemberRecordNumber| DateVal | XMLs/GetContractByMemberRecordNumber.xml |  
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetContractByMemberRecordNumber.xml | XMLs/GetAllContractsBymemrecordnumberResponse.xml |
Then user enters all required values in forgot password page based on "MemberRecordNumber" and response xml "XMLs/GetAllContractsBymemrecordnumberResponse.xml"
Then user enters the security "<Answer>" and clicks submit
Then user sets the new password for the member



@FORGOTPWDMEMBER03 @DAILYREGRESSIONRUNTST03
Examples:
| Regression case No | username | Answer | Endpoint|
| ForgotPasswordMember | 3600564 | A | http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
#| ForgotPasswordMember | 10677079 | A | http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|
@FORGOTPWDMEMBER02 @DAILYREGRESSIONRUNTST02
Examples:
| Regression case No | username |Answer | Endpoint|
| ForgotPasswordMember | 2784290 | A | https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Forgot Username for Producer - Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ForgotUserNamePage"
Given user enters "<EmailAddress>" and SSN "<SSN>" and NPN "<NPN>" in producer Forgot username page
Then user enters the "<SecurityAnswer>" for challenge question and validates user name is displayed

@PRODUCERFORGOTUSERNAME03 @DAILYREGRESSIONRUNTST03
Examples:
| Regression case No | EmailAddress | SSN  | NPN      | SecurityAnswer |
| ProducerForgotUsername01 | test@hap.org | 7981 | 16923117 | 			A					|

@PRODUCERFORGOTUSERNAME02 @DAILYREGRESSIONRUNTST02
Examples:
| Regression case No | EmailAddress | SSN  | NPN      | SecurityAnswer |
| ProducerForgotUsername | test@hap.org | 7981 | 16923117 | 			A					|

Scenario Outline: Reg No - <Regression case No> - Forgot Username for Prospective Member - Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ProspectForgotUsernamePage"
Given user enters "<EmailAddress>" and SSN "<SSN>" and DOB "<DOB>" in Prospective Member Forgot username page
Then user enters the "<SecurityAnswer>" for challenge question for Prospect Member and validates user name is displayed

@PROSPECTFORGOTUSERNAME03 @DAILYREGRESSIONRUNTST03
Examples:
| Regression case No | EmailAddress | SSN  | DOB      | SecurityAnswer |
| ProspectiveMemberForgotUsername01 | test@hap.org | 9993 | 09/09/1999 | 			A					|

@PROSPECTFORGOTUSERNAME02 @DAILYREGRESSIONRUNTST02
Examples:
| Regression case No | EmailAddress | SSN  | DOB      | SecurityAnswer |
| ProspectiveMemberForgotUsername01 | test@hap.org | 9993 | 09/09/1999 | 			A					|

Scenario Outline: Reg No - <Regression case No> - Forgot my Password for Provider - Userid- <NPI>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ForgotPasswordPage"
Then user selects "no" for the question in the forgot password page
Then user enters Registerd username "<NPI>"
Then user enters Primary "<Email>" in email ID TextBox
Then user enters the security "<Answer>" for Provider and clicks submit
Then user sets the new password for the provider

@FORGOTPWDPROVIDER03 @DAILYREGRESSIONRUNTST03
Examples:
| Regression case No | NPI | Answer | Email |
| ForgotPasswordProvider | 1982634499_IDADMIN | A | test@hap.org |
| ForgotPasswordVendor | 55502D_IDADMIN | A | test@hap.org |

@FORGOTPWDPROVIDER02 @DAILYREGRESSIONRUNTST02
Examples:
| Regression case No | NPI | Answer | Email |
| ForgotPasswordProvider | 1982634499_IDADMIN | A | test@hap.org |
| ForgotPasswordVendor | 55502D_IDADMIN | A | test@hap.org |



Scenario Outline: Reg No - <Regression case No> - Forgot my Password for Producer - Userid- "<ProducerUsername>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ForgotPasswordPage"
Then user selects "no" for the question in the forgot password page
Then user enters Registerd username "<ProducerUsername>" 
Then user enters Primary "<Email>" in the email ID TextBox and enters SSN "<SSN>" in SSN Text Box
Then user enters the security "<Answer>" for Producer and clicks submit
Then user sets the new password for the producer


@FORGOTPWDPRODUCER03 @DAILYREGRESSIONRUNTST03
Examples:
| Regression case No     | ProducerUsername |  SSN	 | Answer |   Email      |
| ForgotPasswordProducer | TestAutomation96 |  6263  |   A    | test@hap.org |

@FORGOTPWDPROVIDER02 @DAILYREGRESSIONRUNTST02
Examples:
| Regression case No     | ProducerUsername |   SSN		 | Answer |   Email      |
| ForgotPasswordProducer | TestAutomation96 |   6263   |   A    | test@hap.org |


Scenario Outline: Reg No - <Regression case No> - Forgot my Password for Prospective Member - Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user launches the portal "ForgotPasswordPage"
Then user selects "no" for the question in the forgot password page
Then user enters Registerd username "<username>"
Then user enters the "<DOB>" and "<SSN>" of prospective user "<Answer>"
Then user sets the new password for the prospective member

@PROSPECTIVEFORGOTPASSWORD03 @DAILYREGRESSIONRUNTST03
Examples:
|Regression case No|username|SSN|DOB|Answer|
|ProspectForgotPassword|Teste801|4234|08/06/1960|A|

@PROSPECTIVEFORGOTPASSWORD02 @DAILYREGRESSIONRUNTST02
Examples:
|Regression case No|username|SSN|DOB|Answer|
|ProspectForgotPassword|Teste801|4234|08/06/1960|A|

