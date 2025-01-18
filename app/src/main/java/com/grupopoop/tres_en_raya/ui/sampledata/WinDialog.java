package com.grupopoop.tres_en_raya.ui.sampledata;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.grupopoop.tres_en_raya.MainActivity;
import com.grupopoop.tres_en_raya.R;

public class WinDialog  extends Dialog {

    private final String message;
    private final MainActivity mainActivity;


    public WinDialog(@NonNull Context context,String message,MainActivity mainActivity){
        super(context);
        this.message=message;
        this.mainActivity=mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.win_dialog_layout));

        final TextView messageTxt = findViewById(R.id.messageTxt);
        final Button starAgainBtn= findViewById(R.id.startAgainBtn);

        messageTxt.setText(message);
        starAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();
                dismiss();
            }
        });
    }
}
