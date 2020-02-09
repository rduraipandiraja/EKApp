package com.app.ck.base;

public class PropertyFile {
	
	
	//environment staging or beta
	public static String environment = null ;
	
	//default it is set as true
	public static String needScrnShotOnTestPass = null; 
	
	//default it is set as true
	public static String needScrnShotOnTestFail = null;
	
	
	public static String environment() {
		
		environment = (String)ReadConfigFile.getProperty("environment");
		System.out.println("Running environment is : "+ environment);
		return environment;
	}
	
	public static boolean needScreenshotOnStepPass() {
		
		needScrnShotOnTestPass = (String)ReadConfigFile.getProperty("screenshotOnTestPass");
		
		boolean flag = Boolean.parseBoolean(needScrnShotOnTestPass);
		System.out.println("Screenshot Enabled in Test step Pass  : "+ flag);
		return flag;
	}
	
	public static boolean needScreenshotOnStepFail() {
		
		needScrnShotOnTestFail = (String)ReadConfigFile.getProperty("screenshotOnTestFail");
		boolean flag = Boolean.parseBoolean(needScrnShotOnTestFail);
		System.out.println("Screenshot Enabled in Test step Fail  : "+ flag);
		return flag;
	}

}