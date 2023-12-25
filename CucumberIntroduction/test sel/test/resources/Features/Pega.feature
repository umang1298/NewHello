Feature: Pega PDF Validation


Scenario Outline: Validate PDF's
Given user launches the portal "Portal"
Given User Compares the Reference PDF "<ReferencePDF>" and PDF downloaded "<DownloadedPDF>"

@PEGAPDFVALIDATON
Examples:
| ReferencePDF | DownloadedPDF |
#| SBC_HAP EPO 1000-20 A_Rx 1.pdf | SBC_HAP EPO 1000-20 A_Rx 1.pdf |
#| SBC_HAP EPO 1000-20 A_Rx 1.pdf | BS_HAP EPO 1000-20 A.pdf |
#| SBC_HAP EPO 1000-20 A_Rx 1.pdf | SBC_HAP EPO Silver 3500 PY20.pdf |
| BS_HAP EPO 1000-20 A_Rx 1.pdf | BS_HAP EPO Silver 3500 HSA PY20_CL.pdf |


Scenario Outline: Pega Scenario - PDF Validation
 Given user sets the "UserID" key to value "<UserIDValue>"
 Given I start the video recording for this scenario
 Given user launches the portal "PegaApplication"
 Given user logs in to the Pega Application with "UserID" and "rules"
 Then user clicks on "Generate Documents"
 Then user gives "<MedicalProduct>" and "<PharmacyProduct>" input in the "MedicalName" and "RxName" text box
 Then user selects the checkbox for "<DocumentToGenerate>"
 And user clicks Generate Documents 
 Then user clicks on first result
 Then user Downloads PDF
 Then user checks the PDF is downloaded successfully "<DownloadedPDF>"
 Given User Compares the Reference PDF "<ReferencePDF>" and PDF downloaded "<DownloadedPDF>"
 Then user log off the pega session
 #Given user logs in to the Pega Application with "UserID" and "rules"
 #Then user log off the pega session
 Given I stop the video recording for this scenario
 
 @PEGASCENARIO
 Examples:
 | UserIDValue | MedicalProduct | PharmacyProduct | DocumentToGenerate | DownloadedPDF | ReferencePDF |
 | PMMIManagerSelenium |HAP EPO 1000-20 A (01-01)| Rx 1 (01-01) | SBC document | SBC_HAP EPO 1000-20 A_Rx 1.pdf | SBC_HAP EPO 1000-20 A_Rx 1.pdf |
 
 
 Scenario Outline: Rest API - JSON
 Given user launches the portal "Portal"
 Given user sets up the request json file 
|RewardsNumber|RequestFilePath|
|<RewardsNumber>| XMLs/jsonrequest.json|
Given user sends the REST POST JSON using the following 
|BaseURL|BasePath|PayloadPath|writeresponsejsonpath|
|https://gatewaydsaptst1.marriott.com/|/v2/queries/consumers/?include=consumers.contact-information,consumers.names,consumers.rewards,consumers.rewards.summary,consumers.preferences,consumers.account-activities,consumers.certificates,consumers.saved-properties,consumers.program-partners,consumers.personal-information|//XMLs//jsonrequest.json|/XMLs/ResponseMarriot.json|
 
 @RESTJSON
 Examples:
 |RewardsNumber|
 |784755862|
 
 