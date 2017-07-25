package tw.dgl.com;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Policydata extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView DetailList;
    private String[] key, value;
    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policydata);
        Bundle bundle = this.getIntent().getExtras();
        String policyid = bundle.getString("policyid");

        Log.e("測試", policyid);
        //宣告資料庫物件
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();


        key = new String[]{"UNNO", "PSN", "CLASS", "SUBLABEL1",
                "SUBLABEL2", "PG", "YANG MING", "China Ocean Shipping Company", "KAWASAKI KISEN KAISHA, LTD", "HANJIN",
                "Evergreen Marine Corporation", "Compagnie Maritime D'affretement", "China Shipping Groupe", "Hapag Lloyd", "Interasia Lines Singapore Pte. Ltd", "Mediterranean Shipping Company", "MITSUI O.S.K. LINES TLD"
                , "Nippon Yusen Kaisha", "ORIENT EXPRESS LINES (S) PET LTD", "Orient Overseas Container Line",
                "Pacific Int'l Lines (PTE) Ltd", "SINOTRANS CONTAINER LINES", "STX PAN OCEAN", "TAIWAN NAVIGATION CO LTD", "T. S. MANAGEMENT CO., LTD"
                , "United Arab Shipping Company", "WAN HAI LINE", "Advance Container Lines (PTE) Ltd", "MCC Shipping", "HYUNDAI MERCHANT MARINE CO,LTD", "American  President Line",
                "Regional Container Line", "KANWAY INTERNATIONAL CO LTD", "ZIM ISRAEL NAVIGATION CO. TLD", "SIMATECH SHIPPING PTE LTD", "X-PRESS FEEDERS", "EMES FEEDERING", "SNK Line Co Ltd", "Hamburg Süd Group"};
        value = databaseAccess.Policyid(policyid);

        DetailList = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(Policydata.this, key, value);
        DetailList.setAdapter(myAdapter);

        //DetailList.setOnItemClickListener(DetailListclick);

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

            view = inflater.inflate(R.layout.detail_list_item, viewGroup, false);
            final TextView keyText, valueText;
            keyText = (TextView) view.findViewById(R.id.key);
            valueText = (TextView) view.findViewById(R.id.value);


            keyText.setText(key[i]);
//                keyText.setVisibility( View.GONE );;

            if (value[i].equals("R")) {
                valueText.setText("Restricted");
                valueText.setTextColor(Color.BLUE);
            } else if (value[i].equals("N")) {
                valueText.setText("Prohibited");
                valueText.setTextColor(Color.RED);
            } else if (value[i].equals("Y")) {
                valueText.setText("Acceptable");
            } else {
                valueText.setText(value[i]);
            }

            return view;
//            Restriction
//            Prohibited
//                valueText.setText(value[i].equals(" ") ? "-" : value[i]);


        }
    }
}

