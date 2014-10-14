package com.reflection.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseImageView;
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
public class PersonalReflectionFragment extends Fragment {
    private final String TAG = PersonalReflectionFragment.class.getSimpleName();
    private RelativeLayout mView;
    private EditText title;
    private EditText response;
    private ParseImageView image;
    private ImageView backButton;
    private ImageView takeImage;
    private ImageView submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = (RelativeLayout) inflater.inflate(
                R.layout.fragment_personal_reflection, container, false);
        //TODO: EDIT TEXT CONFIG

        image = (ParseImageView) mView.findViewById(R.id.item_image);
        backButton = (ImageView) mView.findViewById(R.id.button_back_arrow);
        takeImage = (ImageView) mView.findViewById(R.id.button_photo);
        submit = (ImageView) mView.findViewById(R.id.button_submit);


        return mView;
    }

    public PersonalReflectionFragment() {
        //default constructor for fragment
    }


}