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

public class SE extends AppCompatActivity {

    private MyAdapter myAdapter;
    private String[] key, value;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se);


        Bundle bundle = this.getIntent().getExtras();
        String se = bundle.getString("se");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        key = databaseAccess.getse(se, "key");
        value = databaseAccess.getse(se, "value");


        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(getApplicationContext(), key, value);
        listView.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        String[] key;
        String[] value;

        public MyAdapter(Context c, String[] key, String[] value) {
            inflater = LayoutInflater.from(c);
            this.key = key;
            this.value = value;
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
            TextView keyText, valueText;
            keyText = (TextView) view.findViewById(R.id.key);
            valueText = (TextView) view.findViewById(R.id.value);
            keyText.setText(key[i]);
            valueText.setText(value[i]);

            return view;
        }
    }
}
