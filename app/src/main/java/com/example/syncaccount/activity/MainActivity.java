package com.example.syncaccount.activity;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.syncaccount.R;
import com.example.syncaccount.helper.SyncHelper;
import com.example.syncaccount.observer.SyncObserver;

//http://developer.android.com/training/sync-adapters/creating-sync-adapter.html
public class MainActivity extends FragmentActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

    // Instance fields
    private Account mAccount;

	private Object mContentProviderHandle;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);

        // Create the dummy account
        mAccount = SyncHelper.CreateSyncAccount(this);

        SyncHelper.syncInitialize();

        SyncHelper.syncAutomatic(mAccount);

        SyncHelper.syncPeriodic(mAccount);
    }

    @Override
    protected void onResume() {
    	super.onResume();

        mContentProviderHandle = SyncHelper.addStatusChangeListener(new SyncObserver(mAccount));
    }

    @Override
    protected void onPause() {
    	SyncHelper.removeStatusChangeListener(mContentProviderHandle);

    	super.onPause();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickLaunchSync(View view) {
		Log.i(TAG, "onClickLaunchSync");

		SyncHelper.syncDemand(mAccount);
	}
}