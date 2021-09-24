package www.goldpay.exchange.unlockphone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import www.goldpay.exchange.unlockphone.Constants.APIS;
import www.goldpay.exchange.unlockphone.NetworkUtils.NetworkUtils;
import www.goldpay.exchange.unlockphone.R;
import www.goldpay.exchange.unlockphone.UnlockScreen;
import www.goldpay.exchange.unlockphone.Utils.ShowNow;
import www.goldpay.exchange.unlockphone.Utils.VolleyService;

public class IMEICheckerFragment extends Fragment {

    private EditText imei_edt;
    private Button btnGo;

    private VolleyService volleyService;

    private ShowNow showNow;

    private String my_imei;

    private LinearLayout layout1,layout2;

    private ImageView device_img;
    private TextView brand_name,model_name,black_list_status, img_not_avl;
    private Button btnSearchAgain;

    private LottieAnimationView animationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_m_e_i_checker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        showNow = new ShowNow(getContext());
        volleyService = new VolleyService(getContext());

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkUtils.isNetworkConnected(getContext()))
                {
                    animationView.setVisibility(View.GONE);
                    my_imei = imei_edt.getText().toString().trim();
                    if (TextUtils.isEmpty(my_imei)){
                        imei_edt.setError("Enter IMEI");
                    }else {
                        vollyRequest();
                    }
                }
                else {
                    animationView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initViews(View view) {
        imei_edt = view.findViewById(R.id.imei_edt);
        btnGo = view.findViewById(R.id.btnGo);
        device_img = view.findViewById(R.id.device_img);
        brand_name = view.findViewById(R.id.brand_name);
        model_name = view.findViewById(R.id.model_name);
        black_list_status = view.findViewById(R.id.black_list_status);
        btnSearchAgain = view.findViewById(R.id.btnSearchAgain);
        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        img_not_avl = view.findViewById(R.id.img_not_avl);
        animationView = view.findViewById(R.id.animationView);
    }

    public void vollyRequest()
    {
        showNow.showLoadingDialog(getContext());
        String url = APIS.DEVICE_URL;

        volleyService.imei_checker(url+"?imei="+my_imei, "88",
                new VolleyService.VolleyResponseListener() {
                    @Override
                    public void onSuccess(String response) {
                        showNow.scheduleDismiss();
                        Log.d("responce", response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String BRAND       = jsonObj.getString("device_make");
                            String MODEL       = jsonObj.getString("device_name");
                            String IMG        = jsonObj.getString("device_image");

                            String img = "https:"+IMG;

                            Log.e("device_make",BRAND + "device_name" + MODEL
                                    + "device_img" + img);

                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.VISIBLE);
                            brand_name.setText(BRAND);
                            model_name.setText(MODEL);
                            black_list_status.setText("False");

                            Picasso.get()
                                    .load(img)
                                    .placeholder(R.drawable.ic_baseline_image_24)
                                    .into(device_img, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            Log.e("Success","img loaded successfully");
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            Toast.makeText(getContext(), ""+e.getMessage()
                                                    ,Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            btnSearchAgain.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    layout2.setVisibility(View.GONE);
                                    layout1.setVisibility(View.VISIBLE);
                                }
                            });

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(VolleyError error) {
                        showNow.scheduleDismiss();
                    }
                });

    }
}