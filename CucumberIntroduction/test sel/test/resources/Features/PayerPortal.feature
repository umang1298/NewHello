@PAYERPORTAL
Feature: Regression Test Scenaios - Payer Portal

Scenario Outline: Reg No - <Regression case No> - PayerPortal Claim Search by SSN Validations for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Payer" application with user id "MemberId" and password "<password>"
Then User Clicks on "Claims" in Employer Portal My Tools Page
Then User Search using SSN
Then User search the Claims for last one year in payer portal and checks if "claimspresent"
Then user selects the "Claim Number" from Claim Summary list if "claimspresent"
Then user validates the "Claim Number" in Claim Information page if "claimspresent"
Then user validates print option if "claimspresent"

@CLAIMSEARCHSSN03 @DAILYREGRESSIONRUNTST03 @PAYER03
Examples:
| Regression case No | User Type	 | username | password |
| Payer01 |cigna|10000031|Today1234 |

@CLAIMSEARCHSSN02 @DAILYREGRESSIONRUNTST02 @PAYER02
Examples:
| Regression case No | User Type	 | username | password |
| Payer01 |cigna|10000031|Today1234 |

Scenario Outline: Reg No - <Regression case No> - PayerPortal Claim Search by Claim Number Validations for <User Type>- Userid- <username>
Given user sets the "MemberId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Payer" application with user id "MemberId" and password "<password>"
Then User Clicks on "Claims" in Employer Portal My Tools Page
Then User Search using SSN
Then User search the Claims for last one year in payer portal and checks if "claimspresent"
Then user selects the "Claim Number" from Claim Summary list if "claimspresent"
Then user validates the "Claim Number" in Claim Information page if "claimspresent"
Then user search using "Claim Number" if "claimpresent"

@CLAIMNNUMBERSEARCH03 @DAILYREGRESSIONRUNTST03 @PAYER03

Examples:
| Regression case No | User Type	 | username | password |
| Payer02 |cigna|10000031|Today1234 |

@CLAIMNNUMBERSEARCH02 @DAILYREGRESSIONRUNTST02 @PAYER02
Examples:
| Regression case No | User Type	 | username | password |
| Payer02 |cigna|10000031|Today1234 |

