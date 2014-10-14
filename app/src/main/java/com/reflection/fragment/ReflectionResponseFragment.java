package com.reflection.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reflection.MainActivity;
import com.reflection.R;
import com.reflection.ReflectionApplication;
import com.reflection.adapter.PreviousReflectionAdapter;


import org.w3c.dom.Text;

import java.util.List;

/**
 * This fragment displays a single clothing item
 * This will always be provided the nfcId (Barcode number) and will query parse for the item
 * This will be reused for a single item view also
 * Created by Owner on 9/24/2014.
 */
public class ReflectionResponseFragment extends Fragment {
    private final String TAG = ReflectionResponseFragment.class.getSimpleName();
    private RelativeLayout mView;
    PreviousReflectionAdapter responseAdapter;
    ImageView bottomButton1;
    ImageView bottomButton2;
    ImageView bottomBack;
    TextView question;
    ListView responseList;
    EditText response;
    public String title;
    public String reflections[] = new String[] {
            "What are the achievements you are most proud of?",
            "What are you most grateful for in life",
            "What are the most important things to you in life?",
            "What is your ideal self",
            "What does it mean to be your highest self",
            "If you have one week left to live, what would you do?",
            "If you have one month left to live, what would you do?",
            "If you have an hour left to live, what would you do",
            "What opportunities are you looking for?",
            "If you are to do something for free for the rest of your life, what would you want to do?",
            "What’s the top priority in your life right now?",
            "Are you putting any parts of your life on hold?",
            "If you had 1 million dollars, what would you do with it?",
            "What good habits do you want to cultivate?",
            "What bad habits do you want to break?",
            "What drives you?",
            "How can you make your life more meaningful, starting today?",
            "What is your ideal life partner like?",
            "Who are your mentors in life?",
            "How important is social approval for you?",
            "What was the last life lesson you learnt?",
            "When was the last time you helped someone?",
            "What is your greatest fear?",
            "Who are the 3 people who had most powerful influence on you?",
            "What is happiness for you?",
            "What do I want most in this life?",
            "What is one thing I want others to recognize or remember about me?"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = (RelativeLayout) inflater.inflate(
                R.layout.fragment_reflection_response, container, false);


        bottomButton1 = (ImageView) mView.findViewById(R.id.button_1);
        bottomButton2 = (ImageView) mView.findViewById(R.id.button_2);
        bottomBack = (ImageView) mView.findViewById(R.id.button_back_arrow);

        /*Randomly decide on the value of the question and add question to application*/
        question = (TextView) mView.findViewById(R.id.label_question);
        int randomNum = (int)(Math.random()*reflections.length);
        title = reflections[randomNum];
        question.setText(title);
        ReflectionApplication.setCurrentQuestion(title);

        //TODO: Reflection adapter
        responseList = (ListView) mView.findViewById(R.id.repsonse_list);
        responseAdapter = new PreviousReflectionAdapter(this.getActivity().getApplicationContext());
        //responseAdapter.setAutoload(false);
        //responseAdapter.setPaginationEnabled(false);
        responseList.setAdapter(responseAdapter);
        //TODO: Reflect response edit text

        return mView;
    }

    public ReflectionResponseFragment() {
        //default constructor for fragment
    }


}