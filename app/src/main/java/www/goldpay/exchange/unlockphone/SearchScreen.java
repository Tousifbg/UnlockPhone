package www.goldpay.exchange.unlockphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.goldpay.exchange.unlockphone.Models.ModelNames;
import www.goldpay.exchange.unlockphone.adapters.AutoCompleteModelAdapter;


public class SearchScreen extends AppCompatActivity {

    private AutoCompleteTextView actv;

    private List<ModelNames> modelList;

    private AutoCompleteModelAdapter adapter;

    private String text1, text2;

    private TextView txt1, txt2;

    private String img;

    ModelNames student;

    //ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        initViews();

        //fill model
        fillModelList();

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            text1 = extras.getString("txt1");
            text2 = extras.getString("txt2");
            txt1.setText(text1);
            txt2.setText(text2);
        }
        else {
            Log.e("EXTRAS","Null");
        }

        adapter = new AutoCompleteModelAdapter(this, modelList);
        actv.setAdapter(adapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                String item = actv.getText().toString();

                //get item of autocomplete textview
                Object model_img = parent.getItemAtPosition(position);
                if (model_img instanceof ModelNames){
                    student =(ModelNames) model_img;
                }

                if (item.equals("Samsung Galaxy Note 20")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Samsung Galaxy Note 20");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Samsung Galaxy Note 10")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Samsung Galaxy Note 10");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Samsung Galaxy S21")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Samsung Galaxy S21");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Apple iPhone X")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Apple iPhone X");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Apple iPhone 12 Pro Max")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Apple iPhone 12 Pro Max");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Apple iPhone 7")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Apple iPhone 7");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Realme GT")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Realme GT");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Blackberry Bold")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Blackberry Bold");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Infinix Note 7")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Infinix Note 7");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Xiaomi Mi 11 Ultra")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Xiaomi Mi 11 Ultra");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else if (item.equals("Xiaomi Mi 10 lite")) {

                    Intent intent = new Intent(SearchScreen.this,UnlockScreen.class);
                    intent.putExtra("name","Unlock Xiaomi Mi 10 lite");
                    intent.putExtra("name2","Unlocking to USA, AT&T, T-mobile, MetroPC, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                    intent.putExtra("img",student.getModelImage());
                    startActivity(intent);

                } else {
                    Log.e("Ops","Select model first");
                }

            }
        });

    }


    private void fillModelList() {
        modelList = new ArrayList<>();
        modelList.add(new ModelNames("Samsung Galaxy Note 20", R.drawable.note20));
        modelList.add(new ModelNames("Samsung Galaxy Note 10", R.drawable.note10));
        modelList.add(new ModelNames("Samsung Galaxy S21", R.drawable.s21));
        modelList.add(new ModelNames("Apple iPhone X", R.drawable.iphonex));
        modelList.add(new ModelNames("Apple iPhone 12 Pro Max", R.drawable.iphone12promax));
        modelList.add(new ModelNames("Apple iPhone 7", R.drawable.iphone7));
        modelList.add(new ModelNames("Realme GT", R.drawable.realmegt));
        modelList.add(new ModelNames("Blackberry Bold", R.drawable.bold));
        modelList.add(new ModelNames("Infinix Note 7", R.drawable.note7));
        modelList.add(new ModelNames("Xiaomi Mi 11 Ultra", R.drawable.xiami11ultra));
        modelList.add(new ModelNames("Xiaomi Mi 10 lite", R.drawable.xiami10lite));
    }


    private void initViews() {
        actv = findViewById(R.id.actv);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
    }
}