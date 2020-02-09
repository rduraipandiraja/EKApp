package com.app.ck.base;


public  class GetCodeSetUP  {
		
	//Staging Environment SetUp
	public static String EKBASEURL 		 = null;
	public static String EKAPIBASEURI 	 = null;
	public static String EKLINKURL 		 = null;
	public static String ADMINURL_STAGING  = null;
	public static String ADMINURL  		= null;

	public static String SIGNUPGETCODE 		= null;
	public static String FCMGETCODE 		= null;
	public static String FBACCOUNTRESET 	 	= null;
	public static String BACKDATEEXITCLICK	= null;
	public static String ADDBONUS 		 	= null;
	public static String CASHOUT 			= null;
	public static String REFERRALLINK		= null;
	
	public static final String DEEPLINKURL 		 = "https://cashkaro.iamsavings.co.uk/deeplink_earnkaro.php";
	public static final String ONELINK 			= 	"http://design.iamsavings.co.uk/onelink.html";
	
	//Notification Large icon URL	
	public static  String NOTIFICATIONLARGEICON = "https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image";

	public static void getCodeSetUP() {
		
	
		if(PropertyFile.environment().equalsIgnoreCase("staging")) {

			//Staging Environment SetUp
			EKBASEURL 		 = "https://trackingstaging.earnkaro.com/";
			EKAPIBASEURI 	 = "http://apibackendstaging.earnkaro.com/"; 
			EKLINKURL 		 = "trackingstaging.earnkaro.com";
			ADMINURL_STAGING  = "https://stagingadmin.pouringpounds.com";

			SIGNUPGETCODE 		= EKBASEURL + "getcode.php?page=signupotp_app&mobile=";
			FBACCOUNTRESET 	 	= EKBASEURL + "getcode.php?page=reset_fbid&email=";
			BACKDATEEXITCLICK	= EKBASEURL + "getcode.php?page=ticket&id=";
			ADDBONUS 		 	= EKBASEURL + "getcode.php?page=add_bonus&email=";
			CASHOUT 			= EKBASEURL + "getcode.php?page=create_cashout&email=";
			REFERRALLINK		= EKBASEURL + "getcode.php?page=app_referral_link&userid=";
			FCMGETCODE  		= EKBASEURL + "getcode.php?page=get_fcm_id&email=" ; 
			
			System.out.println("Environment Base URL is 		=          " + EKBASEURL);
			System.out.println();
			System.out.println("Back end API end point is 		=          "+ EKAPIBASEURI);
			System.out.println();
			System.out.println("Admin URL is  					=          "+ ADMINURL_STAGING);
			System.out.println();
			System.out.println("SignUP & Payment request OTP is =          " + SIGNUPGETCODE);
			System.out.println();
			System.out.println("Face book account reset is 		=          " + FBACCOUNTRESET);
			System.out.println();
			System.out.println("Back  date the exit click  		=         " + BACKDATEEXITCLICK);
			System.out.println();
			System.out.println("Add bounus 						=          " + BACKDATEEXITCLICK);
			System.out.println();
			System.out.println("CashOut  						=           "+ CASHOUT);
			System.out.println();
			System.out.println("Referral lInk is 				=           "+ REFERRALLINK);
			System.out.println();
			
		} else {

			EKBASEURL 		 = "https://earnkaro.iamsavings.co.uk/";
			EKLINKURL 		 = "earnkaro.iamsavings.co.uk";
			EKAPIBASEURI 	 = "http://earnkaroapi.iamsavings.co.uk/"; 
			ADMINURL 		 = "https://admin.iamsavings.co.uk";

			SIGNUPGETCODE 		= EKBASEURL + "getcode.php?page=signupotp_app&mobile=";
			FBACCOUNTRESET 	 	= EKBASEURL + "getcode.php?page=reset_fbid&email=";
			BACKDATEEXITCLICK	= EKBASEURL + "getcode.php?page=ticket&id=";
			ADDBONUS 		 	= EKBASEURL + "getcode.php?page=add_bonus&email=";
			CASHOUT 			= EKBASEURL + "getcode.php?page=create_cashout&email=";
			REFERRALLINK		= EKBASEURL + "getcode.php?page=app_referral_link&userid=";
			FCMGETCODE  		= EKBASEURL + "getcode.php?page=get_fcm_id&email=" ; 
			
			System.out.println("Environment Base URL is 		=          " + EKBASEURL);
			System.out.println();
			System.out.println("Back end API end point is 		=            "+ EKAPIBASEURI);
			System.out.println();
			System.out.println("Admin URL is  					=          "+ ADMINURL);
			System.out.println();
			System.out.println("SignUP & Payment request OTP is =          " + SIGNUPGETCODE);
			System.out.println();
			System.out.println("Face book account reset is 		=    " + FBACCOUNTRESET);
			System.out.println();
			System.out.println("Back  date the exit click  		=         " + BACKDATEEXITCLICK);
			System.out.println();
			System.out.println("Add bounus 						=           " + BACKDATEEXITCLICK);
			System.out.println();
			System.out.println("CashOut  						=            "+ CASHOUT);
			System.out.println();
			System.out.println("Referral lInk is 				=    "+ REFERRALLINK);
			System.out.println();

		}

	}
	
}