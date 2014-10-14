package com.reflection.fragment;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseImageView;
import com.reflection.MainActivity;
import com.reflection.R;
import com.reflection.ReflectionApplication;
import com.reflection.adapter.PreviousReflectionAdapter;
import com.reflection.model.RModel;


import java.util.List;


public class MainFragment extends Fragment {
    private final String TAG = ReflectionResponseFragment.class.getSimpleName();
    private RelativeLayout mView;
    private ImageView addPersonalReflection;
    private ImageView answerReflectionQuestion;
    private ImageView backButton;
    private ParseImageView profileImage;
    private TextView username;
    private TextView lastLogin;
    private ListView personalReflections;
    private PreviousReflectionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Main Fragment Created");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = (RelativeLayout) inflater.inflate(
                R.layout.fragment_main, container, false);

        profileImage = (ParseImageView) mView.findViewById(R.id.user_image);

        personalReflections = (ListView) mView.findViewById(R.id.personal_reflections_list_view);
        adapter = new PreviousReflectionAdapter(mView.getContext().getApplicationContext());
        adapter.loadObjects();
        personalReflections.setAdapter(adapter);
        return mView;
    }

    public MainFragment() {
        //default constructor for fragment
    }
}