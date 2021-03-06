package net.skhu.bum;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.skhu.traveler.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Login2Activity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private List<SeoulInfo> seoulInfoList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        setListener();
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btGo = findViewById(R.id.bt_go);
        fab = findViewById(R.id.fab);
    }

    private void setListener() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Explode explode = new Explode();
                explode.setDuration(500);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(Login2Activity.this);
                login();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login2Activity.this, fab, fab.getTransitionName());
                startActivity(new Intent(Login2Activity.this, Join2Activity.class), options.toBundle());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        // doInBackground??? ???????????? ?????? ???????????? ????????? ?????? ?????????
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://54.180.145.180:8088/android");  // ?????? ????????? ????????????(localhost ??????.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //???????????? POST ???????????? ???????????????.
                conn.setDoOutput(true);

                // ????????? ?????? ??? ????????? ?????????.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]; // GET???????????? ????????? POST??? ?????? ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter??? ?????? ??????
                osw.flush();

                // jsp??? ????????? ??? ??????, ???????????? ?????? ??? ??????.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // ????????? ????????? ????????? ???????????? ??????
                    Log.i("?????? ??????", conn.getResponseCode()+"??????");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ???????????? ?????? ?????? ???????????????.
            return receiveMsg;
        }
        protected Member setUser(){
            Member member = new Member();
            String[] msg = receiveMsg.split(",");
            member.setIdx(msg[0]);
            member.setId(msg[1]);
            member.setPwd(msg[2]);
            member.setName(msg[3]);
            member.setAddress(msg[4]);
            member.setGender(msg[5]);
            member.setEmail(msg[6]);

            return member;
        }
    }
    void login() {
        Log.w("login","????????? ?????????");
        try {
            String id = etUsername.getText().toString();
            String pw = etPassword.getText().toString();
            Log.w("????????? ?????????",id+", "+pw);

            CustomTask task = new CustomTask();

            String result = task.execute(id,pw).get();
            Member member = task.setUser();


            Intent intent2 = new Intent(Login2Activity.this, SearchActivity.class);
            CovidInfo covid = new CovidInfo();
            SeoulLocation location = new SeoulLocation();
            location.start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            covid.start(location.getSeoulInfo());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seoulInfoList = covid.getSeoulInfo();

            intent2.putExtra("seoulInfo", (Serializable) seoulInfoList);
            Integer a = Integer.parseInt(member.getIdx());
            intent2.putExtra("ID",a);

            startActivity(intent2);
            finish();
        } catch (Exception e) {
            Log.w("login-error","????????? ??????");
        }
    }
}
