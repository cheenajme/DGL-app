package tw.dgl.com;

/**
 * Created by 3et3et on 16/8/5.
 */

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.lang.reflect.Field;


public class PI extends AppCompatActivity {

    private ScaleImage imageView1,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi);

        imageView1 = (ScaleImage)findViewById(R.id.imageView1);
        imageView2 = (ScaleImage)findViewById(R.id.imageView2);

        //接收intent中的bundle物件
        Bundle bundle = this.getIntent().getExtras();
        String PI = bundle.getString("PI");
        String[] piArr = PI.split(" ");

        for(int i=0;i<piArr.length;i++){

            int resID = getResources().getIdentifier(piArr[i].toLowerCase() ,"drawable", getPackageName());

            if(i==0){
                imageView1.setImageResource(resID);
            }else if(i==1){
                imageView2.setImageResource(resID);
            }
        }
    }
}
