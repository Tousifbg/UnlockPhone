package www.goldpay.exchange.unlockphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import www.goldpay.exchange.unlockphone.fragments.IMEICheckerFragment;
import www.goldpay.exchange.unlockphone.fragments.StatusFragment;
import www.goldpay.exchange.unlockphone.fragments.UnlockFragment;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frame;
    private ImageView navImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        showFragment();


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navImage.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.START);
            }
        });

    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        UnlockFragment unlockFragment = new UnlockFragment();
        transaction.replace(R.id.frame, unlockFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initViews() {
        frame = findViewById(R.id.frame);
        navImage = findViewById(R.id.navImage);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.unlock) {
            showFragment();
        }
        else if(id == R.id.imei_checker){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            IMEICheckerFragment imeiCheckerFragment = new IMEICheckerFragment();
            transaction.replace(R.id.frame, imeiCheckerFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.status) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            StatusFragment statusFragment = new StatusFragment();
            transaction.replace(R.id.frame, statusFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        else if (id == R.id.nav_share){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Free IMEI Unlock");
            String shareMessage= "\nLet me recommend you this useful application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }

        else if (id == R.id.nav_rate){
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }
        }

        else if (id == R.id.nav_more){
            Toast.makeText(this, "Coming Soon !!!", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}