package Stepdefs_Jupiter_Toys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class Contact_Page extends Utilities {
		
		@Given("^launch Jupiter_Toys web application$")
		public void launch_web_application() throws IOException {
			
			//launchChromeBrowser();
			launchURL("http://jupiter.cloud.planittesting.com");

		}
		@Given("Jupiter_Toys web application landing page")
		public void verifylanding_page() {
			System.out.println("Page title is : " + driver.getTitle());
			String expectedTitle = "Jupiter Toys";
			Assert.assertEquals("Jupiter_Toys web page is present", driver.getTitle(), expectedTitle);
			
		}

		@When("^I click on \"(.*)\"$")
		public void i_click_on(String ele_key) throws IOException {
          CLICK(ele_key);

		}

		@When("^verify contact page is present$")
		public void verify_contact_page_is_present() throws IOException {
			String expected = "We welcome your feedback";
			String actual = driver.findElement(By.xpath(getPageElement("welcomeText"))).getText();
			Assert.assertEquals("landed on contact page", actual, expected);
          
		}

		@When("^I enter all required details$")
		public void enterAllDetails(DataTable testData) throws Throwable {
			
				List<String> ele_key = testData.column(0);
				List<String> data = testData.column(1);
				for (int i=0;i<ele_key.size();i++) {
					FILL_TEXTBOX(ele_key.get(i),data.get(i));

				}	
		} 

		@Then("^validate message with given name \"(.*)\"$")
		public void validate_message_with_given_name(String expectedName) throws IOException {
			
			String actual = driver.findElement(By.xpath(getPageElement("Success_msgName"))).getText();
			String textmsg = driver.findElement(By.xpath(getPageElement("Success_msgText"))).getText();
			Assert.assertTrue("feedback sent msg: "+ actual+textmsg, actual.contains(expectedName));
			System.out.println("feedback sent msg: "+ actual+textmsg);
		}
		
		@Then("^check \"(.*)\" is present$")
		public void check_is_present(String ele_key) throws IOException {
			WebElement elem = driver.findElement(By.xpath(getPageElement(ele_key)));
			Assert.assertTrue(ele_key + " is displayed", elem.isDisplayed());
			
		}

		@Then("I validate incomplete feedback message")
		public void i_validate_incomplete_feedback_message(String docString) throws IOException {
			WebElement elem = driver.findElement(By.xpath(getPageElement("incomplete_msg")));
			Assert.assertEquals("Error message " +docString+ " is displayed", elem.getText(), docString);
		}

		
		@Then("I validate below error messages in their respective fields")
		public void i_validate_below_error_messages_in_their_respective_fields(DataTable testData) throws IOException {
			List<String> ele_key = testData.column(0);
			List<String> data = testData.column(1);
			// elem [];
			for(int i=0;i<ele_key.size();i++) {
				WebElement elem=null;
				elem =  driver.findElement(By.xpath(getPageElement(ele_key.get(i))));	
				Assert.assertEquals(elem.getText()+" is displayed" , data.get(i), elem.getText());
    		}
        }
		public List<String> quantity;
		@When("I add few items in cart")
		public void i_add_few_items_in_cart(DataTable testData) throws IOException {
			List<String> ele_key = testData.column(0);
			quantity = testData.column(1);

			for (int i=0;i<ele_key.size();i++) {
				int qty = Integer.parseInt(quantity.get(i));
				for(int j=1;j<=qty;j++) {
					CLICK(ele_key.get(i));
				}


			}	
		}
		

		@When("Verify the subtotal for each product")
		public void verify_the_subtotal_for_each_product() throws IOException, InterruptedException {
		    int[] qty = new int[10];
			STfrog_price =get_price_for_each_product("ST_Stuffed_Frog_price");
			STbunny_price=get_price_for_each_product("ST_Fluffy_Bunny_price");
			STbear_price =get_price_for_each_product("ST_Valentine_Bear_price");
			
			for (int i=0;i<quantity.size();i++) {
				 
				qty[i] = Integer.parseInt(quantity.get(i));
			}
				Assert.assertTrue("subtotal of the products are correct", (Sfrog_price*qty[0]==STfrog_price) && (Sbunny_price*qty[1]==STbunny_price && (Sbear_price*qty[2]==STbear_price)));
			
		}
        public float Sfrog_price;
        public float Sbunny_price;
        public float Sbear_price;
        public float STfrog_price;
        public float STbunny_price;
        public float STbear_price;
		@When("Verify the price for each product")
		public void verify_the_price_for_each_product() throws IOException, InterruptedException {
			CLICK("shopTab");
			 Sfrog_price = get_price_for_each_product("Stuffed_Frog_price");
			 Sbunny_price = get_price_for_each_product("Fluffy_Bunny_price");
			 Sbear_price = get_price_for_each_product("Valentine_Bear_price");
            CLICK("Cart_Tab");
            Thread.sleep(1000);
			float Cfrog_price = get_price_for_each_product("Cart_Stuffed_Frog_price");
			float Cbunny_price = get_price_for_each_product("Cart_Fluffy_Bunny_price");
			float Cbear_price = get_price_for_each_product("Cart_Valentine_Bear_price");
			
			
			Assert.assertTrue("prices in shopping page and cart page are same", (Sfrog_price==Cfrog_price) && (Sbunny_price==Cbunny_price) && (Sbear_price==Cbear_price));
		}

		@When("verify total is sum of subtotals")
		public void verify_toal_is_sum_of_subtotals() throws IOException, InterruptedException {
         String totalRate = GET_TEXT("Total_amount");
         String amt[]=totalRate.split(" ");
         Float cartvalue = Float.parseFloat(amt[1]);
         
         Assert.assertTrue("Cart Value is: "+cartvalue, (STfrog_price+STbunny_price+STbear_price)==cartvalue);
         
		}

		@After
		public void afterScenario() throws IOException, InterruptedException {
			Thread.sleep(3000);
		//	driver.quit();
			System.out.println("after hooks completed");
		}
		@AfterStep
		public void afterStep(Scenario s) throws IOException {
			long ms = System.currentTimeMillis();
			s.attach(capture_screen(), "image/png", "Scn_"+ms );
		}
		
		@Before
		public void beforeAll() {
			launchChromeBrowser();
		}

}
