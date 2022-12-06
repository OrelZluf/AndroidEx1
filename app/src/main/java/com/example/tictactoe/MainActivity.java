package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean isGameActive = true;
    int[] boardState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                            {0, 4, 8}, {2, 4, 6}};
    int nCurrPlayer = 0;
    int turnsCounter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        ImageView gameStatus = findViewById(R.id.main_gameStatus);
        Button restartButton = (Button) findViewById(R.id.main_restartButton);
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (isGameActive) {
            if (boardState[tappedImage] == -1) {
                turnsCounter++;

                if (turnsCounter == 9) {
                    isGameActive = false;
                }

                boardState[tappedImage] = nCurrPlayer;

                if (nCurrPlayer == 0) {
                    nCurrPlayer = 1;
                    img.setImageResource(R.drawable.x);

                    gameStatus.setImageResource(R.drawable.oplay);
                } else {
                    nCurrPlayer = 0;
                    img.setImageResource(R.drawable.o);

                    gameStatus.setImageResource(R.drawable.xplay);
                }
            }

            boolean isDone = false;
            int winPositionIndex = 0;

            for (int[] winPosition : winPositions) {
                if (boardState[winPosition[0]] == boardState[winPosition[1]] &&
                        boardState[winPosition[1]] == boardState[winPosition[2]] &&
                        boardState[winPosition[0]] != -1) {
                    isDone = true;

                    isGameActive = false;
                    if (boardState[winPosition[0]] == 0) {
                        gameStatus.setImageResource(R.drawable.xwin);
                    } else {
                        gameStatus.setImageResource(R.drawable.owin);
                    }

                    switch(winPositionIndex){
                        case 0:
                        case 1:
                        case 2:
                            break;
                        case 3:
                            ((ImageView) findViewById(R.id.imageViewResult)).setImageResource(R.drawable.mark5);
                            break;
                        case 4:
                            ((ImageView) findViewById(R.id.imageViewResult)).setImageResource(R.drawable.mark4);
                            break;
                        case 5:
                            ((ImageView) findViewById(R.id.imageViewResult)).setImageResource(R.drawable.mark3);
                            break;
                        case 6:
                            ((ImageView) findViewById(R.id.imageViewResult)).setImageResource(R.drawable.mark2);
                            break;
                        case 7:
                            ((ImageView) findViewById(R.id.imageViewResult)).setImageResource(R.drawable.mark1);
                            break;
                    }

                    restartButton.setVisibility(View.VISIBLE);
                }

                winPositionIndex++;
            }

            if (turnsCounter == 9 && !isDone) {
                gameStatus.setImageResource(R.drawable.nowin);
                restartButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void restartGame(View view) {
        Button restartButton = (Button) findViewById(R.id.main_restartButton);
        ((ImageView) findViewById(R.id.imageViewResult)).setImageResource(R.drawable.empty);
        ImageView gameStatus = findViewById(R.id.main_gameStatus);
        gameStatus.setImageResource(R.drawable.xplay);
        isGameActive = true;
        turnsCounter = 0;
        nCurrPlayer = 0;

        for (int i = 0; i < boardState.length; i++) {
            boardState[i] = -1;
        }

        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        restartButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button restartButton = (Button) findViewById(R.id.main_restartButton);

        restartButton.setOnClickListener(view -> {
            this.restartGame(view);
        });
    }
}