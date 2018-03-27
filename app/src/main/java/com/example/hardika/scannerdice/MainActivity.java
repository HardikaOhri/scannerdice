package com.example.hardika.scannerdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
private int currentvalue;
    private int currentvalue2;
     int turnscore;
     int totalscoreplayer;
    int computerscore;
    int totalscorecomputer;
    TextView textView;
    TextView textView2;
    Button button,button2,button3;
    private ImageView imageView;
    private  ImageView imageView2;
boolean chance;
boolean flag =false;
int image[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
textView=(TextView) findViewById(R.id.textView);
        imageView=(ImageView) findViewById(R.id.imageView);
imageView2=(ImageView) findViewById(R.id.imageView2);

    }

              public void computerTurn(View v)
              {
                  if(!chance)
                  {if(turnscore<20)
                  {
                      roll(null);
                      new android.os.Handler().postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              computerTurn(null);
                          }
                      },1000);
                  }
                  else
                      hold(null);

                  }

              }
    public void hold(View v) {
        if (flag == true) {
            if (chance == true) {
                totalscoreplayer += turnscore;

                chance = false;

            } else {
                totalscorecomputer += turnscore;
                chance = true;
                computerTurn(null);
            }
            updateUI(null);
            turnscore = 0;
            currentvalue = 1;


            if (totalscorecomputer > 100 || totalscoreplayer > 100) {
                Toast.makeText(MainActivity.this, (computerscore > 100 ? "Computer" : "Player") + "won", Toast.LENGTH_SHORT).show();
                reset(null);

            }
            if (!chance) {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        computerTurn(null);
                    }
                }, 1000);
            }
        }

    }

    public void updateUI(View v){
        textView.setText(" YOUR SCORE "+turnscore+"\n"+" TOTAL SCORE "+totalscoreplayer+"\n"+" TOTALCOMPUTER SCORE "+totalscorecomputer);
        Animation rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
     imageView.startAnimation(rotate);
        imageView2.startAnimation(rotate);
        imageView.setImageResource(image[currentvalue-1]);
        imageView2.setImageResource(image[currentvalue2-1]);
    }



    public void reset(View v)
    {
        totalscoreplayer=turnscore=totalscorecomputer=0;
        chance=true;
        updateUI(null);
    }
   public void roll(View v)
   {
       flag=true;

       currentvalue=new Random().nextInt(6)+1;
       currentvalue2=new Random().nextInt(6)+1;




           if (currentvalue == 1 && currentvalue2 == 1) {
               if (chance) {
                   turnscore = 0;
                   totalscoreplayer = 0;
               } else {
                   turnscore = 0;
                   totalscorecomputer = 0;
               }
                    updateUI(null);

               hold(null);
           }   else

       if(currentvalue==1 || currentvalue2==1)
       {
           turnscore=0;

           hold(null);
       }
       else
           if(currentvalue2==currentvalue)
           {
               turnscore+=currentvalue+currentvalue2;
flag=false;

           }
   else
           {
        turnscore+=currentvalue+currentvalue2;
    }
updateUI(null);}}
