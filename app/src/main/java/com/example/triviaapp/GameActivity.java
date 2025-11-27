package com.example.triviaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class GameActivity extends AppCompatActivity {

    SQLiteDatabase db;

    ArrayList<Game> gameArrayList;

    ImageView imageView;

    RadioGroup rg;

    RadioButton[] rb;

    TextView tvQ, tvTime;

    Animation animblink;

    int aquest=0, score=0;

    int codeSubj;

    CountDownTimer countDownTimer=null;

    public static int count = 0;
    public static int minute = 0;
    public static int second = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent music = new Intent(this,TriviaMusicService.class);
        startService(music);

        tvTime = findViewById(R.id.tvTime);

        animblink = AnimationUtils.loadAnimation(this, R.anim.blink_anim);

        countDownTimer = new CountDownTimer(1000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count++;
                second++;

                if (count == 60) {
                    minute++;
                    second = 0;
                }

                if(second<=9 && minute>=9) tvTime.setText("" + minute + ":0" + second);
                if(second<=9 && minute<9) tvTime.setText("0" + minute + ":0" + second);
                if(second>9 && minute<=9) tvTime.setText("0" + minute + ":" + second);
                if(second>9 && minute>9) tvTime.setText("" + minute + ":" + second);


                //setting text in play to be time count from timer in MusicAndTimerService


                if (count == 120) {
                    minute++;
                    second = 0;

                    countDownTimer.cancel();

                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("GeoUiz");
                    builder.setMessage("Your Score is " + ((100 * score) / gameArrayList.size()) + "\n" + "Continue?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Result(username TEXT, subjCode INT, time INT, date TEXT, result INT)");
                            Calendar calendar = Calendar.getInstance();

                            int year = calendar.get(Calendar.YEAR);

                            int month = calendar.get(Calendar.MONTH);

                            int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);

                            String dddd = dayofMonth + "/" + month + "/" + year;

                            db.execSQL("INSERT INTO Result VALUES ('" + MainActivity.user_ + "'," + codeSubj + "," + count + ",'" + dddd + "'," + score + ")");

                            aquest = 0;
                            score = 0;
                            count = 0;

                            countDownTimer.start();

                            if (gameArrayList.get(aquest).getPic() > 0) {
                                imageView.setImageResource(gameArrayList.get(aquest).getPic());
                            } else {
                                imageView.setVisibility(View.INVISIBLE);
                            }

                            tvQ.setText(gameArrayList.get(aquest).getQuestion());

                            rb[0].setText(gameArrayList.get(aquest).getAns1());
                            rb[1].setText(gameArrayList.get(aquest).getAns2());
                            rb[2].setText(gameArrayList.get(aquest).getAns3());
                            rb[3].setText(gameArrayList.get(aquest).getAns4());

                            rb[0].setChecked(false);
                            rb[1].setChecked(false);
                            rb[2].setChecked(false);
                            rb[3].setChecked(false);


                            dialog.cancel();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Calendar calendar = Calendar.getInstance();

                            int year = calendar.get(Calendar.YEAR);

                            int month = calendar.get(Calendar.MONTH);

                            int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);

                            String dddd = dayofMonth + "/" + month + "/" + year;

                            db.execSQL("INSERT INTO Result VALUES ('" + MainActivity.user_ + "'," + codeSubj + "," + count + ",'" + dddd + "'," + score + ")");

                            countDownTimer.cancel();
                            count = 0;

                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
            }

            @Override
            public void onFinish() {
                count = 0;
                minute = 0;
                second = 0;
            }
        }.start();


        db = openOrCreateDatabase("GeoUiz", Context.MODE_PRIVATE, null);

        Intent intent = getIntent();

        String subjecName = intent.getStringExtra("subj");


        Cursor cursor= db.rawQuery("SELECT * FROM Subject WHERE name='"+subjecName+"'",null);
        cursor.moveToFirst();

        codeSubj = cursor.getInt(0);

        Cursor q = db.rawQuery("SELECT * FROM Question WHERE subjCode="+codeSubj,null);

        gameArrayList = new ArrayList<>();

        while(q.moveToNext())
            gameArrayList.add(new Game(q.getString(1),q.getString(2),q.getString(3),q.getString(4),q.getString(5),q.getString(6),q.getInt(7)));

        aquest=0;

        imageView = findViewById(R.id.questionImg);

        tvQ = findViewById(R.id.question);

        rg = findViewById(R.id.rgQuestion);

        if (gameArrayList.get(0).getPic()>0)
        {
            imageView.setImageResource(gameArrayList.get(0).getPic());
        }
        else
        {
            imageView.setVisibility(View.INVISIBLE);
        }

        rb = new RadioButton[4];

        rb[0]=findViewById(R.id.rbans1);
        rb[1]=findViewById(R.id.rbans2);
        rb[2]=findViewById(R.id.rbans3);
        rb[3]=findViewById(R.id.rbans4);


        tvQ.setText(gameArrayList.get(0).getQuestion());

        rb[0].setText(gameArrayList.get(0).getAns1());
        rb[1].setText(gameArrayList.get(0).getAns2());
        rb[2].setText(gameArrayList.get(0).getAns3());
        rb[3].setText(gameArrayList.get(0).getAns4());


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                RadioButton radioButton = findViewById(checkedId);
                String ans = radioButton.getText().toString();

                if(ans.equals(gameArrayList.get(aquest).getCorrectAns()))
                    score++;

                aquest++;


                if(aquest==gameArrayList.size())
                {
                    if(countDownTimer!=null)
                        countDownTimer.cancel();

                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("GeoUiz");
                    builder.setMessage("Your Score is " + ((100*score)/gameArrayList.size())+"\n"+"Continue?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          //Result(username TEXT, subjCode INT, time INT, date TEXT, result INT)");
                            Calendar calendar = Calendar.getInstance();

                            int year = calendar.get(Calendar.YEAR);

                            int month = calendar.get(Calendar.MONTH);

                            int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);

                            String dddd=dayofMonth+"/"+month+"/"+year;

                            db.execSQL("INSERT INTO Result VALUES ('"+MainActivity.user_+"',"+codeSubj+","+count+",'"+dddd+"',"+score+")");

                            aquest=0;
                            score=0;
                            count=0;

                            countDownTimer.start();

                            if (gameArrayList.get(aquest).getPic()>0)
                            {
                                imageView.setImageResource(gameArrayList.get(aquest).getPic());
                            }
                            else
                            {
                                imageView.setVisibility(View.INVISIBLE);
                            }

                            tvQ.setText(gameArrayList.get(aquest).getQuestion());

                            rb[0].setText(gameArrayList.get(aquest).getAns1());
                            rb[1].setText(gameArrayList.get(aquest).getAns2());
                            rb[2].setText(gameArrayList.get(aquest).getAns3());
                            rb[3].setText(gameArrayList.get(aquest).getAns4());

                            rb[0].setChecked(false);
                            rb[1].setChecked(false);
                            rb[2].setChecked(false);
                            rb[3].setChecked(false);


                            dialog.cancel();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Calendar calendar = Calendar.getInstance();

                            int year = calendar.get(Calendar.YEAR);

                            int month = calendar.get(Calendar.MONTH);

                            int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);

                            String dddd=dayofMonth+"/"+month+"/"+year;

                            db.execSQL("INSERT INTO Result VALUES ('"+MainActivity.user_+"',"+codeSubj+","+count+",'"+dddd+"',"+score+")");

                            countDownTimer.cancel();
                            count=0;

                            finish();
                        }
                    });

                    AlertDialog dialog=builder.create();

                    dialog.show();

                }



                else
                {
                    if (gameArrayList.get(aquest).getPic()>0)
                    {
                        imageView.setImageResource(gameArrayList.get(aquest).getPic());
                    }
                    else
                    {
                        imageView.setVisibility(View.INVISIBLE);
                    }

                    tvQ.setText(gameArrayList.get(aquest).getQuestion());


                    rb[0].setText(gameArrayList.get(aquest).getAns1());
                    rb[1].setText(gameArrayList.get(aquest).getAns2());
                    rb[2].setText(gameArrayList.get(aquest).getAns3());
                    rb[3].setText(gameArrayList.get(aquest).getAns4());

                    rb[0].setChecked(false);
                    rb[1].setChecked(false);
                    rb[2].setChecked(false);
                    rb[3].setChecked(false);


                }


            }
        });


    }

}
