package tw.dgl.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class EMS extends AppCompatActivity{

    private TouchImageView imageView1,imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ems);

        imageView1 = (TouchImageView)findViewById(R.id.imageView1);
        imageView2 = (TouchImageView)findViewById(R.id.imageView2);


        Bundle bundle = this.getIntent().getExtras();
        String EMS = bundle.getString("EMS");

        String[] emsArr = EMS.split(" ");

        for(int i=0;i<emsArr.length;i++){

            int emsID = getResources().getIdentifier(emsArr[i].toLowerCase() , "drawable", getPackageName());
            Log.d("ems",EMS);
            if(i==0){
                imageView1.setImageResource(emsID);
            }else if(i==1){
                imageView2.setImageResource(emsID);
            }

        }
    }
}
