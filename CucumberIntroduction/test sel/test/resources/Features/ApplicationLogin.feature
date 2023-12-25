@GUISTAGE @HEALTHCHECKSTAGE @SMOKESTAGE
Feature: Health Check for Different Portals

@TESTYOSTAGE
Scenario Outline: Member Login to HAP Portal for <User Type>- Userid- "<username>"

Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal HAP application with user id "MemberId" and password "<password>"
Then user verifies the HAP logo and the LogOut Button
Then user verifies the HAP Doctor

Examples:

| User Type	| username | password |
|Commercial | 1696591 | Today1234 |
|Medicare | 4371403 | Today1234 |
|Personal Alliance	| 484181 | Today1234 |
|Employer | 10000549 | Today1234 |
|Employer | 10000014 | Today1234 |
|Provider | 1871575704_idadmin | Today1234 |	
|Vendor | 50005803_idadmin | Today1234 |
|IPA | OH1 | Today1234 |


Scenario Outline: Member Login to HAP Portal for <User Type>- Userid- "<username>"
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal HAP application with user id "MemberId" and password "<password>"
Then user verifies the HAP logo and the LogOut Button
Then user verifies label the HAP Welcome

Examples:

| User Type	| username | password |
|Employer | 10000549 | Today1234 |

@CHECK1STAGE
Scenario Outline: Member Login to HAP Portal for <User Type>- Userid- "<username>"
Given I start the video recording for this scenario
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal HAP application with user id "MemberId" and password "<password>"
Then user verifies the HAP logo and LogOut for Agent portal
Given I stop the video recording for this scenario
Examples:

| User Type	| username | password |
| Agent	| Jadams | Today1234 |
