@AGENTPORTAL
Feature: Regression Test Scenaios - Agent Portal

Scenario Outline: Reg No - <Regression case No> - AgentPortal Home Page Validations for <User Type>- Userid- <username>
#Given user sets the "AgentId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "AgentId" and password "<password>"
Then user navigates to Agen Portal "Callidus Cloud"
Then user Verifies Agent Portal menu Link
Then user navigates to Agen Portal "Medicare Enrollment"
Then user Verifies Agent Portal menu Link
Then user navigates to Agen Portal "HAP Connect"
Then user Verifies Agent Portal menu Link

@AGENTPORTALMENULINKS03  @DAILYREGRESSIONRUNTST03 @AGENT03
Examples:
| Regression case No | User Type	 | username | password |
| Agent01 |Agent|mvanden9|Today123 |

@AGENTPORTALMENULINKS02 @DAILYREGRESSIONRUNTST02 @AGENT02
Examples:
| Regression case No | User Type	 | username | password |
| Agent01 |Agent|mvanden9|Today123 |

Scenario Outline: Reg No - <Regression case No> - AgentPortal Top menus Validations for <User Type>- Userid- <username>
Given user sets the "AgentId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "AgentId" and password "<password>"
Then user validates Search Functionalty
Then user validates Announcement page
Then user navigates to "Change Password"
Then user validates Change Password Functionality for agent

@AGENTPASSWORD03  @DAILYREGRESSIONRUNTST03 @AGENT03
Examples:
| Regression case No | User Type	 | username | password |
| Agent02 |Agent|fadams|Today1234 |

@AGENTPASSWORD02  @DAILYREGRESSIONRUNTST02 @AGENT03
Examples:
| Regression case No | User Type	 | username | password |
| Agent02 |Agent|fadams|Today1234 |

Scenario Outline: Reg No - <Regression case No> - AgentPortal Home Page Links Validations for <User Type>- Userid- <username>
Given user sets the "AgentId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "AgentId" and password "<password>"
Then user navigates to "Individual Plans" and validates "Commission"
Then user navigates to "Individual Plans" and validates "Forms"
Then user navigates to "Individual Plans" and validates "Key Contacts" Page
Then user navigates to "Individual Plans" and validates "HAP Connect - How" pdf document
Then user navigates to "Individual Plans" and validates "HAP Connect - Individual" Page
Then user navigates to "Individual Plans" and validates "Marketing"
Then user navigates to "Individual Plans" and validates "Quick Links"
Then user navigates to "Individual Plans" and validates "Renewal"
Then user navigates to "Individual Plans" and validates "Summary"
Then user navigates to "Medicare Plans" and validates "Medicare Enrollment" Page
Then user navigates to "Medicare Plans" and validates "Commission"
Then user navigates to "Medicare Plans" and validates "Forms"
Then user navigates to "Medicare Plans" and validates "Key Contacts" Page
Then user navigates to "Medicare Plans" and validates "Marketing"
Then user navigates to "Medicare Plans" and validates "Medicare Advantage" pdf document
Then user navigates to "Medicare Plans" and validates "Quick Links"
Then user navigates to "Small Group Plans" and validates "Commission"
Then user navigates to "Small Group Plans" and validates "Forms"
Then user navigates to "Small Group Plans" and validates "Key Contacts" Page
Then user navigates to "Small Group Plans" and validates "HAP Connect - How" pdf document
Then user navigates to "Small Group Plans" and validates "HAP Connect - Small Group" Page
Then user navigates to "Small Group Plans" and validates "Marketing"
Then user navigates to "Small Group Plans" and validates "Quick Links"
Then user navigates to "Small Group Plans" and validates "Summary"
Then user navigates to "Small Group Plans" and validates "Quoting Tool Spreadsheet, 2020" document downloaded
Then user navigates to "Large Group Plans" and validates "Commission"
Then user navigates to "Small Group Plans" and validates "Quoting Tool Spreadsheet, 2021" document downloaded
Then user navigates to "Large Group Plans" and validates "Forms"
Then user navigates to "Large Group Plans" and validates "Key Contacts" Page
Then user navigates to "Large Group Plans" and validates "Marketing"
Then user navigates to "Large Group Plans" and validates "Quick Links"

@AGENTPORTALSTATICLINKS03 @DAILYREGRESSIONRUNTST03 @AGENT03
Examples:
|Regression case No| User Type	 | username | password |
| Agent03 |Agent|mvanden9|Today123 |

@AGENTPORTALSTATICLINKS02  @DAILYREGRESSIONRUNTST02 @AGENT02
Examples:
|Regression case No| User Type	 | username | password |
| Agent03 |Agent|mvanden9|Today123 |



Scenario Outline: Reg No - <Regression case No> - AgentPortal Update Password Validations for <User Type>- Userid- <username>
Given user sets the "AgentId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "AgentId" and password "<password>"
Then user navigates to "Change Password"
Then user enters the "<password>" and updates the "<NewPassword>" and "<NewPassword>" and validates update password correctly
Then user verifies the HAP logo and the LogOut Button
Then user close browser
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "AgentId" and password "<NewPassword>"
Then user navigates to "Change Password"
Then user enters the "<NewPassword>" and updates the "<password>" and "<password>" and validates update password correctly


@AGENTPORTALUPDATEPASSWORD03 @DAILYREGRESSIONRUNTST03 @AGENT03
Examples:
|Regression case No| User Type	 | username | password | NewPassword |
| Agent03 |Agent|mvanden9|Today123 | Today1234 |

@AGENTPORTALUPDATEPASSWORD02  @DAILYREGRESSIONRUNTST02 @AGENT02
Examples:
|Regression case No| User Type	 | username | password |  NewPassword | 
| Agent03 |Agent|mvanden9|Today123 | Today1234 | 

Scenario Outline: Reg No - <Regression case No> - AgentPortal Update Password Validations for <User Type>- Userid- <username>
Given user sets the "AgentId" key to value "<username>"
Given user launches the portal "Portal"
Given user logs in to the SPortal "Producer" application with user id "AgentId" and password "<password>"
Then user navigates to Agen Portal "HAP Connect"
Then user validates Shop for Coverage for "<User Type>"

@HAPCONNECTIVITY03 @DAILYREGRESSIONRUNTST03 @AGENT03
Examples:
|Regression case No| User Type	 | username | password | 
| Agent03 |HAP|mvanden9|Today123 | 
| Agent03 |AHL|mvanden9|Today123 | 


@HAPCONNECTIVITY02  @DAILYREGRESSIONRUNTST02 @AGENT02
Examples:
|Regression case No| User Type	 | username | password |  NewPassword | 
| Agent03 |Agent|mvanden9|Today1234 | Today123 | 

