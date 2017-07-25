package tw.dgl.com;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import static tw.dgl.com.R.id.listView;
import static tw.dgl.com.R.id.listView2;

public class Segregationdata extends AppCompatActivity {

    private Adapter adapter;
    private ListView DetailList;
    private String[] key, value;
    Spinner spinner2;
    TextView textView;
    String str2;
    String str;
    DatabaseAccess databaseAccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segregationdata);

        Bundle bundle = this.getIntent().getExtras();
        String segregation = bundle.getString("segregation");


        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        key = new String[]{"1.1 , 1.2 , 1.5", "1.3 , 1.6", "1.4", "2.1", "2.2", "2.3"
                , "4.1", "4.2", "5.1", "5.2", "6.1", "6.2", "7", "8", "9"};
        value = databaseAccess.Segregation(segregation);

        String[] value2 = new String[]{"1.1 , 1.2 , 1.5", "1.3 , 1.6", "1.4", "2.1", "2.2", "2.3"
                , "4.1", "4.2", "5.1", "5.2", "6.1", "6.2", "7", "8", "9"};

        textView = (TextView)findViewById(R.id.text);
        DetailList = (ListView) findViewById(R.id.listView2);
        adapter = new Adapter(Segregationdata.this, key, value);
        DetailList.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.Spinner2);
        ArrayAdapter<String> sper2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, value2);
        sper2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(sper2);
        spinner2.setOnItemSelectedListener(sp2);



    }

    private Spinner.OnItemSelectedListener sp2 = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           str2 = parent.getSelectedItem().toString();

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    public class Adapter extends BaseAdapter {

        private LayoutInflater inflater;

        String[] key;
        String[] value;

        public Adapter(Context c, String[] key, String[] value) {
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
            view = inflater.inflate(R.layout.detail_list_item, viewGroup, false);
            final TextView keyText, valueText;
            keyText = (TextView) view.findViewById(R.id.key);
            valueText = (TextView) view.findViewById(R.id.value);
            keyText.setText(key[i]);
            if (key[i] .equals(str2) )
            {
                valueText.setTextColor(Color.GRAY);}
            valueText.setText(value[i]);
            notifyDataSetChanged();
            return view;

        }
    }

}
