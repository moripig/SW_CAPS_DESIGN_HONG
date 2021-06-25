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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Login2Activity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;

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
        // doInBackground의 매개변수 값이 여러개일 경우를 위해 배열로
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://54.180.145.180:8088/android");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();

                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 서버에서 보낸 값을 리턴합니다.
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
        Log.w("login","로그인 하는중");
        try {
            String id = etUsername.getText().toString();
            String pw = etPassword.getText().toString();
            Log.w("앱에서 보낸값",id+", "+pw);

            CustomTask task = new CustomTask();

            String result = task.execute(id,pw).get();
            Member member = task.setUser();


            Intent intent2 = new Intent(Login2Activity.this, LoginActivity.class);
            Log.w("인덱스값",member.getIdx());
            intent2.putExtra("loginId",member.getIdx());


            startActivity(intent2);
            finish();
        } catch (Exception e) {
            Log.w("login-error","로그인 실패");
        }
    }
}
