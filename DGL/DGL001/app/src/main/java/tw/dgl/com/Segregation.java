package tw.dgl.com;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Segregation extends AppCompatActivity {

    Spinner spinner1,spinner2;
    Button  button;
    TextView text;

    String[] value1 = new String[]{"1.1 1.2 1.5", "1.3 1.6", "1.4", "2.1", "2.2", "2.3","3"
            , "4.1", "4.2", "5.1", "5.2", "6.1", "6.2", "7", "8", "9"};
    String[] value2 = new String[]{"1.1 1.2 1.5", "1.3 1.6", "1.4", "2.1", "2.2", "2.3","3"
            , "4.1", "4.2", "5.1", "5.2", "6.1", "6.2", "7", "8", "9"};


    private MyAdapter myAdapter;
    private ListView listView;

    String str,str2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segregation);

        spinner1 = (Spinner) findViewById(R.id.Spinner1);
        ArrayAdapter<String> sper1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, value1);
        sper1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(sper1);
        spinner1.setOnItemSelectedListener(sp1);

        spinner2 = (Spinner) findViewById(R.id.Spinner2);
        ArrayAdapter<String> sper2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, value2);
        sper2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(sper2);
        spinner2.setOnItemSelectedListener(sp2);

        listView = (ListView)findViewById(R.id.listView);
        myAdapter=new MyAdapter(Segregation.this);
        listView.setAdapter(myAdapter);

        button = (Button)findViewById(R.id.but);
        button.setOnClickListener(sumit);
        text = (TextView)findViewById(R.id.text);
    }


    private Spinner.OnItemSelectedListener sp1 = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            str = parent.getSelectedItem().toString();


        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private Spinner.OnItemSelectedListener sp2 = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            str2 = parent.getSelectedItem().toString();




        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    public class MyAdapter extends BaseAdapter {
        private final ArrayList mData;
        DatabaseAccess databaseAccess;

        public MyAdapter(Context c) {
            mData = new ArrayList();
            //利用databaseAccess存取資料
            this.databaseAccess = DatabaseAccess.getInstance(c);
            databaseAccess.open();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Map.Entry<String, String> getItem(int position) {
            return (Map.Entry) mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO implement you own logic with ID
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View result;

            if (convertView == null) {
                result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            } else {
                result = convertView;
            }

            final Map.Entry<String, String> item = getItem(position);

            // TODO replace findViewById by ViewHolder
            ((TextView) result.findViewById(R.id.title)).setText(item.getValue());

            //點下跳轉
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //傳送intent中的bundle物件
                    Intent intent = new Intent();
                    intent.setClass(Segregation.this, Segregationdata.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("segregation", item.getKey());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            });

            return result;
        }

        //自訂搜尋過濾器
        public void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());


            if (mData != null) {
                mData.clear();
            }

            if (charText.length() != 0) {
                mData.addAll(databaseAccess.Search3(charText).entrySet());
            }
            notifyDataSetChanged();
        }

        /*public void filter2(String chartext) {
            chartext = chartext.toLowerCase(Locale.getDefault());
            if(mData!=null){
                mData.clear();
            }

            if (chartext.length() != 0 ) {
                mData.addAll(databaseAccess.Search3(chartext).entrySet());
            }
            notifyDataSetChanged();
        }*/
    }
        private Button.OnClickListener sumit = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
               sw();

                //text.setText(str + "-----" + str2);

                myAdapter.filter(str.toString() + str2.toString().trim());
                listView.invalidate();

            }
        };
    public void sw () {

        switch (str + str2) {
            case "1.1 1.2 1.5"+"1.1 1.2 1.5":
                text.setText("*");
                break;
            case "1.1 1.2 1.5"+"1.3 1.6":
                text.setText("*");
                break;
            case "1.1 1.2 1.5"+"1.4":
                text.setText("*");
                break;
            case "1.1 1.2 1.5"+"2.1":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"2.2":
                text.setText("2");
                break;
            case "1.1 1.2 1.5"+"2.3":
                text.setText("2");
                break;
            case "1.1 1.2 1.5"+"3":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"4.1":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"4.2":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"4.3":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"5.1":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"5.2":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"6.1":
                text.setText("2");
                break;
            case "1.1 1.2 1.5"+"6.2":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"7":
                text.setText("2");
                break;
            case "1.1 1.2 1.5"+"8":
                text.setText("4");
                break;
            case "1.1 1.2 1.5"+"9":
                text.setText("X");
                break;

            case "1.3 1.6"+"1.1 1.2 1.5":
                text.setText("*");
                break;
            case "1.3 1.6"+"1.3 1.6":
                text.setText("*");
                break;
            case "1.3 1.6"+"1.4":
                text.setText("*");
                break;
            case "1.3 1.6"+"2.1":
                text.setText("4");
                break;
            case "1.3 1.6"+"2.2":
                text.setText("2");
                break;
            case "1.3 1.6"+"2.3":
                text.setText("2");
                break;
            case "1.3 1.6"+"3":
                text.setText("4");
                break;
            case "1.3 1.6"+"4.1":
                text.setText("3");
                break;
            case "1.3 1.6"+"4.2":
                text.setText("3");
                break;
            case "1.3 1.6"+"4.3":
                text.setText("4");
                break;
            case "1.3 1.6"+"5.1":
                text.setText("4");
                break;
            case "1.3 1.6"+"5.2":
                text.setText("4");
                break;
            case "1.3 1.6"+"6.1":
                text.setText("2");
                break;
            case "1.3 1.6"+"6.2":
                text.setText("4");
                break;
            case "1.3 1.6"+"7":
                text.setText("2");
                break;
            case "1.3 1.6"+"8":
                text.setText("2");
                break;
            case "1.3 1.6"+"9":
                text.setText("X");
                break;

            case "1.4"+"1.1 1.2 1.5":
                text.setText("*");
                break;
            case "1.4"+"1.3 1.6":
                text.setText("*");
                break;
            case "1.4"+"1.4":
                text.setText("*");
                break;
            case "1.4"+"2.1":
                text.setText("2");
                break;
            case "1.4"+"2.2":
                text.setText("1");
                break;
            case "1.4"+"2.3":
                text.setText("1");
                break;
            case "1.4"+"3":
                text.setText("2");
                break;
            case "1.4"+"4.1":
                text.setText("2");
                break;
            case "1.4"+"4.2":
                text.setText("2");
                break;
            case "1.4"+"4.3":
                text.setText("2");
                break;
            case "1.4"+"5.1":
                text.setText("2");
                break;
            case "1.4"+"5.2":
                text.setText("2");
                break;
            case "1.4"+"6.1":
                text.setText("X");
                break;
            case "1.4"+"6.2":
                text.setText("4");
                break;
            case "1.4"+"7":
                text.setText("2");
                break;
            case "1.4"+"8":
                text.setText("2");
                break;
            case "1.4"+"9":
                text.setText("X");
                break;

            case "2.1"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "2.1"+"1.3 1.6":
                text.setText("4");
                break;
            case "2.1"+"1.4":
                text.setText("2");
                break;
            case "2.1"+"2.1":
                text.setText("X");
                break;
            case "2.1"+"2.2":
                text.setText("X");
                break;
            case "2.1"+"2.3":
                text.setText("X");
                break;
            case "2.1"+"3":
                text.setText("2");
                break;
            case "2.1"+"4.1":
                text.setText("1");
                break;
            case "2.1"+"4.2":
                text.setText("2");
                break;
            case "2.1"+"4.3":
                text.setText("2");
                break;
            case "2.1"+"5.1":
                text.setText("2");
                break;
            case "2.1"+"5.2":
                text.setText("2");
                break;
            case "2.1"+"6.1":
                text.setText("X");
                break;
            case "2.1"+"6.2":
                text.setText("4");
                break;
            case "2.1"+"7":
                text.setText("2");
                break;
            case "2.1"+"8":
                text.setText("1");
                break;
            case "2.1"+"9":
                text.setText("X");
                break;

            case "2.2"+"1.1 1.2 1.5":
                text.setText("2");
                break;
            case "2.2"+"1.3 1.6":
                text.setText("2");
                break;
            case "2.2"+"1.4":
                text.setText("1");
                break;
            case "2.2"+"2.1":
                text.setText("X");
                break;
            case "2.2"+"2.2":
                text.setText("X");
                break;
            case "2.2"+"2.3":
                text.setText("X");
                break;
            case "2.2"+"3":
                text.setText("1");
                break;
            case "2.2"+"4.1":
                text.setText("X");
                break;
            case "2.2"+"4.2":
                text.setText("1");
                break;
            case "2.2"+"4.3":
                text.setText("X");
                break;
            case "2.2"+"5.1":
                text.setText("X");
                break;
            case "2.2"+"5.2":
                text.setText("1");
                break;
            case "2.2"+"6.1":
                text.setText("X");
                break;
            case "2.2"+"6.2":
                text.setText("2");
                break;
            case "2.2"+"7":
                text.setText("1");
                break;
            case "2.2"+"8":
                text.setText("X");
                break;
            case "2.2"+"9":
                text.setText("X");
                break;

            case "2.3"+"1.1 1.2 1.5":
                text.setText("2");
                break;
            case "2.3"+"1.3 1.6":
                text.setText("2");
                break;
            case "2.3"+"1.4":
                text.setText("1");
                break;
            case "2.3"+"2.1":
                text.setText("X");
                break;
            case "2.3"+"2.2":
                text.setText("X");
                break;
            case "2.3"+"2.3":
                text.setText("X");
                break;
            case "2.3"+"3":
                text.setText("2");
                break;
            case "2.3"+"4.1":
                text.setText("X");
                break;
            case "2.3"+"4.2":
                text.setText("2");
                break;
            case "2.3"+"4.3":
                text.setText("X");
                break;
            case "2.3"+"5.1":
                text.setText("X");
                break;
            case "2.3"+"5.2":
                text.setText("2");
                break;
            case "2.3"+"6.1":
                text.setText("X");
                break;
            case "2.3"+"6.2":
                text.setText("2");
                break;
            case "2.3"+"7":
                text.setText("1");
                break;
            case "2.3"+"8":
                text.setText("X");
                break;
            case "2.3"+"9":
                text.setText("X");
                break;

            case "3"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "3"+"1.3 1.6":
                text.setText("4");
                break;
            case "3"+"1.4":
                text.setText("2");
                break;
            case "3"+"2.1":
                text.setText("2");
                break;
            case "3"+"2.2":
                text.setText("1");
                break;
            case "3"+"2.3":
                text.setText("2");
                break;
            case "3"+"3":
                text.setText("X");
                break;
            case "3"+"4.1":
                text.setText("X");
                break;
            case "3"+"4.2":
                text.setText("X");
                break;
            case "3"+"4.3":
                text.setText("2");
                break;
            case "3"+"5.1":
                text.setText("2");
                break;
            case "3"+"5.2":
                text.setText("2");
                break;
            case "3"+"6.1":
                text.setText("X");
                break;
            case "3"+"6.2":
                text.setText("3");
                break;
            case "3"+"7":
                text.setText("2");
                break;
            case "3"+"8":
                text.setText("X");
                break;
            case "3"+"9":
                text.setText("X");
                break;

            case "4.1"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "4.1"+"1.3 1.6":
                text.setText("3");
                break;
            case "4.1"+"1.4":
                text.setText("2");
                break;
            case "4.1"+"2.1":
                text.setText("1");
                break;
            case "4.1"+"2.2":
                text.setText("X");
                break;
            case "4.1"+"2.3":
                text.setText("X");
                break;
            case "4.1"+"3":
                text.setText("X");
                break;
            case "4.1"+"4.1":
                text.setText("X");
                break;
            case "4.1"+"4.2":
                text.setText("1");
                break;
            case "4.1"+"4.3":
                text.setText("X");
                break;
            case "4.1"+"5.1":
                text.setText("1");
                break;
            case "4.1"+"5.2":
                text.setText("2");
                break;
            case "4.1"+"6.1":
                text.setText("X");
                break;
            case "4.1"+"6.2":
                text.setText("3");
                break;
            case "4.1"+"7":
                text.setText("2");
                break;
            case "4.1"+"8":
                text.setText("1");
                break;
            case "4.1"+"9":
                text.setText("X");
                break;

            case "4.2"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "4.2"+"1.3 1.6":
                text.setText("3");
                break;
            case "4.2"+"1.4":
                text.setText("2");
                break;
            case "4.2"+"2.1":
                text.setText("2");
                break;
            case "4.2"+"2.2":
                text.setText("1");
                break;
            case "4.2"+"2.3":
                text.setText("2");
                break;
            case "4.2"+"3":
                text.setText("2");
                break;
            case "4.2"+"4.1":
                text.setText("1");
                break;
            case "4.2"+"4.2":
                text.setText("X");
                break;
            case "4.2"+"4.3":
                text.setText("1");
                break;
            case "4.2"+"5.1":
                text.setText("2");
                break;
            case "4.2"+"5.2":
                text.setText("2");
                break;
            case "4.2"+"6.1":
                text.setText("1");
                break;
            case "4.2"+"6.2":
                text.setText("3");
                break;
            case "4.2"+"7":
                text.setText("2");
                break;
            case "4.2"+"8":
                text.setText("1");
                break;
            case "4.2"+"9":
                text.setText("X");
                break;

            case "4.3"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "4.3"+"1.3 1.6":
                text.setText("4");
                break;
            case "4.3"+"1.4":
                text.setText("2");
                break;
            case "4.3"+"2.1":
                text.setText("2");
                break;
            case "4.3"+"2.2":
                text.setText("X");
                break;
            case "4.3"+"2.3":
                text.setText("X");
                break;
            case "4.3"+"3":
                text.setText("2");
                break;
            case "4.3"+"4.1":
                text.setText("X");
                break;
            case "4.3"+"4.2":
                text.setText("1");
                break;
            case "4.3"+"4.3":
                text.setText("X");
                break;
            case "4.3"+"5.1":
                text.setText("2");
                break;
            case "4.3"+"5.2":
                text.setText("2");
                break;
            case "4.3"+"6.1":
                text.setText("X");
                break;
            case "4.3"+"6.2":
                text.setText("2");
                break;
            case "4.3"+"7":
                text.setText("2");
                break;
            case "4.3"+"8":
                text.setText("1");
                break;
            case "4.3"+"9":
                text.setText("X");
                break;

            case "5.1"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "5.1"+"1.3 1.6":
                text.setText("4");
                break;
            case "5.1"+"1.4":
                text.setText("2");
                break;
            case "5.1"+"2.1":
                text.setText("2");
                break;
            case "5.1"+"2.2":
                text.setText("X");
                break;
            case "5.1"+"2.3":
                text.setText("X");
                break;
            case "5.1"+"3":
                text.setText("2");
                break;
            case "5.1"+"4.1":
                text.setText("1");
                break;
            case "5.1"+"4.2":
                text.setText("2");
                break;
            case "5.1"+"4.3":
                text.setText("2");
                break;
            case "5.1"+"5.1":
                text.setText("X");
                break;
            case "5.1"+"5.2":
                text.setText("2");
                break;
            case "5.1"+"6.1":
                text.setText("1");
                break;
            case "5.1"+"6.2":
                text.setText("3");
                break;
            case "5.1"+"7":
                text.setText("1");
                break;
            case "5.1"+"8":
                text.setText("2");
                break;
            case "5.1"+"9":
                text.setText("X");
                break;

            case "5.2"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "5.2"+"1.3 1.6":
                text.setText("4");
                break;
            case "5.2"+"1.4":
                text.setText("2");
                break;
            case "5.2"+"2.1":
                text.setText("2");
                break;
            case "5.2"+"2.2":
                text.setText("1");
                break;
            case "5.2"+"2.3":
                text.setText("2");
                break;
            case "5.2"+"3":
                text.setText("2");
                break;
            case "5.2"+"4.1":
                text.setText("2");
                break;
            case "5.2"+"4.2":
                text.setText("2");
                break;
            case "5.2"+"4.3":
                text.setText("2");
                break;
            case "5.2"+"5.1":
                text.setText("2");
                break;
            case "5.2"+"5.2":
                text.setText("X");
                break;
            case "5.2"+"6.1":
                text.setText("1");
                break;
            case "5.2"+"6.2":
                text.setText("3");
                break;
            case "5.2"+"7":
                text.setText("2");
                break;
            case "5.2"+"8":
                text.setText("2");
                break;
            case "5.2"+"9":
                text.setText("X");
                break;

            case "6.1"+"1.1 1.2 1.5":
                text.setText("2");
                break;
            case "6.1"+"1.3 1.6":
                text.setText("2");
                break;
            case "6.1"+"1.4":
                text.setText("X");
                break;
            case "6.1"+"2.1":
                text.setText("X");
                break;
            case "6.1"+"2.2":
                text.setText("X");
                break;
            case "6.1"+"2.3":
                text.setText("X");
                break;
            case "6.1"+"3":
                text.setText("X");
                break;
            case "6.1"+"4.1":
                text.setText("X");
                break;
            case "6.1"+"4.2":
                text.setText("1");
                break;
            case "6.1"+"4.3":
                text.setText("X");
                break;
            case "6.1"+"5.1":
                text.setText("2");
                break;
            case "6.1"+"5.2":
                text.setText("1");
                break;
            case "6.1"+"6.1":
                text.setText("X");
                break;
            case "6.1"+"6.2":
                text.setText("1");
                break;
            case "6.1"+"7":
                text.setText("X");
                break;
            case "6.1"+"8":
                text.setText("X");
                break;
            case "6.1"+"9":
                text.setText("X");
                break;

            case "6.2"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "6.2"+"1.3 1.6":
                text.setText("4");
                break;
            case "6.2"+"1.4":
                text.setText("4");
                break;
            case "6.2"+"2.1":
                text.setText("4");
                break;
            case "6.2"+"2.2":
                text.setText("2");
                break;
            case "6.2"+"2.3":
                text.setText("2");
                break;
            case "6.2"+"3":
                text.setText("3");
                break;
            case "6.2"+"4.1":
                text.setText("3");
                break;
            case "6.2"+"4.2":
                text.setText("3");
                break;
            case "6.2"+"4.3":
                text.setText("2");
                break;
            case "6.2"+"5.1":
                text.setText("3");
                break;
            case "6.2"+"5.2":
                text.setText("3");
                break;
            case "6.2"+"6.1":
                text.setText("1");
                break;
            case "6.2"+"6.2":
                text.setText("X");
                break;
            case "6.2"+"7":
                text.setText("3");
                break;
            case "6.2"+"8":
                text.setText("3");
                break;
            case "6.2"+"9":
                text.setText("X");
                break;

            case "7"+"1.1 1.2 1.5":
                text.setText("2");
                break;
            case "7"+"1.3 1.6":
                text.setText("2");
                break;
            case "7"+"1.4":
                text.setText("2");
                break;
            case "7"+"2.1":
                text.setText("2");
                break;
            case "7"+"2.2":
                text.setText("1");
                break;
            case "7"+"2.3":
                text.setText("1");
                break;
            case "7"+"3":
                text.setText("2");
                break;
            case "7"+"4.1":
                text.setText("2");
                break;
            case "7"+"4.2":
                text.setText("2");
                break;
            case "7"+"4.3":
                text.setText("2");
                break;
            case "7"+"5.1":
                text.setText("1");
                break;
            case "7"+"5.2":
                text.setText("2");
                break;
            case "7"+"6.1":
                text.setText("X");
                break;
            case "7"+"6.2":
                text.setText("3");
                break;
            case "7"+"7":
                text.setText("X");
                break;
            case "7"+"8":
                text.setText("2");
                break;
            case "7"+"9":
                text.setText("X");
                break;

            case "8"+"1.1 1.2 1.5":
                text.setText("4");
                break;
            case "8"+"1.3 1.6":
                text.setText("2");
                break;
            case "8"+"1.4":
                text.setText("2");
                break;
            case "8"+"2.1":
                text.setText("1");
                break;
            case "8"+"2.2":
                text.setText("X");
                break;
            case "8"+"2.3":
                text.setText("X");
                break;
            case "8"+"3":
                text.setText("X");
                break;
            case "8"+"4.1":
                text.setText("1");
                break;
            case "8"+"4.2":
                text.setText("1");
                break;
            case "8"+"4.3":
                text.setText("1");
                break;
            case "8"+"5.1":
                text.setText("2");
                break;
            case "8"+"5.2":
                text.setText("2");
                break;
            case "8"+"6.1":
                text.setText("X");
                break;
            case "8"+"6.2":
                text.setText("3");
                break;
            case "8"+"7":
                text.setText("2");
                break;
            case "8"+"8":
                text.setText("X");
                break;
            case "8"+"9":
                text.setText("X");
                break;

            case "9"+"1.1 1.2 1.5":
                text.setText("X");
                break;
            case "9"+"1.3 1.6":
                text.setText("X");
                break;
            case "9"+"1.4":
                text.setText("X");
                break;
            case "9"+"2.1":
                text.setText("X");
                break;
            case "9"+"2.2":
                text.setText("X");
                break;
            case "9"+"2.3":
                text.setText("X");
                break;
            case "9"+"3":
                text.setText("X");
                break;
            case "9"+"4.1":
                text.setText("X");
                break;
            case "9"+"4.2":
                text.setText("X");
                break;
            case "9"+"4.3":
                text.setText("X");
                break;
            case "9"+"5.1":
                text.setText("2");
                break;
            case "9"+"5.2":
                text.setText("X");
                break;
            case "9"+"6.1":
                text.setText("X");
                break;
            case "9"+"6.2":
                text.setText("X");
                break;
            case "9"+"7":
                text.setText("X");
                break;
            case "9"+"8":
                text.setText("X");
                break;
            case "9"+"9":
                text.setText("X");
                break;

            default:
                text.setText(str + str2);
        }
    }

}





