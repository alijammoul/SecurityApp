package com.example.lenovo.securityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private Button b;
private TextView inv,add,sub,mul,div,red;
private EditText invin,addin1,addin2,subin1,subin2,mulin1,mulin2,divin1,divin2,redin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button)findViewById(R.id.run);
        inv=(TextView)findViewById(R.id.invout); red=(TextView)findViewById(R.id.redout);
        add=(TextView)findViewById(R.id.addout); sub=(TextView)findViewById(R.id.subout); mul=(TextView)findViewById(R.id.mulout); div=(TextView)findViewById(R.id.divout);


        invin=(EditText)findViewById(R.id.invin); redin=(EditText)findViewById(R.id.redin);
        addin1=(EditText)findViewById(R.id.addin1);addin2=(EditText)findViewById(R.id.addin2);
        subin1=(EditText)findViewById(R.id.subin1);subin2=(EditText)findViewById(R.id.subin2);
        mulin1=(EditText)findViewById(R.id.mulin1);mulin2=(EditText)findViewById(R.id.mulin2);
        divin1=(EditText)findViewById(R.id.divin1);divin2=(EditText)findViewById(R.id.divin2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(!addin1.getText().toString().equals("")&&!addin2.getText().toString().equals("")){
    Polynomial add1 = new Polynomial(addin1.getText().toString());
    Polynomial add2 = new Polynomial(addin2.getText().toString());
    add.setText(add1.add(add2).toString());
}


                if(!subin1.getText().toString().equals("")&&!subin2.getText().toString().equals("")){
                    Polynomial sub1 = new Polynomial(subin1.getText().toString());
                    Polynomial sub2 = new Polynomial(subin2.getText().toString());
                    sub.setText(sub1.subtract(sub2).toString());
                }

                if(!mulin1.getText().toString().equals("")&&!mulin2.getText().toString().equals("")){
                    Polynomial mul1 = new Polynomial(mulin1.getText().toString());
                    Polynomial mul2 = new Polynomial(mulin2.getText().toString());
                    mul.setText(mul1.multiply(mul2).toString());
                }

                if(!divin1.getText().toString().equals("")&&!divin2.getText().toString().equals("")){
                    Polynomial div1 = new Polynomial(divin1.getText().toString());
                    Polynomial div2 = new Polynomial(divin2.getText().toString());
                    div.setText(div1.divide(div2).toString());
                }


                if(!invin.getText().toString().equals("")){
                    Polynomial x = new Polynomial(invin.getText().toString());

                    String output = x.inverse()+"";
                    //Polynomial s = y.multiply(x);
                    inv.setText(output);
                }
                if(!redin.getText().toString().equals("")){
                    Polynomial x = new Polynomial(redin.getText().toString());

                    String output = x.reduce()+"";
                    //Polynomial s = y.multiply(x);
                    inv.setText(output);
                }


            }
        });
    }
}
