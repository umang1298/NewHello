@EMPLOYEEPORTAL
Feature: Regression Test Scenaios - Employee Portal

Scenario Outline: Reg No - <Regression case No> - Member Eligibility Validations on Employee Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to "Member Eligibility" in Employee Portal
Then user clicks on "Click here to view Member Roster" link
Then user searches Provider with last name "muslah" and selects one and click on search
Then user gets the MemberID from search results and set the value to "MemberID"
Then user navigtes to Employee portal home page
Then user navigates to "Member Eligibility" in Employee Portal
Then user enters "MemberID" and Clicks on submit
Then user gets "SubscriberID" from "MemberID"
Given user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:
|SubscriberID| As of date | Path to write XML |
|SubscriberID| DateVal | XMLs/GetContractBySubscriberID.xml | 
Given user sends a SOAP request and Writes it to file:
| Endpoint | Path to Load XML| Path to Write Response |
| <Endpoint> | //XMLs//GetContractBySubscriberID.xml | XMLs/GetAllContractsBySubscriberIDResponse.xml | 
Then user does the validation for "MemberID" based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"
Then user clicks on Member Name Link
Then user does the validation on Member Eligibility Details page based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"
Then user navigtes to Employee portal home page
Then user navigates to "Member Eligibility" in Employee Portal
Then user enters "SubscriberID" and Clicks on submit
Then user waits for 30000 ms
Then user validates results are successfully displayed for all the dependants based on the response XML "/XMLs/GetAllContractsBySubscriberIDResponse.xml"


@EMPLOYEEMEMBERELIGIBILITY03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee01 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEEMEMBERELIGIBILITY02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee01 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Remittance Advice Validations on Employee Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to the "Remittance  Advice" in Employee portal
Then user searches "vendorId" with "<Vendor Name>" and selects one
Then user selects Reports for radio button "byPaymentDate"
Then user searches RA for one year
Then user validates results for RA search in employee portal
Then user navigates to the "Remittance  Advice" in Employee portal
Then user searches "vendorId" with "<Vendor Name>" and selects one
Then user selects Reports for radio button "byDateOfService"
Then user searches RA for one year
Then user validates results for RA search in employee portal
#Then user takes "VendorId" and "Payment Number" from search results
#Then user navigates to the "Remittance Advice"
#Then user searches RA with "VendorId"
#Then user searches RA for one year 
 #for vendor Id
#Then user validates results for RA search
#Then user navigates to the "Remittance Advice"
#Then user searches RA with "Payment Number"
 #for Payment Number
#Then user validates results for RA search

@EMPLOYEEREMITTANCEADVICE03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password | Vendor Name |Endpoint|
| Employee02 | asivakum@hap.org | Neyveli&85 | Andrea |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEEREMITTANCEADVICE02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password | Vendor Name |Endpoint|
| Employee02 | asivakum@hap.org | Neyveli&85 | Teresa |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Benefit Admin Manual Validations on Employee Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to "Benefit Admin Manual" in Employee Portal
Then user searches Policy by text and validates search results are displayed
Then user searches Policy by Policy Index and Validates the search results are displayed 
Then user searches Policy by Categorical Index and validates the Searched results are displayed
Then user searches Policy by Recent Changes and validate searched Results are displayed

@EMPLOYEEBENEFITADMINMANUAL03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee03 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEEBENEFITADMINMANUAL02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee03 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Code Editing Explanation Validations on Employee Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to "Code Editing Explanation" in Employee Portal
Then user verifies terms and conditions are opened in new tab

@EMPLOYEECODEEDITINGEXPLANATION03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee04 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEECODEEDITINGEXPLANATION02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee04 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Search for a Doctor/Facility Validations on Employee Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to "Search for a Doctor or Facility" in Employee Portal
Then user verifies successfull landing in the provider online lookup page

@EMPLOYEESEARCHFORDOCTOR03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee05 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEESEARCHFORDOCTOR02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee05 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - Large Group Quoting Tool (Search for an Existing Quote) Validations on Employee Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to the "LG Alternate Quoting Tool" in Employee portal
Given user sets the "Company Name Value" key to value "FORD"
Then user performs search for an existing quote with "Company Name" and value as "Company Name Value"
Then user takes "Quote ID" and "Group ID" from search results for Company Name
Then user validates search result table is displayed
Then user navigates to the "LG Alternate Quoting Tool" in Employee portal
Then user performs search for an existing quote with "Quote ID" and value as "Quote ID"
Then user validates Quote details page is displayed for "Quote ID"
Then user validates "SBC", "Benefit Summary" and "View Rate Sheet" Functionalities
Then user Clicks Back to search button
Then user navigates to the "LG Alternate Quoting Tool" in Employee portal
Then user performs search for an existing quote with "Group ID" and value as "Group ID"
Then user validates search result table is displayed
Then user navigates to the "LG Alternate Quoting Tool" in Employee portal
Then user performs search for an existing quote with "Company Name" and value as "Company Name Value"
Then user Clicks "Quote ID" link in the search results
Then user validates Quote details page is displayed for "Quote ID"
Then user validates "SBC", "Benefit Summary" and "View Rate Sheet" Functionalities
Then user Clicks Back to search button


@EMPLOYEELGALTERNATEQUOTINGTOOLEXISTINGQUOTE03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee06 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEELGALTERNATEQUOTINGTOOLEXISTINGQUOTE02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee06 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - Large Group Quoting Tool (Browse Catalog) Validations on Employee Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to the "LG Alternate Quoting Tool" in Employee portal
Then user clicks on Browse catalog option
Then user selects Plan attributes Line of Business as "HMO" and HSA as "Yes"
Then user sets range value for Medical plan benefits and validates slider functionalities
Then user clicks search button in browse catalog page
Then user validates search results page is loaded
Then user modifies search criteria in the left side filter by options
Then user validates search results page is loaded

@EMPLOYEELGALTERNATEQUOTINGTOOLBROWSECATALOG03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee07 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEELGALTERNATEQUOTINGTOOLBROWSECATALOG02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee07 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - HEDIS Member outreach (Gaps Search) Validations on Employee Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "More" in Employee portal
Then user navigates to the HEDIS Member Outreach in Employee portal
And user clicks "Gap Search"
Then user searches for MemberId with "<MemberFirstName>" and "<MemberLastName>" and selects one
Then user validates HEDIS gaps results are displayed

@EMPLOYEEHEDISMEMBEROUTREACHGAPSEARCH03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password | MemberFirstName | MemberLastName | Endpoint|
| Employee08 | asivakum@hap.org | Neyveli&85 | Harmina | Bullock |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEEHEDISMEMBEROUTREACHGAPSEARCH02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password | MemberFirstName | MemberLastName | Endpoint|
| Employee08 | asivakum@hap.org | Neyveli&85 | Harmina | Bullock |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|


Scenario Outline: Reg No - <Regression case No> - HEDIS Member outreach (Advance Search And Download) Validations on Employee Portal for Userid - <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to the HEDIS Member Outreach in Employee portal
And user clicks "Advance Search And Download"
Then user creates Target list of the member with Hedis measures
Then user executes the saved target list and verifies the results


@EMPLOYEEHEDISMEMBEROUTREACHADVANCESEARCH03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |Endpoint|
| Employee09 | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|

@EMPLOYEEHEDISMEMBEROUTREACHADVANCESEARCH02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |Endpoint|
| Employee09 | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx|

Scenario Outline: Reg No - <Regression case No> - DNSP Search Validations on Employee Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to the "DSNP Provider Search" in Employee portal
And user clicks option "DSNP Search Page"
And user selects "Provider Name" and search with "<providername>"
Then user validates Provider name search result page
Then user navigates to the "DSNP Provider Search" in Employee portal
And user clicks option "DSNP Search Page"
And user selects "NPI" and search with "<NPI>"
Then user validates Provider name search result page

@DNSPSEARCH03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |providername|NPI|
| Employee10 | asivakum@hap.org | Neyveli&85 |Andrea|18413|

@DNSPSEARCH02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |providername|NPI|
| Employee10 | asivakum@hap.org | Neyveli&85 |Andrea|18413|

Scenario Outline: Reg No - <Regression case No> - DNSP Advanced Search Validations on Employee Portal for <User Type>- Userid- <username>
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "EmployeeId" key to value "<username>"
Given user launches the portal "EmployeePortal"
Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
Then user navigates to the "DSNP Provider Search" in Employee portal
And user clicks option "Advanced Search and Download"
And user Run the DNSP Traning Report
And user Save the DNSP Traning Report
Then user validates saved Target list name and delete from the list
#Then user down the DNSP Training Report
Then user navigates to the "DSNP Provider Search" in Employee portal
And user clicks option "Dashboard"
Then user validates the DNSP Dashboard

@DNSPADVANCEDEARCH03 @DAILYREGRESSIONRUNTST03 @EMPLOYEE03
Examples:
| Regression case No | username | password |providername|NPI|
| Employee11 | asivakum@hap.org | Neyveli&85 |Andrea|18413|

@DNSPADVANCEDEARCH02 @DAILYREGRESSIONRUNTST02 @EMPLOYEE02
Examples:
| Regression case No | username | password |providername|NPI|
| Employee11 | asivakum@hap.org | Neyveli&85 |Andrea|18413|



#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Provider <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user close browser
#Given user sets the "UserId" key to value "<userId>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal HAP application with user id "UserId" and password "<NewPassword>"
#Then user navigates to "Update Profile" 
#Then user enters the "<NewPassword>" and updates the "<ActualPassword>" and "<ActualPassword>"
#
#
#Prospectmbr #Employers #Members #Agencies/Agents #Tpa #Ipa 
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPROVIDER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee12         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| 1801821988_IDADMIN | Providers/Vendors | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPROVIDER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee12         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| 1801821988_IDADMIN | Providers/Vendors | Today123 | Today123 | Today1234 |
#
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for IPA Child user <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user close browser
#Given user sets the "UserId" key to value "<userId>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "UserId" and password "<NewPassword>"
#Then user navigates to "Update Profile" 
#Then user changes password to the old password
#
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDIPACHILDUSER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee13         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| oh1_tautomation | Ipa | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDIPACHILDUSER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee13         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| oh1_tautomation | Ipa | Today123 | Today123 | Today1234 |
#
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for IPA Admin User <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user close browser
#Given user sets the "UserId" key to value "<userId>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "UserId" and password "<NewPassword>"
#Then user navigates to "Update Profile" 
#Then user changes password to the old password
#
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDIPAADMINUSER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee14         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| os1_admin | Ipa | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDIPAADMINUSER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee14         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| os1_admin | Ipa | Today123 | Today123 | Today1234 |
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Agent <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user close browser
#Given user sets the "UserId" key to value "<userId>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal "Provider" application with user id "UserId" and password "<NewPassword>"
#Then user navigates to "Change Password"
#Then user enters the "<NewPassword>" and updates the "<ActualPassword>" and "<ActualPassword>" and validates update password correctly
#
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDAGENT03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee15         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| mvanden9 | Agencies/Agents | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDAGENT02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee15         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| mvanden9 | Agencies/Agents | Today123 | Today123 | Today1234 |
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Employer <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the Employee Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user close browser
#Given user sets the "UserId" key to value "<userId>"
#Given user launches the portal "Portal"
#Given user logs in to the SPortal HAP application with user id "UserId" and password "<NewPassword>"
#Then user navigates to the "Update Profile" Page
#Then user enters the "<NewPassword>" and updates "<ActualPassword>" and "<ActualPassword>"
#
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDEMPLOYER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee16         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| 10002674 | Employers | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDEMPLOYER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee16         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| 10002674 | Employers | Today123 | Today123 | Today1234 |
#
#
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Payer <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user hits the URL "PortalUrl"
#Given user sets the "UserId" key to value "<userId>"
#Given user logs in to the SPortal "Provider" application with user id "UserId" and password "<NewPassword>"
#Then user logs out
#Then user hits the URL "EmployeePortalURL"
#Then user clicks signs in with organisational account
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<ActualPassword>" and "<ActualPassword>" and validates the password updatedsuccessfully
#
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPAYER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee17         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| 10000031 | Tpa | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPAYER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee17         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| 10000031 | Tpa | Today123 | Today123 | Today1234 |
#
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Prospective Member Part 1 <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the Employee Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user hits the URL "PortalUrl"
#Given user sets the "UserId" key to value "<userId>"
#Given user logs in to the SPortal HAP application with user id "UserId" and password "<NewPassword>"
#Then user logs out the Prospective member page
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPROSPECTIVEMEMBERP103 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee18         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| Test619 | Prospectmbr | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPROSPECTIVEMEMBERP102 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee18         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| Test619 | Prospectmbr | Today123 | Today123 | Today1234 |
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Prospective Member Part 2 <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<ActualPassword>" and "<ActualPassword>" and validates the password updatedsuccessfully
#
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPROSPECTIVEMEMBERP203 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee19         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| Test619 | Prospectmbr | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDPROSPECTIVEMEMBERP202 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee19         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| Test619 | Prospectmbr | Today123 | Today123 | Today1234 |
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Password on Employee Portal for Member <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the Employee Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user updates "<NewPassword>" and "<ConfirmPassword>" and validates the password updatedsuccessfully
#Then user verifies the HAP logo and the LogOut Button
#Then user hits the URL "PortalUrl"
#Given user sets the "UserId" key to value "<userId>"
#Given user logs in to the SPortal HAP application with user id "UserId" and password "<NewPassword>"
#Then user navigates to "Update Profile" 
#Then user enters "<NewPassword>" and  updates a "<ActualPassword>" and "<ActualPassword>" and validates the password are updated 
#Then user verifies the HAP logo and the LogOut Button
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDMEMBER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword | 
#| Employee20         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| 11278309 | Members | Today123 | Today123 | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEPASSWORDMEMBER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | NewPassword | ConfirmPassword | ActualPassword |
#| Employee20         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| 11278309 | Members | Today123 | Today123 | Today1234 |
#
#
#
#Scenario Outline: Reg No - <Regression case No> - Security Admin Tool Update Application on Employee Portal for Provider/Vendor  <User Type>- Userid- <username>
#Given user sets the value of date to 0 days from now and saves it to "DateVal" key
#Given user sets the "EmployeeId" key to value "<username>"
#Given user launches the portal "EmployeePortal"
#Then user clicks signs in with organisational account
#Given user logs in to the "Employee" Portal with user id "EmployeeId" and password "<password>"
#Then user navigates to the "Security Admin Tool" in Employee portal
#Then user selects the "<UserType>" enters the "<userId>" and validates the Search results
#Then user selects the update application and add or remove the application & validates its successfully updated
#Then user verifies the HAP logo and the LogOut Button
#Then user hits the URL "PortalUrl"
#Given user sets the "UserId" key to value "<userId>"
#Given user logs in to the SPortal "Provider" application with user id "UserId" and password "<Password>"
#Then user validates whether the application is added or removed successfully 
#Then user verifies the HAP logo and the LogOut Button
#
#@EMPLOYEESECURITYADMINTOOLUPDATEAPPLICATIONPROVIDER03 @DAILYREGRESSIONRUNTST03
#Examples:
#| Regression case No | username         | password   |Endpoint| userId | UserType | Password |
#| Employee21         | asivakum@hap.org | Neyveli&85 |http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| 1801821988_IDADMIN | Providers/Vendors | Today1234 |
#
#@EMPLOYEESECURITYADMINTOOLUPDATEAPPLICATIONPROVIDER02 @DAILYREGRESSIONRUNTST02
#Examples:
#| Regression case No | username          | password   |Endpoint| userId | UserType | Password |
#| Employee21         | asivakum@hap.org  | Neyveli&85 |https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| 1801821988_IDADMIN | Providers/Vendors | Today1234 |
