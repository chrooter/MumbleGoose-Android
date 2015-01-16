package com.melvinkellner.mumblegooseforandroid.Controllers;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.melvinkellner.mumblegooseforandroid.Interfaces.OnConnectionResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mk on 16/01/15.
 */
public class ConnectionController
{
  private static String FAILED_MSG = "failed to get response";
  private static OnConnectionResponse connectionResponseListener = null;

  public static void setConnectionResponseListener(OnConnectionResponse onConnectionResponse)
  {
    ConnectionController.connectionResponseListener = onConnectionResponse;
  }

  public static OnConnectionResponse getConnectionResponseListener()
  {
    return ConnectionController.connectionResponseListener;
  }

  public static String sendRequestAndGetResponse(String url)
  {
    try
    {
      URL location = new URL(url);
      URLConnection conn = location.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
      {
        System.out.println(inputLine);
      }
      in.close();
      return inputLine;
    } catch (MalformedURLException e)
    {
      e.printStackTrace();
      return FAILED_MSG;
    } catch (IOException e)
    {
      e.printStackTrace();
      return FAILED_MSG;
    }
  }



  public static void getPlayList()
  {
    final String server = "http://music.playlane.be:8080/index.html?getplaylist=getplaylist";
    AsyncTask at = new AsyncTask()
    {
      String response;
      @Override
      protected Object doInBackground(Object[] params)
      {
        response = sendRequestAndGetResponse(server);
        return null;
      }

      @Override
      protected void onPostExecute(Object o)
      {
        if (connectionResponseListener != null)
        {
          if (!response.equals(FAILED_MSG))
          {
            connectionResponseListener.OnConnectionSuccesListener(0, response);
          }
          else
          {
            connectionResponseListener.OnConnectionFailedListener(0, response);
          }
        }
        super.onPostExecute(o);
      }
    }.execute(AsyncTask.THREAD_POOL_EXECUTOR);
  }



}
