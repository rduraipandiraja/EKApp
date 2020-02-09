package com.app.ck.base;
import org.testng.ITestContext ;
import org.testng.ITestListener ;
import org.testng.ITestResult ;
import com.aventstack.extentreports.Status;


public class TestListner extends Base implements ITestListener{		
	


    @Override		
    public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onStart(ITestContext arg0) {					
        System.out.println("On Test start");	
        		
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult arg0) {					
      		
    	System.out.println("Test failure");	
    }		

    @Override		
    public void onTestSkipped(ITestResult arg0) {		
    	
    	System.out.println("Test skipped");			
    	testInfo_Static.log(Status.INFO, "Test skipped");
    
    }		

    @Override		
    public void onTestStart(ITestResult arg0) {					
    	System.out.println("Test start");			
    	
    
        		
    }		

    @Override		
    public void onTestSuccess(ITestResult arg0) {					
    	System.out.println("Test success");		
        		
    }		
}		