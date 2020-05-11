package sg.edu.rp.c346.id18015518.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button split;
    EditText numPax;
    EditText amount;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    TextView totalBill;
    TextView eachPays;
    Button reset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        split = findViewById(R.id.buttonSplit);
        numPax = findViewById(R.id.editInputPax);
        amount = findViewById(R.id.editInputAmount);
        svs = findViewById(R.id.buttonSvs);
        gst = findViewById(R.id.buttonGst);
        discount = findViewById(R.id.editInputDiscount);
        totalBill = findViewById(R.id.textBill);
        eachPays = findViewById(R.id.textPay);
        reset = findViewById(R.id.buttonReset);



        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0){
            double newAmt = 0.0;
            if(!svs.isChecked() && !gst.isChecked()) {
                newAmt = Double.parseDouble(amount.getText().toString());
            }else if (svs.isChecked() && !gst.isChecked()){
                newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
            }else if (!svs.isChecked() && gst.isChecked()) {
                newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
            }else {
                newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
            }

            //Discount
                    if(discount.getText().toString().trim().length() !=0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if (numPerson != 1)
                        eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                    else
                        eachPays.setText("Each Pays: $" + newAmt);


            }
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
                                 }
        );
    }
}
