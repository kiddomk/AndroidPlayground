package com.example.junseki.myapplication3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;



public class MessageDialogFragment extends DialogFragment {

    public static final String MESSAGE_TITLE = "message.title";
    public static final String MESSAGE_CONTENT = "message.content";
    public static final String MESSAGE_BUTTON_TEXT = "message.button.text";

    private TextView titleTextView;
    private TextView contentTextView;
    private Button confirmationButton;

    private MessageDialogListener mListener;

    public MessageDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_message_dialog, null);

        titleTextView=(TextView)view.findViewById(R.id.fragment_message_dialog_title);
        String messageTitle=(String)this.getArguments().getSerializable(MESSAGE_TITLE);
        titleTextView.setText(messageTitle);

        contentTextView=(TextView)view.findViewById(R.id.fragment_message_dialog_content);
        String messageContent=(String)this.getArguments().getSerializable(MESSAGE_CONTENT);
        contentTextView.setText(messageContent);

        confirmationButton=(Button)view.findViewById(R.id.fragment_message_dialog_button);
        String buttonText=(String)this.getArguments().getSerializable(MESSAGE_BUTTON_TEXT);
        confirmationButton.setText(buttonText);

        builder.setView(view);

        // Create dialog
        final Dialog dialog = builder.create();

        confirmationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Send the positive button event back to the host activity
                mListener.onDialogPositiveClick(MessageDialogFragment.this);

                dialog.dismiss();

            }
        });


        return dialog;
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (MessageDialogListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement MessageDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface MessageDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }

}
