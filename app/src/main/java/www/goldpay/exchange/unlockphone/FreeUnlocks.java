package www.goldpay.exchange.unlockphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FreeUnlocks extends AppCompatActivity {

    private TextView alcatel,htc,huawei,iphone,lg,samsung,sony,blackberry,nokia,vivo,infinix,realme,
            mi,lenovo,microsoft;

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_unlocks);

        initViews();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        alcatel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeUnlocks.this, SearchScreen.class);
                intent.putExtra("txt1","Unlock Alcatel by IMEI");
                intent.putExtra("txt2","Unlock Alcatel phone by IMEI in minutes safely, fast and from the control of your home. Compatible with AT&T-mobile, MetroPCS, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                startActivity(intent);
            }
        });

        htc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeUnlocks.this, SearchScreen.class);
                intent.putExtra("txt1","Unlock HTC by IMEI");
                intent.putExtra("txt2","Unlock HTC phone by IMEI in minutes safely, fast and from the control of your home. Compatible with AT&T-mobile, MetroPCS, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                startActivity(intent);
            }
        });

        huawei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeUnlocks.this, SearchScreen.class);
                intent.putExtra("txt1","Unlock Huawei by IMEI");
                intent.putExtra("txt2","Unlock Huawei phone by IMEI in minutes safely, fast and from the control of your home. Compatible with AT&T-mobile, MetroPCS, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                startActivity(intent);
            }
        });

        iphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeUnlocks.this, SearchScreen.class);
                intent.putExtra("txt1","Unlock iPhone by IMEI");
                intent.putExtra("txt2","Unlock iPhone by IMEI in minutes safely, fast and from the control of your home. Compatible with AT&T-mobile, MetroPCS, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                startActivity(intent);
            }
        });

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeUnlocks.this, SearchScreen.class);
                intent.putExtra("txt1","Unlock LG by IMEI");
                intent.putExtra("txt2","Unlock LG phone by IMEI in minutes safely, fast and from the control of your home. Compatible with AT&T-mobile, MetroPCS, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                startActivity(intent);
            }
        });

        samsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeUnlocks.this, SearchScreen.class);
                intent.putExtra("txt1","Unlock Samsung by IMEI");
                intent.putExtra("txt2","Unlock Samsung phone by IMEI in minutes safely, fast and from the control of your home. Compatible with AT&T-mobile, MetroPCS, Cricket, Sprint, Verizon, Tracfone, Boost Mobile, Xfinity, etc...");
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        alcatel = findViewById(R.id.alcatel);
        htc = findViewById(R.id.htc);
        huawei = findViewById(R.id.huawei);
        iphone = findViewById(R.id.iphone);
        lg = findViewById(R.id.lg);
        samsung = findViewById(R.id.samsung);
        sony = findViewById(R.id.sony);
        blackberry = findViewById(R.id.blackberry);
        nokia =findViewById(R.id.nokia);
        vivo = findViewById(R.id.vivo);
        infinix = findViewById(R.id.infinix);
        realme = findViewById(R.id.realme);
        mi = findViewById(R.id.mi);
        lenovo = findViewById(R.id.lenovo);
        microsoft = findViewById(R.id.microsoft);

        btnBack = findViewById(R.id.btnBack);
    }
}