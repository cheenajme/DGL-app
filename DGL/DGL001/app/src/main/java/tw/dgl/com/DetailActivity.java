package tw.dgl.com;

/**
 * Created by 3et3et on 16/8/5.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView DetailList;
    private String[] key, value;
    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //接收intent中的bundle物件
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");

        Log.e("測試", id);
        //宣告資料庫物件
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();


        key = new String[]{"1.UNNO", "2.PSN", "2.VARIATION", "3.CLASS", "4.SUBLABEL1",
                "4.SUBLABEL2", "4.MP", "5.PACKING GROUP", "6.SPECIAL PROVISION",
                "7a.LQ", "7b.EQ", "8.PACKING INSTRCTION", "9.PACKING PROVISION",
                "10.IBC INSTRUCTION", "11.IBC PROVISION", "13.TANK INSTRUCTION",
                "14.TANK PROVISION", "15.EMS", "16a.STOWAGE CATEGORY", "16a.STOWAGE CODE",
                "16b.SEGREGATION", "17.PROPERTIES  & OBSERVATION"};
        value = databaseAccess.idGetAll(id);

        DetailList = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(DetailActivity.this, key, value);
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

            valueText.setText(value[i].equals("") ? "-" : value[i]);


            /*
            if(value[i].equals("")){
                valueText.setText("-");
            }else{
                valueText.setText(value[i]);
            }
            */

            //把空白取代成逗點DatabaseAccess getSP搜尋會用到
            final String SP = value[i].replace(" ", ",");


            //跳轉到下一個頁面
            if (key[i].equals("6.SPECIAL PROVISION")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;//只有底線無法點擊,到下一頁
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }//判斷有值加底線,跟藍色

                // valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, additional.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("SP", SP);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });


            }
            final String ibcp = value[i].replace(" ", "','");


            //跳轉到下一個頁面
            if (key[i].equals("11.IBC PROVISION")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }

                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, IBC_P.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ibcp", ibcp);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
            }
            final String sc = value[i].replace(" ", "','");


            //跳轉到下一個頁面
            if (key[i].equals("16a.STOWAGE CATEGORY")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }


                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, SC.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("sc", sc);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
            }
            final String se = value[i].replace(" ", "','");


            //跳轉到下一個頁面
            if (key[i].equals("16b.SEGREGATION")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;

                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }


                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, SE.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("se", se);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
            final String pp = value[i].replace(" ", "','");

            //跳轉到下一個頁面
            if (key[i].equals("9.PACKING PROVISION")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }


                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, PP.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("pp", pp);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
            final String TP = value[i].replace(" ", "','");

            //跳轉到下一個頁面
            if (key[i].equals("14.TANK PROVISION")) {

                if (value[i].equals("")) {

                    valueText.setText("-");
                    return view;

                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }


                // valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件

                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, TP.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("TP", TP);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
            final String stc = value[i].replace(" ", "','");
            Log.e("ed", stc);
            //跳轉到下一個頁面
            if (key[i].equals("16a.STOWAGE CODE")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }


                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, ST_CODE.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("stc", stc);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }

            final String EQ = value[i].replace(" ", "','");


            //跳轉到下一個頁面
            if (key[i].equals("7b.EQ")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }

                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, EQ.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("EQ", EQ);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
            final String IBCI = value[i];

            //跳轉到下一個頁面
            if (key[i].equals("10.IBC INSTRUCTION")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }

                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, IBCI.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("IBCI", IBCI);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            final String PI = value[i].replace(".", "");


            //跳轉到下一個頁面 20160903 Ivan add
            if (key[i].equals("8.PACKING INSTRCTION")) {

                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }


                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, PI.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("PI", PI);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
            final String EMS = value[i].replace("-", "");
            //跳轉到下一個頁面 20160903 Ivan add
            if (key[i].equals("15.EMS")) {
                if (value[i].equals("")) {
                    valueText.setText("-");
                    return view;
                } else {
                    valueText.setText(value[i]);
                    valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    valueText.setTextColor(Color.BLUE);
                }

                //valueText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//畫底線
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //傳送intent中的bundle物件
                        Intent intent = new Intent();
                        intent.setClass(DetailActivity.this, EMS.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("EMS", EMS);
                        intent.putExtras(bundle);
                        startActivity(intent);
//                        valueText.setTextColor(Color.RED);


                    }
                });
            }
            return view;
        }

    }

}
