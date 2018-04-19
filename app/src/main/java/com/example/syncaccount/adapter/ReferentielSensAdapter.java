package com.example.syncaccount.adapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;


public class ReferentielSensAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = ReferentielSensAdapter.class.getSimpleName();

    /**
     * Set up the sync adapter
     */
    public ReferentielSensAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
		Log.i(TAG, "SyncAdapter autoInitialize:"+autoInitialize);
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public ReferentielSensAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
		Log.i(TAG, "SyncAdapter autoInitialize:"+autoInitialize+" allowParallelSyncs:"+allowParallelSyncs);
    }

    @Override
	public void onPerformSync(Account arg0, Bundle arg1, String arg2, ContentProviderClient arg3, SyncResult arg4) {
		Log.i(TAG, "onPerformSync");

        initializeLog();

		synchronizeSens();
	}

	private void synchronizeSens() {
//		try {
//			SocleHttpsConnector myConnector = new SocleHttpsConnector(PfmcServerType.RECETTE_EXTERNE, PfmcInstanceType.BB, "359901041781960", "R3S_V7.0.0", false);
//			Response ret = myConnector.getData("/synchro/sens/v4");
//
//			String retString = IOUtils.toString(ret.getBody());
//			Log.i(TAG, retString);
//		} catch (IOException e) {
//			Log.e(TAG, "IOException", e);
//		} catch (Exception e) {
//			Log.e(TAG, "Exception", e);
//		}
	}

	private void initializeLog() {
//		com.laposte.commons.antibrouillard.Log.setFilePath("SyncAccount.log");
	}
}