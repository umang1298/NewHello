@APPLICATIONMENUS @MEMBERPORTAL

Feature: Regression Test Scenaios - Member Portal Application menus

Scenario Outline: Reg No - <Regression case No> - Update Profile security validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to "Update Profile" 
Then user updates Email ID "<EmailID>" and validates the provided information displayed correctly
Then user updates Phone number and validates profile security information are displayed correctly
Then user updates Challenge question "<Challenge Question>" and validate security quesstion are updated successfull

@MEMBERUPDATEPROFILE03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit| EmailID | Challenge Question |
|MEMBER01|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx|TEST@HAP.ORG |  Q |

@MEMBERUPDATEPROFILE02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit| EmailID | Challenge Question |
|MEMBER02|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| TEST@HAP.ORG | Q |


Scenario Outline: Reg No - <Regression case No> - Update Profile paperless options validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to "Update Profile"  
Then user navigates to the "Paperless Options" page
Then user updates Email ID "<EmailID>" and validates information displayed correctly
Then user sign ups for paperless delivery of documents and validates the delivery of document type


@MEMBERPAPAERLESSOPTION03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit| EmailID |
|MEMBER03|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| NONE@HAP.ORG |

@MEMBERPAPAERLESSOPTION02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit| EmailID |
|MEMBER04|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| NONE@HAP.ORG |

Scenario Outline: Reg No - <Regression case No> - Program Consents validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to "Update Profile"  
#Then user navigates to the "Program Consents" page
Then user validates program consents page and Access history

@PROGRAMCONSENTS03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER03|Program Consents|3487227|Today123|

@PROGRAMCONSENTS02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |
|MEMBER03|Program Consents|3487227|Today123|

Scenario Outline: Reg No - <Regression case No> - Update Password validations for member <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user sets the value of date to 0 days from now and saves it to "DateVal" key 
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<password>"
Then user navigates to "Update Profile" 
Then user enters "<password>" and  updates a "<NewPassoword>" and "<NewPassoword>" and validates the password are updated 
Then user verifies the HAP logo and the LogOut Button
Then user close browser
Given user launches the portal "Portal"
Given user logs in to the SPortal "Member" application with user id "MemberId" and password "<NewPassoword>"
Then user navigates to "Update Profile" 
Then user enters "<NewPassoword>" and  updates a "<password>" and "<password>" and validates the password are updated


@MEMBERUPDATEPASSWORD03  @DAILYREGRESSIONRUNTST03 @MEMBERPORTAL03
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit| NewPassoword | 
|MEMBER05|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|http://facetstest3api.hap.org/Memberservice/Memberservice.asmx| Today123 | 

@MEMBERUPDATEPASSWORD02 @DAILYREGRESSIONRUNTST02 @MEMBERPORTAL02
Examples:
|Regression case No| User Type	 | username | password |EndpointBenefit| NewPassoword | 
|MEMBER06|Subscriberwith dependents(member contract drop down)|10003540700|Today1234|https://facetstest2api.hap.org/Memberservice/Memberservice.asmx| Today123 |

