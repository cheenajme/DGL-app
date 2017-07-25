package tw.dgl.com;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class opening extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        context=this;
        countDownTimer=getCountDownTimer();
        countDownTimer.start();
    }
    protected void onPause(){
        super.onPause();
        countDownTimer.cancel();
    }
    private CountDownTimer getCountDownTimer(){
        CountDownTimer countDownTimer=new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(context,FristActivity.class);
                startActivity(intent);
                finish();
            }
        };
        return  countDownTimer;
    }
}
