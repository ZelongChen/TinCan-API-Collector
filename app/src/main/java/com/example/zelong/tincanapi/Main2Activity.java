package com.example.zelong.tincanapi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.zelong.tincanapi.Tools.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Main2Activity extends AppCompatActivity {

    EditText actorText;
    EditText verbText;
    TextView objecTitle;
    EditText objectText;
    Switch swithButton;
    TableRow moreinfoRow;
    TableRow descriptionRow;
    EditText moreinfoText;
    EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        actorText = (EditText) findViewById(R.id.actor_text);
        verbText = (EditText) findViewById(R.id.verb_text);
        objecTitle = (TextView) findViewById(R.id.object_title);
        objectText = (EditText) findViewById(R.id.object_text);
        swithButton = (Switch) findViewById(R.id.object_toggle);
        moreinfoRow = (TableRow) findViewById(R.id.moreinfo_row);
        descriptionRow = (TableRow) findViewById(R.id.description_row);
        moreinfoText = (EditText) findViewById(R.id.activity_moreinfo);
        descriptionText = (EditText) findViewById(R.id.activity_description);

        swithButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    objecTitle.setText("Actor");
                    objectText.setHint("actor name");
                    moreinfoRow.setVisibility(View.INVISIBLE);
                    descriptionRow.setVisibility(View.INVISIBLE);
                } else {
                    objecTitle.setText("Activity");
                    objectText.setHint("activity name");
                    moreinfoRow.setVisibility(View.VISIBLE);
                    descriptionRow.setVisibility(View.VISIBLE);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }
        });

    }

    private void sendPost() {
        String objectType = swithButton.isChecked() ? "actor" : "activity";
        JSONObject actor = new JSONObject();
        JSONObject verb = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject object = new JSONObject();
        JSONObject json = new JSONObject();
        StringEntity entity = null;
        try {
            actor.put("name", actorText.getText().toString());
            verb.put("display", verbText.getText().toString());
            data.put("name", objectText.getText().toString());
            if (!swithButton.isChecked()) {
                data.put("moreInfo", moreinfoText.getText().toString());
                data.put("description", descriptionText.getText().toString());
            }
            object.put("objectType", objectType);
            object.put("data", data);
            json.put("actor", actor);
            json.put("verb", verb);
            json.put("object", object);
            entity = new StringEntity(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RestClient.post(this, "statements", entity, "application/json;charset=UTF-8", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(Main2Activity.this, "Sucess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
