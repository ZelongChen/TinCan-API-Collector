package com.example.zelong.tincanapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zelong.tincanapi.Tools.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class VerbsFragment extends Fragment {


    public VerbsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verbs, container, false);
        listView = (ListView) view.findViewById(R.id.verbs_list);

        return view;
    }
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> verbs = new ArrayList();

    @Override
    public void onResume() {
        super.onResume();
        downloadVerbs();
    }

    private void downloadVerbs() {
        RestClient.get("verbs", null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        verbs.add(response.getJSONObject(i).getString("display"));
                    } catch (JSONException e) {
                        Log.d("JSON Exception", "onSuccess: " + e.getLocalizedMessage());
                    }
                }
                adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, verbs.toArray());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("", "onSuccess: " + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("", "onSuccess: " + errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("", "onSuccess: " + errorResponse);
            }
        });
    }
}
