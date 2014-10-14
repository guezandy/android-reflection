package com.reflection.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.reflection.MainActivity;
import com.reflection.R;
import com.reflection.ReflectionApplication;


import java.util.List;

/**
 * This fragment displays a single clothing item
 * This will always be provided the nfcId (Barcode number) and will query parse for the item
 * This will be reused for a single item view also
 * Created by Owner on 9/24/2014.
 */
public class SettingsFragment extends Fragment {
    private final String TAG = SettingsFragment.class.getSimpleName();
    private RelativeLayout mView;
    private RadioGroup frequencyGroup;
    private Button SaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = (RelativeLayout) inflater.inflate(
                R.layout.fragment_settings, container, false);

        // The search distance choices
        frequencyGroup = (RadioGroup) mView.findViewById(R.id.searchDistanceGroup);
        int frequency = ReflectionApplication.getFrequency();

        if (frequency == 30) {
            frequencyGroup.check(R.id.monthButton);
        } else if (frequency == 7) {
            frequencyGroup.check(R.id.weekButton);
        } else if(frequency == 14) {
            frequencyGroup.check(R.id.twoWeekButton);
        } else {
            frequencyGroup.check(R.id.dayButton);
        }
        // Set up the selection handler to save the selection to the application
        frequencyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.monthButton:
                        ReflectionApplication.setFrequency(30);
                        break;
                    case R.id.twoWeekButton:
                        ReflectionApplication.setFrequency(14);
                        break;
                    case R.id.weekButton:
                        ReflectionApplication.setFrequency(7);
                        break;
                    case R.id.dayButton:
                        ReflectionApplication.setFrequency(1);
                        break;
                }
            }
        });

        SaveButton = (Button) mView.findViewById(R.id.saveFrequency);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mView.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(mView.getContext(), "Frequency of Reflections updated", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        return mView;
    }

    public SettingsFragment() {
        //default constructor for fragment
    }


}