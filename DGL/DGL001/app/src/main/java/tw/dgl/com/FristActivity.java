package tw.dgl.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class FristActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
    }

    public void chi(View v) {
        Intent it = new Intent(this, chinese.class);
        startActivity(it);
    }

    public void eng(View v) {
        Intent it = new Intent(this, Frist2Activity.class);
        startActivity(it);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {


            new AlertDialog.Builder(FristActivity.this)
                    .setTitle("Rule Marine")
                    .setMessage("確定要離開嗎(Want to leave it)?")
                    .setCancelable(false)
                    .setPositiveButton("確定(Yes)", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消(NO)", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
