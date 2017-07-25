package tw.dgl.com.Label;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import tw.dgl.com.R;
import tw.dgl.com.chinese;


public class LabelActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout label_01,label_02,label_03,label_04,label_05,label_06
            ,label_07,label_08,label_09;
    private Button bluehome_btn,home_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
     label_01=(LinearLayout)findViewById(R.id.label_01);
        label_02=(LinearLayout)findViewById(R.id.label_02);
        label_03=(LinearLayout)findViewById(R.id.label_03);
        label_04=(LinearLayout)findViewById(R.id.label_04);
        label_05=(LinearLayout)findViewById(R.id.label_05);
        label_06=(LinearLayout)findViewById(R.id.label_06);
        label_07=(LinearLayout)findViewById(R.id.label_07);
        label_08=(LinearLayout)findViewById(R.id.label_08);
        label_09=(LinearLayout)findViewById(R.id.label_09);
        bluehome_btn=(Button)findViewById(R.id.bluehome_btn);
        home_btn=(Button)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(this);
        bluehome_btn.setOnClickListener(this);
        label_01.setOnClickListener(this);
        label_02.setOnClickListener(this);
        label_03.setOnClickListener(this);
        label_04.setOnClickListener(this);
        label_05.setOnClickListener(this);
        label_06.setOnClickListener(this);
        label_07.setOnClickListener(this);
        label_08.setOnClickListener(this);
        label_09.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.label_01:
                Intent intent=new Intent(LabelActivity.this,Dglabel01Activity.class);
                startActivity(intent);
                break;
            case R.id.label_02:
                Intent intent1=new Intent(LabelActivity.this,Dglabel02Activity.class);
                startActivity(intent1);
                break;
            case R.id.label_03:
                break;
            case R.id.label_04:
                Intent intent2=new Intent(LabelActivity.this,Dglablel04Activity.class);
                startActivity(intent2);
                break;
            case R.id.label_05:
                Intent intent5=new Intent(LabelActivity.this,Dgablel05Activity.class);
                startActivity(intent5);
                break;
            case R.id.label_06:
                Intent intent6=new Intent(LabelActivity.this,Dgablel06Activity.class);
                startActivity(intent6);
                break;
            case R.id.label_07:
                break;
            case R.id.label_08:
                break;
            case R.id.label_09:
                break;
            case R.id.home_btn:
                Intent intent10=new Intent(LabelActivity.this,chinese.class);
                startActivity(intent10);
                finish();
                break;
            case R.id.bluehome_btn:
                onBackPressed();
                break;

        }

    }
}
