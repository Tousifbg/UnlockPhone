package www.goldpay.exchange.unlockphone.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import www.goldpay.exchange.unlockphone.CompleteStatus;
import www.goldpay.exchange.unlockphone.Constants.APIS;
import www.goldpay.exchange.unlockphone.Models.IMEI_SEARCH;
import www.goldpay.exchange.unlockphone.NetworkUtils.NetworkUtils;
import www.goldpay.exchange.unlockphone.R;
import www.goldpay.exchange.unlockphone.Utils.ShowNow;
import www.goldpay.exchange.unlockphone.Utils.VolleyService;


public class StatusFragment extends Fragment {

    private LinearLayout dataCard;
    private ImageView img;
    private TextView date;
    private Button btnProcessing,btnReceived,btnCompleted;
    private EditText search_Edt;
    private TextView btnSearch;

    ShowNow showNow;
    private VolleyService volleyService;
    private String my_imei;

    private Dialog dialog1;

    private RadioButton rdBtn1,rdBtn2,rdBtn3;

    private String proccessing, received, completed, todayDate;

    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    private int days_difference;

    private LottieAnimationView animationView,animationViewInternet;

    String IMEI, DATE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        showNow=new ShowNow(getContext());
        volleyService = new VolleyService(getContext());

        //get today date
    /*    Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        todayDate = simpleDateFormat.format(currentDate);
*/
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkUtils.isNetworkConnected(getContext())){
                    animationView.setVisibility(View.GONE);
                    animationViewInternet.setVisibility(View.GONE);
                    my_imei = search_Edt.getText().toString().trim();
                    if (TextUtils.isEmpty(my_imei)){
                        search_Edt.setError("You must type IMEI first");
                    }else {
                        vollyRequest();
                    }
                }else {
                    animationViewInternet.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.GONE);
                }
            }
        });
    }

    public void vollyRequest()
    {
        showNow.showLoadingDialog(getContext());
        String url = APIS.IMEI_SUBMIT_URL;

        IMEI_SEARCH imei_search_model_class =new IMEI_SEARCH();
        imei_search_model_class.setImei(my_imei);


        volleyService.imei_search(url, imei_search_model_class,
                new VolleyService.VolleyResponseListener() {
                    @Override
                    public void onSuccess(String response) {
                        showNow.scheduleDismiss();
                        Log.d("responce", response);
                        int res = response.length();
                        Log.e("length", String.valueOf(res));
                        if (res == 49){
                            dataCard.setVisibility(View.INVISIBLE);
                            dialog1 = new Dialog(getContext());
                            dialog1.setContentView(R.layout.imei_result_dialog);
                            dialog1.getWindow().setLayout(650,800);
                            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog1.show();

                            Button btnCLOSE = dialog1.findViewById(R.id.btnCLOSE);
                            btnCLOSE.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog1.dismiss();
                                    search_Edt.setText("");
                                    animationView.setVisibility(View.VISIBLE);
                                }
                            });

                        }else {
                            dataCard.setVisibility(View.VISIBLE);
                            try {
                                JSONArray jsonObj = new JSONArray(response);

                                for (int i = 0; i < jsonObj.length(); i++) {
                                    JSONObject myOb = jsonObj.getJSONObject(i);
                                    //get api objects
                                    String ID = myOb.getString("id");
                                    IMEI = myOb.getString("imei");
                                    String COUNTRY = myOb.getString("country");
                                    DATE = myOb.getString("date");
                                    String NETWORK = myOb.getString("network");

                                    Log.e("id", ID + "imei" + IMEI
                                            + "country" + COUNTRY + "date" + DATE + "network" + NETWORK);

                                    date.setText("Date: " + DATE);

                                    try {
                                        //FIND Difference between today date and api return date
                                        getDateDiffFromNow(DATE);

                                        //check difference here
                                        if (days_difference >= 1 && days_difference < 7)
                                        {
                                            Toast.makeText(getContext(), "equal or more than 2", Toast.LENGTH_SHORT).show();
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                rdBtn1.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.colorPrimary)));

                                                rdBtn2.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                .getColor(getContext(), R.color.gray)));
                                                rdBtn3.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.gray)));

                                                rdBtn1.setChecked(true);
                                                rdBtn2.setChecked(false);
                                                rdBtn3.setChecked(false);
                                            }
                                            rdBtn1.setHighlightColor(getResources().getColor(R.color.colorPrimary));
                                            rdBtn1.setChecked(true);
                                            rdBtn2.setChecked(false);
                                            rdBtn3.setChecked(false);

                                            rdBtn2.setHighlightColor(getResources().getColor(R.color.gray));
                                            rdBtn3.setHighlightColor(getResources().getColor(R.color.gray));

                                            btnReceived.setBackground(getResources().getDrawable(R.drawable.shape_received));
                                            btnProcessing.setBackground(getResources().getDrawable(R.drawable.card_shape));
                                            btnCompleted.setBackground(getResources().getDrawable(R.drawable.card_shape));

                                        }
                                      else if (days_difference >= 7 && days_difference < 10)
                                        {
                                            Toast.makeText(getContext(), "equal or mroe than 7", Toast.LENGTH_SHORT).show();
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                rdBtn2.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.colorAccent)));

                                                rdBtn1.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.gray)));
                                                rdBtn3.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.gray)));

                                                rdBtn2.setChecked(true);
                                                rdBtn1.setChecked(false);
                                                rdBtn3.setChecked(false);
                                            }
                                            rdBtn2.setHighlightColor(getResources().getColor(R.color.colorAccent));
                                            rdBtn2.setChecked(true);
                                            rdBtn1.setChecked(false);
                                            rdBtn3.setChecked(false);

                                            rdBtn1.setHighlightColor(getResources().getColor(R.color.gray));
                                            rdBtn3.setHighlightColor(getResources().getColor(R.color.gray));

                                            btnReceived.setBackground(getResources().getDrawable(R.drawable.card_shape));
                                            btnProcessing.setBackground(getResources().getDrawable(R.drawable.shape_processing));
                                            btnCompleted.setBackground(getResources().getDrawable(R.drawable.card_shape));
                                        }
                                        else if (days_difference >= 10)
                                        {
                                            Toast.makeText(getContext(), "equal or mroe than 10", Toast.LENGTH_SHORT).show();
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                rdBtn3.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.green)));

                                                rdBtn1.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.gray)));
                                                rdBtn2.setButtonTintList(ColorStateList.valueOf(ContextCompat
                                                        .getColor(getContext(), R.color.gray)));

                                                rdBtn3.setChecked(true);
                                                rdBtn1.setChecked(false);
                                                rdBtn2.setChecked(false);
                                            }
                                            rdBtn3.setHighlightColor(getResources().getColor(R.color.green));
                                            rdBtn3.setChecked(true);
                                            rdBtn1.setChecked(false);
                                            rdBtn2.setChecked(false);

                                            rdBtn1.setHighlightColor(getResources().getColor(R.color.gray));
                                            rdBtn2.setHighlightColor(getResources().getColor(R.color.gray));

                                            btnReceived.setBackground(getResources().getDrawable(R.drawable.card_shape));
                                            btnProcessing.setBackground(getResources().getDrawable(R.drawable.card_shape));
                                            btnCompleted.setBackground(getResources().getDrawable(R.drawable.shape_completed));

                                            //click completed btn event
                                            clickCompletedBtn();
                                        }

                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        showNow.scheduleDismiss();
                        dataCard.setVisibility(View.INVISIBLE);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), ""+error.getMessage()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void clickCompletedBtn() {
        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (days_difference >= 10){
                    Intent intent = new Intent(getContext(), CompleteStatus.class);
                    intent.putExtra("my_imei",IMEI);
                    intent.putExtra("date",DATE);
                    startActivity(intent);
                }else {
                    Log.e("TOUSIF","less than 10");
                }
            }
        });
    }

    //get difference between two dates (METHOD 1)
    public static long getDateDiff(SimpleDateFormat simpleDateFormat, String todayDate, String api_date) {
        try {
            return TimeUnit.DAYS.convert(simpleDateFormat.parse(todayDate).getTime() -
                    simpleDateFormat.parse(api_date).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //get diff between two dates (METHOD 2)
    public int getDateDiffFromNow(String date){
        int days = 0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            long diff = new Date().getTime() - sdf.parse(date).getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            days = ((int) (long) hours / 24);
            //.setText(String.valueOf(days));
            days_difference = days;

            Log.d("TAG", "getDateDiffFromNow: "+days);
            Log.d("TAG", "Date "+date+" Difference From Now :"+ days + " days");


        }catch (Exception e){
            e.printStackTrace();
        }
        return days;
    }

    private void initViews(View view) {
        dataCard = view.findViewById(R.id.dataCard);
        img = view.findViewById(R.id.img);
        date = view.findViewById(R.id.date);
        btnProcessing = view.findViewById(R.id.btnProcessing);
        btnReceived = view.findViewById(R.id.btnReceived);
        btnCompleted = view.findViewById(R.id.btnCompleted);
        search_Edt = view.findViewById(R.id.search_Edt);
        btnSearch = view.findViewById(R.id.btnSearch);
        rdBtn1 = view.findViewById(R.id.rdBtn1);
        rdBtn2 = view.findViewById(R.id.rdBtn2);
        rdBtn3 = view.findViewById(R.id.rdBtn3);
        animationView = view.findViewById(R.id.animationView);
        animationViewInternet = view.findViewById(R.id.animationViewInternet);
    }
}