package com.brandycamacho.iota_lookup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jota.IotaAPI;
import jota.error.ArgumentException;

public class MainActivity extends AppCompatActivity {
  TextView tv_1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    String[] wallets = {"MAWQYLHMQSGTLTNCYEXJPTMNLJZQDIX9GDQRPGLKYPBIFNYFQBPP9UENNGODA9IYODAGI99PGGNPATZFDSMMX99WZC"};
    tv_1 = (TextView) findViewById(R.id.tv_1);
    new IOTA_Test().execute(wallets);
  }

  private class IOTA_Test extends AsyncTask<String, Integer, String> {
    protected String doInBackground(String... data) {
      String[] wallets = data;
      ArrayList<String> transactionHashes = new ArrayList<>();
      List transactionData = null;
      List walletData = null;

      /*
      Connect to IOTA node
       */
      IotaAPI api = new IotaAPI.Builder()
        .protocol("http")
        .host("node03.iotatoken.nl")
        .port("15265")
        .build();

      /*
      Find transaction hashes from wallet addresses
       */
      try {
        walletData = api.findTransactionObjectsByAddresses(wallets);
      } catch (ArgumentException e) {
        e.printStackTrace();
      }

      /*
      Get transaction hash details
       */
      JSONArray jArray = new JSONArray();
      for (Object r : walletData) {
        JSONObject jObject = new JSONObject();
        String[] parentItems = r.toString().split(" ");
        for (String v : parentItems) {
          String[] childItem = v.split("=");
          if (childItem.length>1) {
            String obj = childItem[0];
            String val = childItem[1];
            try {
              jObject.put(obj,val);
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        }
        jArray.put(jObject);
      }
      for (int i = 0; i<jArray.length();i++){
        try {
          JSONObject val = jArray.getJSONObject(i);
          transactionHashes.add(val.getString("hash").trim().replaceAll("\n",""));
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      /*
      Convert ArrayList to String[] - required from IOTA API
       */
      String[] transactionHasheSet = new String[transactionHashes.size()];
      transactionHasheSet = transactionHashes.toArray(transactionHasheSet);
      Log.d("Dynamic Hashes", Arrays.toString(transactionHasheSet));

      try {
        transactionData = api.findTransactionsObjectsByHashes(transactionHasheSet);
      } catch (ArgumentException e) {
        e.printStackTrace();
      }

      /*
      Convert transaction hash details to JSON
       */

      String jsonTransactions = new Gson().toJson(transactionData);

      return jsonTransactions;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {

      // Test output: Deserialize Transactions
      Type hashItemsList = new TypeToken<ArrayList<Transaction.HashItem>>(){}.getType();
      List<Transaction.HashItem> transactionList = new Gson().fromJson(result, hashItemsList);
      Log.d("DEBUG_TEST: Hash0", transactionList.get(0).hash);

      /*
      TODO:
      - update u/i flow
      */

      // Update UI
      tv_1.setText("View Logcat");

    }
    private void log(String data){
      Log.d("DEBUG", data);
    }
  }

}
