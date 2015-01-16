package com.melvinkellner.mumblegooseforandroid.Interfaces;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by mk on 16/01/15.
 */
public interface OnConnectionResponse
{
  void OnConnectionSuccesListener(int type, String response);
  void OnConnectionFailedListener(int type, String response);
}
