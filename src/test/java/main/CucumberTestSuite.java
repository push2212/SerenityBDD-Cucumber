package main;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features"
,tags= {"@DomainLogin001 or @LMS022"}
     //,tags= {"@LMS001 or @LMS003 or @LMS006"}
)
public class CucumberTestSuite {}

