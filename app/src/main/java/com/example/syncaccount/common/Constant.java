package com.example.syncaccount.common;


public class Constant {

    // Constants
    // The authorities for the sync adapter's content provider
    public enum AUTHORITY_REFERENTIEL {
    	SENS("com.example.syncaccount.provider.sens"),
    	ARTICLE("com.example.syncaccount.provider.article");

    	public String key;
    	AUTHORITY_REFERENTIEL (String key) {
    		this.key = key;
    		
    	}
    }

    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "com.example.syncaccount";
    // The account name
    public static final String ACCOUNT = "default_account";

    // Sync interval constants
    public static final long MILLISECONDS_PER_SECOND = 1000L;
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long SYNC_INTERVAL_IN_MINUTES = 60L;
    public static final long SYNC_INTERVAL = 5L;//SYNC_INTERVAL_IN_MINUTES * SECONDS_PER_MINUTE;// * MILLISECONDS_PER_SECOND;
	
	private Constant() {
	}
}