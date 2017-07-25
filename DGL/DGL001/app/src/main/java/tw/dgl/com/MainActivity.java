package tw.dgl.com;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        myAdapter=new MyAdapter(MainActivity.this);
        listView.setAdapter(myAdapter);

    }

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
                    intent.setClass(MainActivity.this, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getKey());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            });

            return result;
        }

        //自訂搜尋過濾器
        public void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());

            if(mData!=null){
                mData.clear();
            }

            if (charText.length() != 0 ) {
                mData.addAll(databaseAccess.Search(charText).entrySet());
            }
            notifyDataSetChanged();
        }
    }

    //搜尋
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                //搜尋透過adapte過濾器
                myAdapter.filter(searchQuery.toString().trim());
                listView.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }



}
