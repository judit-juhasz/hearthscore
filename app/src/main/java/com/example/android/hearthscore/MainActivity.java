package com.example.android.hearthscore;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String SAVE_CURRENT_PHASE_KEY = "SAVE_CURRENT_PHASE_KEY";
    private static final String SAVE_SELECTED_PLAYER_ID_KEY = "SAVE_SELECTED_PLAYER_ID_KEY";
    private static final String SAVE_CLASS_SELECTION_PANEL_VISIBILITY_KEY = "SAVE_CLASS_SELECTION_PANEL_VISIBILITY_KEY";
    private static final String SAVE_START_BUTTON_VISIBILITY_KEY = "SAVE_START_BUTTON_VISIBILITY_KEY";
    private static final String SAVE_MESSAGE_VISIBILITY_KEY = "SAVE_MESSAGE_VISIBILITY_KEY";
    private static final String SAVE_PLAYER_1_LIFE_KEY = "SAVE_PLAYER_1_LIFE_KEY";
    private static final String SAVE_PLAYER_2_LIFE_KEY = "SAVE_PLAYER_2_LIFE_KEY";
    private static final String SAVE_PLAYER_1_SELECTED_CLASS_ID_KEY = "SAVE_PLAYER_1_SELECTED_CLASS_ID_KEY";
    private static final String SAVE_PLAYER_2_SELECTED_CLASS_ID_KEY = "SAVE_PLAYER_2_SELECTED_CLASS_ID_KEY";

    private static final int PHASE_SELECT_PLAYER = 0;
    private static final int PHASE_SELECT_WINNER = 1;

    private int mCurrentPhase;
    private int mSelectedPlayerId;
    private ViewGroup mClassSelectionPanel;
    private TextView mPlayer1LifeTextView;
    private TextView mPlayer2LifeTextView;
    private ImageView mPlayer1ClassImageView;
    private ImageView mPlayer2ClassImageView;
    private ViewGroup mStartButton;
    private TextView mMessageTextView;
    private int mPlayer1SelectedClassId;
    private int mPlayer2SelectedClassId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClassSelectionPanel = (ViewGroup) findViewById(R.id.class_selection_panel);
        mPlayer1LifeTextView = (TextView) findViewById(R.id.tv_player_1_life);
        mPlayer2LifeTextView = (TextView) findViewById(R.id.tv_player_2_life);
        mPlayer1ClassImageView = (ImageView) findViewById(R.id.iv_player_1_class);
        mPlayer2ClassImageView = (ImageView) findViewById(R.id.iv_player_2_class);
        mStartButton = ( ViewGroup) findViewById(R.id.rl_start_button);
        mMessageTextView = (TextView) findViewById(R.id.tv_message);

        switchToPhase(PHASE_SELECT_PLAYER);
        selectClassForPlayer(R.id.ib_player_1, R.id.ib_class_rogue);
        selectClassForPlayer(R.id.ib_player_2, R.id.ib_class_mage);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVE_CURRENT_PHASE_KEY, mCurrentPhase);
        outState.putInt(SAVE_SELECTED_PLAYER_ID_KEY, mSelectedPlayerId);
        outState.putInt(SAVE_CLASS_SELECTION_PANEL_VISIBILITY_KEY, mClassSelectionPanel.getVisibility());
        outState.putInt(SAVE_START_BUTTON_VISIBILITY_KEY, mStartButton.getVisibility());
        outState.putInt(SAVE_MESSAGE_VISIBILITY_KEY, mMessageTextView.getVisibility());
        outState.putString(SAVE_PLAYER_1_LIFE_KEY, mPlayer1LifeTextView.getText().toString());
        outState.putString(SAVE_PLAYER_2_LIFE_KEY, mPlayer2LifeTextView.getText().toString());
        outState.putInt(SAVE_PLAYER_1_SELECTED_CLASS_ID_KEY, mPlayer1SelectedClassId);
        outState.putInt(SAVE_PLAYER_2_SELECTED_CLASS_ID_KEY, mPlayer2SelectedClassId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentPhase = savedInstanceState.getInt(SAVE_CURRENT_PHASE_KEY);
        mSelectedPlayerId  = savedInstanceState.getInt(SAVE_SELECTED_PLAYER_ID_KEY);
        mClassSelectionPanel.setVisibility(savedInstanceState.getInt(SAVE_CLASS_SELECTION_PANEL_VISIBILITY_KEY));
        mStartButton.setVisibility(savedInstanceState.getInt(SAVE_START_BUTTON_VISIBILITY_KEY));
        mMessageTextView.setVisibility(savedInstanceState.getInt(SAVE_MESSAGE_VISIBILITY_KEY));
        mPlayer1LifeTextView.setText(savedInstanceState.getString(SAVE_PLAYER_1_LIFE_KEY));
        mPlayer2LifeTextView.setText(savedInstanceState.getString(SAVE_PLAYER_2_LIFE_KEY));

        mPlayer1SelectedClassId = savedInstanceState.getInt(SAVE_PLAYER_1_SELECTED_CLASS_ID_KEY);

        ImageButton player1SelectedClass = (ImageButton) findViewById(mPlayer1SelectedClassId);
        Drawable player1SelectedClassDrawable = player1SelectedClass.getDrawable();
        mPlayer1ClassImageView.setImageDrawable(player1SelectedClassDrawable);

        mPlayer2SelectedClassId = savedInstanceState.getInt(SAVE_PLAYER_2_SELECTED_CLASS_ID_KEY);

        ImageButton player2SelectedClass = (ImageButton) findViewById(mPlayer2SelectedClassId);
        Drawable player2SelectedClassDrawable = player2SelectedClass.getDrawable();
        mPlayer2ClassImageView.setImageDrawable(player2SelectedClassDrawable);
    }

    private void resetGame() {
        mPlayer1LifeTextView.setText(getString(R.string.default_life));
        mPlayer2LifeTextView.setText(getString(R.string.default_life));
        switchToPhase(PHASE_SELECT_PLAYER);
    }

    public void onClickResetGame(View v) {
        resetGame();
    }

    public void onClickPlayerSelection(View v) {
        ImageButton selectedPlayer = (ImageButton) v;
        if (PHASE_SELECT_PLAYER == mCurrentPhase){
            mSelectedPlayerId = selectedPlayer.getId();
            alternateClassSelectionPanelVisibility();
        } else {
            handleWinnerSelection(selectedPlayer);
        }
    }

    private void handleWinnerSelection(ImageButton selectedPlayer) {
        int selectedPlayerId = selectedPlayer.getId();
        TextView opponentPlayerLifeTextView;

        if (R.id.ib_player_1 == selectedPlayerId) {
            opponentPlayerLifeTextView = mPlayer2LifeTextView;
        } else {
            opponentPlayerLifeTextView = mPlayer1LifeTextView;
        }

        String opponentsLifeText = opponentPlayerLifeTextView.getText().toString();
        int opponentsLife = Integer.parseInt(opponentsLifeText);
        opponentsLife = opponentsLife - 1;
        opponentPlayerLifeTextView.setText(String.valueOf(opponentsLife));
        if (0 == opponentsLife) {
            if (R.id.ib_player_1 == selectedPlayerId) {
                Toast.makeText(this, "The winner is Player 1", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The winner is Player 2", Toast.LENGTH_LONG).show();
            }
            resetGame();
        } else {
            switchToPhase(PHASE_SELECT_PLAYER);
        }
    }

    public void onClickClassImage(View v) {
        ImageButton selectedClass = (ImageButton) v;
        selectClassForPlayer(mSelectedPlayerId, selectedClass);
        hideClassSelectionPanel();
    }

    private void selectClassForPlayer(int playerId, int classImageButtonId) {
        ImageButton selectedClassImageButton = (ImageButton) findViewById(classImageButtonId);
        selectClassForPlayer(playerId, selectedClassImageButton);
    }

    private void selectClassForPlayer(int playerId, ImageButton classImageButton) {
        Drawable selectedClassDrawable = classImageButton.getDrawable();
        getPlayerClassImageView(playerId).setImageDrawable(selectedClassDrawable);
        if (R.id.ib_player_1 == playerId) {
            mPlayer1SelectedClassId = classImageButton.getId();
        } else {
            mPlayer2SelectedClassId = classImageButton.getId();
        }
    }

    private ImageView getPlayerClassImageView(int playerId) {
        if (R.id.ib_player_1 == playerId) {
            return mPlayer1ClassImageView;
        } else {
            return mPlayer2ClassImageView;
        }
    }

    public void onClickStartWinnerSelection(View v) {
        switchToPhase(PHASE_SELECT_WINNER);
    }

    private void hideClassSelectionPanel() {
        mClassSelectionPanel.setVisibility(View.GONE);
    }

    private void showClassSelectionPanel() {
        mClassSelectionPanel.setVisibility(View.VISIBLE);
    }

    private void alternateClassSelectionPanelVisibility() {
        if (View.GONE == mClassSelectionPanel.getVisibility()) {
            showClassSelectionPanel();
        } else {
            hideClassSelectionPanel();
        }
    }

    private void switchToPhase(int phase) {
        if (PHASE_SELECT_PLAYER == phase) {
            showStartButton();
        } else {
            showMessage();
        }

        hideClassSelectionPanel();
        mCurrentPhase = phase;
    }

    private void showStartButton() {
        mStartButton.setVisibility(View.VISIBLE);
        mMessageTextView.setVisibility(View.GONE);
    }

    private void showMessage() {
        mStartButton.setVisibility(View.GONE);
        mMessageTextView.setVisibility(View.VISIBLE);
    }
}
