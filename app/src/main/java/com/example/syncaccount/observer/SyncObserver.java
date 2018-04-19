package com.example.syncaccount.observer;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.SyncAdapterType;
import android.content.SyncStatusObserver;
import android.util.Log;

import com.example.syncaccount.common.Constant;

public class SyncObserver implements SyncStatusObserver {

    private static final String TAG = SyncObserver.class.getSimpleName();
	private Account mAccount;
    
    public SyncObserver(Account account) {
		this.mAccount = account;
    }

    @Override
	public void onStatusChanged(int which) {
    	SyncAdapterType[] listSync = ContentResolver.getSyncAdapterTypes();
    	for(SyncAdapterType sync : listSync) {
    		if (Constant.ACCOUNT_TYPE.equals(sync.accountType)) {
        		String key = sync.authority;
				Log.i(TAG, key+" isSyncActive:"+ContentResolver.isSyncActive(mAccount, key));
    		}
    	}
	}
}
