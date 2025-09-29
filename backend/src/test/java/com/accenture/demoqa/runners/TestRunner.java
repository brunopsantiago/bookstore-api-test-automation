package com.accenture.demoqa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.accenture.demoqa.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-report.html",
                "json:target/cucumber-reports/cucumber-report.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}