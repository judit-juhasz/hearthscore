package com.example.android.hearthscore;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mPlayer1LifeTextView;
    private TextView mPlayer2LifeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer1LifeTextView = (TextView) findViewById(R.id.tv_player_1_life);
        mPlayer2LifeTextView = (TextView) findViewById(R.id.tv_player_2_life);

        final Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/belwe-bd-bt-bold.ttf");

        final ImageButton player1ImageButton = (ImageButton) findViewById(R.id.ib_player_1);
        player1ImageButton.post(new Runnable() {
            @Override
            public void run() {
                int height = player1ImageButton.getHeight() / 2;
                int width = player1ImageButton.getWidth() / 2;
                ImageView health = (ImageView) findViewById(R.id.iv_player_1_health_icon);
                health.getLayoutParams().height = height;
                health.getLayoutParams().width = width;
                health.requestLayout();
                mPlayer1LifeTextView.getLayoutParams().height = (health.getLayoutParams().height * 3) / 4;
                mPlayer1LifeTextView.getLayoutParams().width = health.getLayoutParams().width;
                mPlayer1LifeTextView.setTypeface(typeface);
                mPlayer1LifeTextView.requestLayout();
            }
        });

        final ImageButton player2ImageButton = (ImageButton) findViewById(R.id.ib_player_2);
        player2ImageButton.post(new Runnable() {
            @Override
            public void run() {
                int height = player2ImageButton.getHeight() / 2;
                int width = player2ImageButton.getWidth() / 2;
                ImageView health = (ImageView) findViewById(R.id.iv_player_2_health_icon);
                health.getLayoutParams().height = height;
                health.getLayoutParams().width = width;
                health.requestLayout();
                mPlayer2LifeTextView.getLayoutParams().height = (health.getLayoutParams().height * 3) / 4;
                mPlayer2LifeTextView.getLayoutParams().width = health.getLayoutParams().width;
                mPlayer2LifeTextView.setTypeface(typeface);
                mPlayer2LifeTextView.requestLayout();
            }
        });

        TextView headerTextView = (TextView)findViewById(R.id.tv_header_text);
        headerTextView.setTypeface(typeface);

        TextView player1TextView = (TextView) findViewById(R.id.tv_player_1_text);
        player1TextView.setTypeface(typeface);

        TextView player2TextView = (TextView) findViewById(R.id.tv_player_2_text);
        player2TextView.setTypeface(typeface);

        TextView playTextTextView = (TextView) findViewById(R.id.tv_play_text);
        playTextTextView.setTypeface(typeface);

        TextView messageTextView = (TextView) findViewById(R.id.tv_message);
        messageTextView.setTypeface(typeface);
    }
}
