package tw.dgl.com;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class IBCI extends AppCompatActivity {

    private ScaleImage imageView1,imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibci);

        imageView1 = (ScaleImage) findViewById(R.id.imageView1);
        imageView2 = (ScaleImage) findViewById(R.id.imageView2);


        Bundle bundle =this.getIntent().getExtras();
        String IBCI = bundle.getString("IBCI");
        String[] ibcArr = IBCI.split(" ");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

    for (int i = 0; i < ibcArr.length; i++) {

        int resID = getResources().getIdentifier(ibcArr[i].toLowerCase(), "drawable", getPackageName());

        if (i == 0) {
            imageView1.setImageResource(resID);
        } else if (i == 1) {
            imageView2.setImageResource(resID);
        }
    }

    }
}

