package com.example.user.TicTacToe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.connect3.R;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red
    private long pressedTime;
    int player = 1;

    int[] gameState = {0,0,0,0,0,0,0,0,0};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean active = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void tapped(View v)
    {
        ImageView img = (ImageView) v;
        String tag_value = img.getTag().toString();

        if (active && gameState[Integer.parseInt(tag_value)] == 0) {
            gameState[Integer.parseInt(tag_value)] = player;
            Log.i("pressed", tag_value + " selected by player" + player);

            img.setTranslationY(-1500);
            if (player == 1) {
                img.setImageResource(R.drawable.zero);
                img.animate().translationYBy(1500).rotation(360 * 10).setDuration(1000);
                player = 2;

            } else {
                img.setImageResource(R.drawable.cross);
                img.animate().translationYBy(1500).rotation(360 * 10).setDuration(1000);
                player = 1;
            }
            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]]!=0)

                {
                    active = false;
                    String winner = "";
                    if (player == 1)
                        winner = "X";
                    else
                        winner = "O";

                    //player menang

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerNotification = (TextView) findViewById(R.id.winnerNameText);

                    winnerNotification.setText(winner + " Menang");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerNotification.setVisibility(View.VISIBLE);

                    //Toast.makeText(this, "someone won", Toast.LENGTH_LONG).show();
                }


            }
            boolean check_not_zero = true;
            for (int i = 0; i < gameState.length; i++)
            {
                if (gameState[i] == 0)
                {
                    check_not_zero = false;

                }
            }
            if (check_not_zero) {
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);
                TextView winnerNotification =(TextView) findViewById(R.id.winnerNameText);
                winnerNotification.setText("Draw");
                winnerNotification.setVisibility(View.VISIBLE);
            }
        }

    }

    public void playAgain(View v){


        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerNotification = (TextView)findViewById(R.id.winnerNameText);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerNotification.setVisibility(View.INVISIBLE);

        //REMOVING IMAGES
        ImageView imageview1 = findViewById(R.id.imageView1);
        ImageView imageview2 = findViewById(R.id.imageView2);
        ImageView imageview3 = findViewById(R.id.imageView3);
        ImageView imageview4 = findViewById(R.id.imageView4);
        ImageView imageview5 = findViewById(R.id.imageView5);
        ImageView imageview6 = findViewById(R.id.imageView6);
        ImageView imageview7 = findViewById(R.id.imageView7);
        ImageView imageview8 = findViewById(R.id.imageView8);
        ImageView imageview9 = findViewById(R.id.imageView9);

        imageview1.setImageDrawable(null);
        imageview2.setImageDrawable(null);
        imageview3.setImageDrawable(null);
        imageview4.setImageDrawable(null);
        imageview5.setImageDrawable(null);
        imageview6.setImageDrawable(null);
        imageview7.setImageDrawable(null);
        imageview8.setImageDrawable(null);
        imageview9.setImageDrawable(null);
        player =1;
        for(int i=0;i<gameState.length;i++)
            gameState[i] = 0;
        active=true;


    }
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }






    }

