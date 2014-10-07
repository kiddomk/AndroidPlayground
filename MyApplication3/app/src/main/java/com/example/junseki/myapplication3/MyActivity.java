package com.example.junseki.myapplication3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.plotprojects.retail.android.Plot;
import com.plotprojects.retail.android.PlotConfiguration;


public class MyActivity extends Activity implements MessageDialogFragment.MessageDialogListener {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private static String PLOT_PUBLIC_TOKEN = "NyJtkU2p2KmegxWy";

    private static PlotConfiguration config = new PlotConfiguration(PLOT_PUBLIC_TOKEN);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_my);

        Plot.init(this, config);

        Intent intent = getIntent();
        String message = intent.getStringExtra(IntentReceiver.UA_PUSH_MESSAGE);
        if (message!=null&&!message.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Push Message")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete

                        }
                    })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
//                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();

        }

    }

//    public void stackOverflow() {
//        this.stackOverflow();
//
//    }

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

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {

//        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);

        // Open as Dialog Fragment
        DialogFragment messageDialog = new MessageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MessageDialogFragment.MESSAGE_TITLE,"PUsh messages");
        bundle.putSerializable(MessageDialogFragment.MESSAGE_CONTENT,message);
        bundle.putSerializable(MessageDialogFragment.MESSAGE_BUTTON_TEXT,"OK");
        messageDialog.setArguments(bundle);
        messageDialog.show(getFragmentManager(), "MessageDialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.i("buttonclick","clicked ok");
    }
}
