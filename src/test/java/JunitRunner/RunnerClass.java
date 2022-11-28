package JunitRunner;
import java.io.IOException;

import org.junit.runner.RunWith;

import Stepdefs_Jupiter_Toys.Utilities;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Jupiter_Toys-Demo", 
glue = "Stepdefs_Jupiter_Toys",
monochrome=true,
plugin = {"pretty","html:target/Reports/cucumberReport.html"},
tags = "@shop")
public class RunnerClass {
	

}
