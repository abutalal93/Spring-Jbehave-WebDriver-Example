package org.google.web.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends DriverProvider {

	@FindBy(how = How.CSS, using = "h3.r")
	private List<WebElement> searchResultTitles;

	private List<String> resultTitles = new ArrayList<String>();

	public SearchResultsPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}

	@Then("<query> is shown in the searchresulttitles")
	public void verifyTitle(@Named("query") String query)
			throws InterruptedException {
		Iterator<WebElement> itr = searchResultTitles.iterator();
		while (itr.hasNext()) {
			WebElement element = itr.next();
			System.out.println(element.getText());
			resultTitles.add(element.getText());
		}
		assertThat(resultTitles, hasItem(containsString(query)));
	}

}