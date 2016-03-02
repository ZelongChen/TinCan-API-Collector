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


/**
 * A simple {@link Fragment} subclass.
 */
public class StatementsFragment extends Fragment {


    public StatementsFragment() {
        // Required empty public constructor
    }

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> statements = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statements, container, false);
        listView = (ListView) view.findViewById(R.id.statements_list);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        downloadStatements();
    }

    private void downloadStatements() {
        RestClient.get("statements", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject actor = response.getJSONObject(i).getJSONObject("actor");
                        JSONObject verb = response.getJSONObject(i).getJSONObject("verb");
                        JSONObject object = response.getJSONObject(i).getJSONObject("object").getJSONObject("data");
                        statements.add(actor.getString("name") + " " + verb.getString("display") + " " + object.getString("name"));
                    } catch (JSONException e) {
                        Log.d("JSON Exception", "onSuccess: " + e.getLocalizedMessage());
                    }
                }
                adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, statements.toArray());
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
