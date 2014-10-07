package com.example.junseki.bvdisplayexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bazaarvoice.BazaarRequest;
import com.bazaarvoice.DisplayParams;
import com.bazaarvoice.OnBazaarResponse;
import com.bazaarvoice.types.ApiVersion;
import com.bazaarvoice.types.RequestType;

import org.json.JSONException;
import org.json.JSONObject;


public class MyActivity extends Activity implements OnBazaarResponse{

    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mainText = (TextView) findViewById(R.id.mainText);

//http://api.bazaarvoice.com/data/reviews.json?apiversion=5.4&limit=2&offset=0&passkey=q8ydkootljh2bmbcvvbo01zw9
        BazaarRequest request = new BazaarRequest("api.bazaarvoice.com/data/reviews.json", "q8ydkootljh2bmbcvvbo01zw9", ApiVersion.FIVE_FOUR);
        DisplayParams params = new DisplayParams();
        request.sendDisplayRequest(RequestType.PRODUCTS, params, this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(JSONObject response) {

        final JSONObject myJson = response;
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() { try {
                if (myJson.getBoolean("HasErrors")) { mainText.setText("JSON response has errors");
                } else { mainText.setText(myJson.toString());
                }
            } catch (JSONException exception) {
                Log.e("BVDisplayExample", "Error = " + exception.getMessage() + "\n");
                exception.printStackTrace(); }
            } });

    }

    @Override
    public void onException(String message, Throwable exception) {
        Log.e("BVDisplayExample", "Error = " + message + "\n");
        exception.printStackTrace();
    }
}
