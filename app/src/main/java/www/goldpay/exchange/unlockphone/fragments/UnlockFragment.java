package www.goldpay.exchange.unlockphone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import www.goldpay.exchange.unlockphone.FreeUnlocks;
import www.goldpay.exchange.unlockphone.MoreServices;
import www.goldpay.exchange.unlockphone.R;


public class UnlockFragment extends Fragment {

    Button btnFreeUnlock,btnMoreServices;

    TextView txttt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_free_unlock, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        txttt.setSelected(true);

        btnFreeUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FreeUnlocks.class));
            }
        });

        btnMoreServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MoreServices.class));
            }
        });
    }

    private void initViews(View view) {
        btnMoreServices = view.findViewById(R.id.btnMoreServices);
        btnFreeUnlock = view.findViewById(R.id.btnFreeUnlock);
        txttt = view.findViewById(R.id.txttt);
    }
}