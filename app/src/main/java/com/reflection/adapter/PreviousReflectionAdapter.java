package com.reflection.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.reflection.R;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;
import com.reflection.ReflectionApplication;
import com.reflection.model.RModel;

import android.support.v7.app.ActionBarActivity;

/*
 * The FavoriteTagHistoryItemAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite TagHistoryItems, including a
 * bigger preview image, the TagHistoryItem's rating, and a "favorite"
 * star.
 */

public class PreviousReflectionAdapter extends ParseQueryAdapter<RModel> {
    private final String TAG = PreviousReflectionAdapter.class.getSimpleName();

    public PreviousReflectionAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<RModel>() {

            public ParseQuery<RModel> create() {
                ParseQuery query = new ParseQuery("RModel");
                //query.setLimit(10);
                //query.whereEqualTo("user", ParseUser.getCurrentUser());
                //query.whereEqualTo("title", ReflectionApplication.getCurrentClueString());
                //query.whereEqualTo("personal", true);
                return query;
            }
        });
    }

    @Override
    public View getItemView(final RModel response, View v,  ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.row_previous_response,
                    null);
        }
        super.getItemView(response, v, parent);
        Log.i(TAG, " getting response "+response.getObjectId());
        //TextView date = (TextView) v.findViewById(R.id.date);
        TextView responseText = (TextView) v.findViewById(R.id.response);

        //date.setText(response.getDate("createdAt").toString());

        responseText.setText(response.getResponse());

        return v;
    }
}