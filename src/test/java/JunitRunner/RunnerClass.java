package JunitRunner;
import java.io.IOException;

import org.junit.runner.RunWith;

import StepdefsJupiterToys.Utilities;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/JupiterToysDemo", 
glue = "StepdefsJupiterToys",
monochrome=true,
plugin = {"pretty","html:target/Reports/cucumberReport.html"},
tags = "@shop")
public class RunnerClass {
	

}
