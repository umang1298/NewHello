package com.qa.stepDefinitions;

import java.util.List;
import java.util.Properties;

import com.qa.base.TestBase;

import com.qa.pages.MemberRosterPage;

import io.cucumber.java.en.Then;

public class MemberRosterPageSteps extends TestBase{

	MemberRosterPage memberRosterPage = new MemberRosterPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user selects provider from the drop down and Clicks Search")
	public void user_selects_provider_from_the_drop_down_and_Clicks_Search() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberRosterPage.selectvaluefromDropdownandClickSearch();
	}
	
	@Then("user gets the MemberID from search results and set the value to {string}")
	public void user_gets_the_MemberID_from_search_results_and_set_the_value_to(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		memberRosterPage.getmemberid(MemberID);
	}
	
	@Then("user gets {string} from {string}")
	public void user_gets_from(String SubscriberID, String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		memberRosterPage.getsubscriberidfrommemberid(SubscriberID,MemberID);
	}
	@Then("user gets {string} and {string} from {string}")
	public void user_gets_and_from(String SubscriberID, String MemberSuffix, String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		memberRosterPage.getsubscriberidandsuffixfrommemberID(SubscriberID,MemberSuffix,MemberID);
	}
	@Then("user gets three MemberIDs from search results:")
	public void user_gets_three_MemberIDs_from_search_results(io.cucumber.datatable.DataTable dataTable) {

		List<List<String>> list = dataTable.asLists(String.class);
		String Mem1 = list.get(1).get(0);
		String Mem2 = list.get(1).get(1);
		String Mem3 = list.get(1).get(2);
		memberRosterPage.getthreememberids(Mem1,Mem2,Mem3);
		
	}
	
	
	@Then("user searches Provider with last name {string} and selects one and click on search")
	public void user_searches_Provider_with_last_name_and_selects_one_and_click_on_search(String lastname) {
	    // Write code here that turns the phrase above into concrete actions
		memberRosterPage.usersearchesproviderandselectsone(lastname);
		memberRosterPage.userclickssearchbutton();
	}
	

	@Then("user searches Provider with last name {string} and selects one")
	public void user_searches_Provider_with_last_name_and_selects_one(String lastname) {
	    // Write code here that turns the phrase above into concrete actions
		memberRosterPage.usersearchesproviderandselectsone(lastname);
	}

}
