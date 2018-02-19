package com.yeti_llc.slot_machine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button bSetValue = findViewById(R.id.bSetValue);
        final Button bPullLever = findViewById(R.id.bPullLever);
        bSetValue.setEnabled(false);
        bPullLever.setEnabled(false);

        final EditText etNewAmount = findViewById(R.id.editText);
        etNewAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            //if amount has value enable button
            @Override
            public void afterTextChanged(Editable s) {
                if(etNewAmount.getText().toString().length() > 0) {
                    bSetValue.setEnabled(true);

                }
                else
                    bSetValue.setEnabled(false);
            }
        });
    }
    public void onSetValue(View v){
        Button bSetValue = findViewById(R.id.bSetValue);
        final Button bPullLever = findViewById(R.id.bPullLever);
        EditText etNewAmount = findViewById(R.id.editText);
        String amountText = etNewAmount.getText().toString();

        if(Integer.parseInt(amountText) < 100 || Integer.parseInt(amountText) > 500){
            Context context = getApplicationContext();
            CharSequence text = "Value must be between 100 and 500";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }//end if
        else {
            TextView bankAmount = findViewById(R.id.tvBankAmount);
            bankAmount.setText(amountText);
            bPullLever.setEnabled(true);
            bSetValue.setEnabled(false);
            etNewAmount.setText("");
            etNewAmount.setEnabled(false);

        }//end else
    }//end onSetValue

    public void onNewGame(View v){
        Button bPullLever = findViewById(R.id.bPullLever);
        TextView bankAmount = findViewById(R.id.tvBankAmount);
        EditText etNewAmount = findViewById(R.id.editText);
        TextView tvSlotOne = findViewById(R.id.tvSlot_One);
        TextView tvSlotTwo = findViewById(R.id.tvSlot_Two);
        TextView tvSlotThree = findViewById(R.id.tvSlot_Three);

        bankAmount.setText("");
        tvSlotOne.setText("");
        tvSlotTwo.setText("");
        tvSlotThree.setText("");
        bPullLever.setEnabled(false);
        etNewAmount.setText("");
        etNewAmount.setEnabled(true);

    }//end onNewGame

    public void onPullLever(View v){
        int max = 9;
        int min = 1;
        TextView tvBankAmount = findViewById(R.id.tvBankAmount);
        String bankAmount = tvBankAmount.getText().toString();
        Random rand = new Random();
        TextView tvSlot_One = findViewById(R.id.tvSlot_One);
        TextView tvSlot_Two = findViewById(R.id.tvSlot_Two);
        TextView tvSlot_Three = findViewById(R.id.tvSlot_Three);



        int intBankAmount = Integer.parseInt(bankAmount) - 5;
        bankAmount = Integer.toString(intBankAmount);
        tvBankAmount.setText(bankAmount);


        int random_Slot = rand.nextInt(max - min + 1) + min;
        String slot_One = Integer.toString(random_Slot);
        tvSlot_One.setText(slot_One);

        random_Slot = rand.nextInt(max - min + 1) + min;
        String slot_Two = Integer.toString(random_Slot);
        tvSlot_Two.setText(slot_Two);

        random_Slot = rand.nextInt(max - min + 1) + min;
        String slot_Three = Integer.toString(random_Slot);
        tvSlot_Three.setText(slot_Three);


        if(slot_One == slot_Two && slot_One == slot_Three){
            if(Integer.parseInt(slot_One) < 5){
                intBankAmount = Integer.parseInt(bankAmount) + 40;
                bankAmount = Integer.toString(intBankAmount);
                tvBankAmount.setText(bankAmount);
            }
            else if(Integer.parseInt(slot_One) >= 5 && Integer.parseInt(slot_One) <=8){
                intBankAmount = Integer.parseInt(bankAmount) + 100;
                bankAmount = Integer.toString(intBankAmount);
                tvBankAmount.setText(bankAmount);
            }
            else{
                intBankAmount = Integer.parseInt(bankAmount) + 1000;
                bankAmount = Integer.toString(intBankAmount);
                tvBankAmount.setText(bankAmount);
            }
        }//end 3 match pay out
        else if((slot_One == slot_Two) || (slot_One == slot_Three) || (slot_Two == slot_Three)){
            intBankAmount = Integer.parseInt(bankAmount) + 10;
            bankAmount = Integer.toString(intBankAmount);
            tvBankAmount.setText(bankAmount);
        }// end of 2 match payout

        if(intBankAmount >= 1000){
            Context context = getApplicationContext();
            CharSequence text = "You cleared out the slot machine!! New Game Starting";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            onNewGame(v);
        }
        if(intBankAmount <= 0){
            Context context = getApplicationContext();
            CharSequence text = "You lost all of your money! New Game Starting";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            onNewGame(v);
        }




    }//end onPullLever
}//end MainActivity
