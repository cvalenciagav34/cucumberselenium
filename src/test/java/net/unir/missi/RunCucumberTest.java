package net.unir.missi;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("net/unir/missi")
// pretty
// html:target/cucumber-report/cucumber.html
// json:target/cucumber-report/cucumber.json
// @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
// @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-report/cucumber.html")
public class RunCucumberTest {}
