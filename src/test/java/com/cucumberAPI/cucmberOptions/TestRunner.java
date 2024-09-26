package com.cucumberAPI.cucmberOptions;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/cucumberAPI/featureFile",plugin = "json:target/jsonReports/cucumber-report.json",
        glue={"com/cucumberAPI/stepDefinitions"})
public class TestRunner {

}
