package tw.dgl.com;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SC extends AppCompatActivity {
    private MyAdapter myAdapter;
    private String[] key, value, value2;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc);
        Bundle bundle = this.getIntent().getExtras();
        String sc = bundle.getString("sc");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        key = databaseAccess.getsc(sc, "key");
        value = databaseAccess.getsc(sc, "value");
        value2 = databaseAccess.getsc(sc, "value2");


        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(getApplicationContext(), key, value, value2);
        listView.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        String[] key;
        String[] value;
        String[] value2;


        public MyAdapter(Context c, String[] key, String[] value, String[] value2) {
            inflater = LayoutInflater.from(c);
            this.key = key;
            this.value = value;
            this.value2 = value2;

        }

        @Override
        public int getCount() {
            return key.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = inflater.inflate(R.layout.detail_list_item2, viewGroup, false);
            TextView keyText, valueText,valueText2;
            keyText = (TextView) view.findViewById(R.id.key);
            valueText = (TextView) view.findViewById(R.id.value);
            valueText2 = (TextView) view.findViewById(R.id.value2);

            keyText.setText(key[i]);
            valueText.setText(value[i]);
            valueText2.setText(value2[i]);

            return view;
        }
    }
}
