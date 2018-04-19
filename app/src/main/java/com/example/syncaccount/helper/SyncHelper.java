package com.example.syncaccount.helper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;

import com.example.syncaccount.R;
import com.example.syncaccount.common.Constant;

//http://developer.android.com/training/sync-adapters/creating-sync-adapter.html
public class SyncHelper {
    private static final String TAG = SyncHelper.class.getSimpleName();

    private SyncHelper() {
    }

    /**
     * Create a new account for the sync adapter
     *
     * @param context The application context
     */
    public static Account CreateSyncAccount(Context context) {

        // Create the account type and default account
        Account newAccount = new Account(context.getString(R.string.account_name), Constant.ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        	SyncAdapterType[] listSync = ContentResolver.getSyncAdapterTypes();
        	for(SyncAdapterType sync : listSync) {
        		if (Constant.ACCOUNT_TYPE.equals(sync.accountType)) {
            		String key = sync.authority;
            		ContentResolver.setIsSyncable(newAccount, key, 1);
        		}
        	}
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }

        return newAccount;
    }

	/**
	 * Enable Auto Synchronization
	 */
	public static void syncInitialize() {
		Log.i(TAG, "Activate master sync automatically");
		ContentResolver.setMasterSyncAutomatically(true);
	}

	/**
	 * Turn on periodic syncing
	 * @param account
	 */
	public static void syncPeriodic(Account account) {
    	SyncAdapterType[] listSync = ContentResolver.getSyncAdapterTypes();
    	for(SyncAdapterType sync : listSync) {
    		if (Constant.ACCOUNT_TYPE.equals(sync.accountType)) {
        		String key = sync.authority;
        		Log.i(TAG, "Activate '"+key+"' sync periodic interval:"+Constant.SYNC_INTERVAL);
                ContentResolver.addPeriodicSync(account, key, new Bundle(), Constant.SYNC_INTERVAL);
    		}
    	}
	}

	/**
	 * Turn on automatic syncing for the default account and authority
	 * @param account
	 */
	public static void syncAutomatic(Account account) {
    	SyncAdapterType[] listSync = ContentResolver.getSyncAdapterTypes();
    	for(SyncAdapterType sync : listSync) {
    		if (Constant.ACCOUNT_TYPE.equals(sync.accountType)) {
        		String key = sync.authority;
    			Log.i(TAG, "Activate '"+key+"' sync automatically");
                ContentResolver.setSyncAutomatically(account, key, true);
    		}
    	}
	}

	public static void syncDemand(Account account) {
		// Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
    	SyncAdapterType[] listSync = ContentResolver.getSyncAdapterTypes();
    	for(SyncAdapterType sync : listSync) {
    		if (Constant.ACCOUNT_TYPE.equals(sync.accountType)) {
        		String key = sync.authority;
        		Log.i(TAG, "Activate '"+key+"' sync on demand");
                ContentResolver.requestSync(account, key, settingsBundle);
    		}
    	}
	}

	public static Object addStatusChangeListener(SyncStatusObserver syncObserver) {
		return ContentResolver.addStatusChangeListener(ContentResolver.SYNC_OBSERVER_TYPE_ACTIVE, syncObserver);
	}

	public static void removeStatusChangeListener(Object contentProviderHandle) {
		ContentResolver.removeStatusChangeListener(contentProviderHandle);
	}
}