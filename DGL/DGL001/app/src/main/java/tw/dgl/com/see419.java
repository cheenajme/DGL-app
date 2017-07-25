package tw.dgl.com;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomButton;
import android.widget.ZoomControls;

public class see419 extends AppCompatActivity {

    ScaleImage image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see419);

        image = (ScaleImage) findViewById(R.id.image);
        image.setImageResource(R.drawable.see419);


    }
}
