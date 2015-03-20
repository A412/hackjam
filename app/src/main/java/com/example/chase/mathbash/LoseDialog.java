package com.example.chase.mathbash;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class LoseDialog extends DialogFragment {

    public interface LoseDialogListener {
        public String getLoseDialogMessage();
        public void onDialogPositiveClick(DialogFragment d);
    }

    public LoseDialogListener ldl;

    private String scoreMessage;

    public void onAttach(Activity ac) {
        super.onAttach(ac);
        ldl = (LoseDialogListener) ac;
        scoreMessage = ((LoseDialogListener) ac).getLoseDialogMessage();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(scoreMessage).setTitle(R.string.lose_title).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ldl.onDialogPositiveClick(LoseDialog.this);
            }
        });
        return builder.create();
    }
}
