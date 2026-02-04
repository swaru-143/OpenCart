package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;   // UI of the report
    public ExtentReports extent;                // Common info
    public ExtentTest test;                     // Test entries

    String repName;

    // Before Suite starts
    public void onStart(ITestContext testContext)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName); //Specify Location to generate Report.

        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");   // Title of the Report.
        sparkReporter.config().setReportName("OpenCart Functional Testing");     // Name of the Report.
        sparkReporter.config().setTheme(Theme.DARK);                             // Theme of the Report.

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty())
        {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    // Test Passed
    public void onTestSuccess(ITestResult result)
    {
        test = extent.createTest(result.getTestClass().getName());                // to display Class Name in Report.
        test.assignCategory(result.getMethod().getGroups());                      // to display Group in Report
        test.log(Status.PASS, result.getName() + " got successfully executed");   // to display Message with ClassName.
    }

    // Test Failed
    public void onTestFailure(ITestResult result)
    {
        test = extent.createTest(result.getTestClass().getName());               // to display Class Name in Report.
        test.assignCategory(result.getMethod().getGroups());                     // to display Group in Report

        test.log(Status.FAIL, result.getName() + " got failed");                 // to display Message with ClassName.
        test.log(Status.INFO, result.getThrowable().getMessage());               // to display error message.

        try
        {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Test Skipped
    public void onTestSkipped(ITestResult result)
    {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // After Suite ends
    public void onFinish(ITestContext testContext)
    {
        extent.flush();         // This flush is enough for Finish method.
        
        
        // This below perform---> Automatically Opens the Report after Test execution compeleted.

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;

        File extentReport = new File(pathOfExtentReport);

        try
        {
            Desktop.getDesktop().browse(extentReport.toURI());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        
        
        
        // Automatically Send Report via Email to specified email address.
        
        /*
        
        try
        {
        URL url =new URL("file:///" + System.getProperty("user.dir")+ "\\reports\\"+repName);
        
        
        //Create the email message
        
        ImageHtmlEmail email =new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setHostName("smtp.googlemail.com");                            //--->This is for only Gmail.
        email.setSmtpPort(465);                                              //--->This is for Only Gmail.
        email.setAuthenticator(new DefaultAuthenticator("brooklynswaroop@gmail.com", "Swaru@143"));
        email.setSSLOnConnect(true);
        email.setFrom("brooklynswaroop@gmail.com");
        email.setSubject("Test Result");
        email.setMsg("Please finf Attached report...");
        email.addTo("mgswarup18@gmail.com");
        email.attach(url, "extent report", "please check report...");
        email.send();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        */
    }
}

