package testing;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/com/tools2test/featureui/ui_verification.feature",
		glue="com.tools2test.uiconfsteps"
		)

public class AppTester {

}
