@RESPONSIVE
Feature: Responsive site Testing

Scenario Outline: Reg No - <Regression case No> - Responsive Site testing Login
Given user sets the value of date to 0 days from now and saves it to "DateVal" key
Given user sets the "ProviderId" key to value "<Providerusername>"
Given user launches the portal "Portal"
Given user logs in to the SPortal HAP application with user id "ProviderId" and password "<password>"
Then user Logouts the session

@RESPONSIVELOGIN
Examples:
| Regression case No |  Providerusername  | password |
| Responsive01 |  1871575704_idadmin | Today1234 |
