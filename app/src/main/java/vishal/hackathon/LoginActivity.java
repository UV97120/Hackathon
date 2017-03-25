package vishal.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;

import org.json.JSONException;
import org.json.JSONObject;

import vishal.hackathon.gps_tracker.gps_track;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_READ_CONTACTS = 0;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private RequestQueue registerQueue;


    private View mProgressView;
    private View mLoginFormView;

    gps_track gps_track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        //Parse.initialize(this);

//        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
//                .applicationId("myAppId")
//                .server("http://192.168.0.7:1337/parse/")   // '/' important after 'parse'
//                .build());



        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button geotag = (Button) findViewById(R.id.geotag);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view) {

                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("fname", mEmailView.getText().toString());
                    jsonObject.put("lname", mPasswordView.getText().toString());
                    Log.d("Testing", "Inside Try");
                } catch (JSONException e) {
                    Log.d("Testing", "Inside Try");
                    e.printStackTrace();
                }

                //----------------Login request--------------------

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, "http://192.168.0.6:80/hack/index.php", jsonObject,new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("response", response.toString());
                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try {
                                    Log.d("res",jsonObject.getString("status"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), error.toString()+"Bhosdike", Toast.LENGTH_LONG).show();
                                Log.d("Error", error.toString());
                                NetworkResponse response = error.networkResponse;
                                if(response != null && response.data != null){
                                    Log.d("ERROR_MESSAGE", String.valueOf(response.data));
                                }
                            }
                        });

                registerQueue = Volley.newRequestQueue(getApplicationContext());
                registerQueue.add(jsonObjectRequest);

//                ParseObject testObject = new ParseObject("TestObject");
//                testObject.put("email",mEmailView.getText().toString());
//                testObject.put("password",mPasswordView.getText().toString());
//
//                testObject.saveInBackground();

            }
        });

        geotag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_track = new gps_track(LoginActivity.this);
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void geo(View view) {
        Intent gro = new Intent(LoginActivity.this,Geo_Tag.class);
        startActivity(gro);
    }

    public void uparloading(View view) {
        Intent gro1 = new Intent(LoginActivity.this,Upload_to_server.class);
        startActivity(gro1);
    }
}


