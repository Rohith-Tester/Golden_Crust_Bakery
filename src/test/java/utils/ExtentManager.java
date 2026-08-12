package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/GCB.html");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "Golden Crust Bakery");
            extent.setSystemInfo("Framework", "Playwright");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Test Framework", "TestNG");
        }

        return extent;
    }
}