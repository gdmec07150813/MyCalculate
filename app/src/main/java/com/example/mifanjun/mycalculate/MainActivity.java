package com.example.mifanjun.mycalculate;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText weight;
    private RadioButton manButton,womanButton;
    private TextView resultTextView;
    private Button calculate;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight = (EditText) findViewById(R.id.weight);
        manButton = (RadioButton) findViewById(R.id.manButton);
        womanButton = (RadioButton) findViewById(R.id.womanButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        calculate = (Button) findViewById(R.id.calculate);


    }

    @Override
    protected void onStart() {
        super.onStart();
        register();
    }

    private void register(){
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weight.getText().toString().equals("")){
                    if(manButton.isChecked()||womanButton.isChecked()){
                        Double dweight = Double.parseDouble(weight.getText().toString());
                        StringBuffer st = new StringBuffer();
                        st.append("————————评估结果————————\n");
                        if(manButton.isChecked()){
                            double dheight = result(dweight,"男");
                            st.append("男--标准身高为: "+dheight);
                        }else{
                            double dheight = result(dweight,"女");
                            st.append("女--标准身高为: "+dheight);
                        }
                        resultTextView.setText(st);
                    }else{
                        setMessage("请选择性别");
                    }
                }else{
                    setMessage("请输入体重");
                }
            }
        });
    }
    private double result(double weight,String sex){
         double height;
        if(sex == "男"){
            height = 170-(62-weight)/0.6;
        }else{
            height = 158-(52-weight)/0.5;
        }
        return height;
    }

    private void setMessage(String message){
        dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("系统提示");
        dialog.setMessage(message);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

}
