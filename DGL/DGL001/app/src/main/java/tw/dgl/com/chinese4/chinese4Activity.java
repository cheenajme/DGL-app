package tw.dgl.com.chinese4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import tw.dgl.com.R;
import tw.dgl.com.chinese;

public class chinese4Activity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout  chinese4_01,chinese4_02,chinese4_03,chinese4_04,chinese4_05,chinese4_06
            ,chinese4_07,chinese4_08,chinese4_09;
    private Button bluehome_btn,home_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去標題
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese4);
        chinese4_01=(LinearLayout)findViewById(R.id.chinese4_01);
        chinese4_02=(LinearLayout)findViewById(R.id.chinese4_02);
        chinese4_03=(LinearLayout)findViewById(R.id.chinese4_03);
        chinese4_04=(LinearLayout)findViewById(R.id.chinese4_04);
        chinese4_05=(LinearLayout)findViewById(R.id.chinese4_05);
        chinese4_06=(LinearLayout)findViewById(R.id.chinese4_06);
        chinese4_07=(LinearLayout)findViewById(R.id.chinese4_07);
        chinese4_08=(LinearLayout)findViewById(R.id.chinese4_08);
        chinese4_09=(LinearLayout)findViewById(R.id.chinese4_09);
        bluehome_btn=(Button)findViewById(R.id.bluehome_btn);
        home_btn=(Button)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(this);
        bluehome_btn.setOnClickListener(this);
        chinese4_01.setOnClickListener(this);
        chinese4_02.setOnClickListener(this);
        chinese4_03.setOnClickListener(this);
        chinese4_04.setOnClickListener(this);
        chinese4_05.setOnClickListener(this);
        chinese4_06.setOnClickListener(this);
        chinese4_07.setOnClickListener(this);
        chinese4_08.setOnClickListener(this);
        chinese4_09.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.chinese4_01:
                Intent intent=new Intent(chinese4Activity.this,Label01Activity.class);
                startActivity(intent);
                break;
            case R.id.chinese4_02:
                Intent intent1=new Intent(chinese4Activity.this,Label02Activity.class);
                startActivity(intent1);
                break;
            case R.id.chinese4_03:
                break;
            case R.id.chinese4_04:
                Intent intent2=new Intent(chinese4Activity.this,Label04Activity.class);
                startActivity(intent2);
                break;
            case R.id.chinese4_05:
                Intent intent5=new Intent(chinese4Activity.this,Label05Activity.class);
                startActivity(intent5);
                break;
            case R.id.chinese4_06:
                Intent intent6=new Intent(chinese4Activity.this,Label6Activity.class);
                startActivity(intent6);
                break;
            case R.id.chinese4_07:
                break;
            case R.id.chinese4_08:
                break;
            case R.id.chinese4_09:
                break;
            case R.id.home_btn:
                Intent intent10=new Intent(chinese4Activity.this,chinese.class);
                startActivity(intent10);
                finish();
                break;
            case R.id.bluehome_btn:
                onBackPressed();
                break;

        }

    }
}
