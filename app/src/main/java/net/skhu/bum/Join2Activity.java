package net.skhu.bum;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

public class Join2Activity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private EditText etName;
    private EditText etEmail;
    private RadioGroup etSex;
    private EditText etAddress;

    private Button btGo;
    private FloatingActionButton fab;
    private CardView cvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);
        ShowEnterAnimation();
        initView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListener();
                animateRevealClose();
            }
        });
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
                    Explode explode = new Explode();
                    explode.setDuration(500);
                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(Join2Activity.this);
                    join();
                    Toast.makeText(Join2Activity.this, "???????????????...", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(Join2Activity.this, etPassword.getText()+":"+etRepeatPassword.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        fab = findViewById(R.id.fab);
        cvAdd = findViewById(R.id.cv_add);
        btGo = findViewById(R.id.bt_go);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etRepeatPassword = findViewById(R.id.et_repeatPassword);
        etName = findViewById(R.id.et_nickname);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                Join2Activity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    private void setListener() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPassword.equals(etRepeatPassword)) {
                    Explode explode = new Explode();
                    explode.setDuration(500);
                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(Join2Activity.this);
                    join();
                    Toast.makeText(Join2Activity.this, "???????????????...", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(Join2Activity.this, "??????????????? ???????????? ????????????.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        // doInBackground??? ???????????? ?????? ???????????? ????????? ?????? ?????????
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://54.180.145.180:8088/androidJoin");  // ?????? ????????? ????????????(localhost ??????.)
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //???????????? POST ???????????? ???????????????.
                conn.setDoOutput(true);

                // ????????? ?????? ??? ????????? ?????????.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = strings[0]; // GET???????????? ????????? POST??? ?????? ex) "id=admin&pwd=1234";
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
       /* protected Member setUser(){
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
        }*/
    }
    void join() {
        Log.w("join","???????????? ?????????");
        try {
            String id = etUsername.getText().toString();
            String pw = etPassword.getText().toString();
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String sex = "1";
            String address = etAddress.getText().toString();
            String sendMsg = "id="+id +"&pw="+pw+"&name="+name+"&email="+email+"&sex="+sex+"&address="+address;



            Log.w("????????? ?????????",id+", "+pw+", "+name+", "+email+", "+sex+", "+address);

            CustomTask task = new CustomTask();

            task.execute(sendMsg);
            Toast.makeText(Join2Activity.this, "????????????!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.w("join-error  ","???????????? ??????!");
        }
        finally {
            Intent intent2 = new Intent(Join2Activity.this, Login2Activity.class);
            startActivity(intent2);
            finish();
        }
    }
}
