package com.example.android.scorekeeper;

import android.content.DialogInterface;
import android.support.annotation.MainThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Boolean gameOver=false;
    private String gameMessage="";
    private int scorePlayer_1=0;
    private int scorePlayer_2=0;
    private String player_1_name,player_2_name;
    // number of ships destroyed by each player
    private int ship_type_1_player_1_count=0,ship_type_1_player_2_count=0,
            ship_type_2_player_1_count=0,ship_type_2_player_2_count=0,
            ship_type_3_player_1_count=0,ship_type_3_player_2_count=0,
            ship_type_4_player_1_count=0,ship_type_4_player_2_count=0;

    private int shipType_1_player_1_score=0,shipType_1_player_2_score=0,
            shipType_2_player_1_score=0,shipType_2_player_2_score=0,
            shipType_3_player_1_score=0,shipType_3_player_2_score=0,
            shipType_4_player_1_score=0,shipType_4_player_2_score=0;
    private TextView mPlayer_1_name,mPlayer_2_name,mPlayer_1_score,mPlayer_2_score,
            mShipType_1_player_1_score,mShipType_1_player_2_score,
            mShipType_2_player_1_score,mShipType_2_player_2_score,
            mShipType_3_player_1_score,mShipType_3_player_2_score,
            mShipType_4_player_1_score,mShipType_4_player_2_score ;
    private Button mShip_1_player_1_button,mShip_1_player_2_button,
            mShip_2_player_1_button,mShip_2_player_2_button,
            mShip_3_player_1_button,mShip_3_player_2_button,
            mShip_4_player_1_button,mShip_4_player_2_button;
    private Button mInstructions,mReset;
    private String points;



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.PLAYER_1_SHIP_TYPE_1_COUNT,ship_type_1_player_1_count);
        outState.putInt(Constants.PLAYER_1_SHIP_TYPE_2_COUNT,ship_type_2_player_1_count);
        outState.putInt(Constants.PLAYER_1_SHIP_TYPE_3_COUNT,ship_type_3_player_1_count);
        outState.putInt(Constants.PLAYER_1_SHIP_TYPE_4_COUNT,ship_type_4_player_1_count);
        outState.putInt(Constants.PLAYER_2_SHIP_TYPE_1_COUNT,ship_type_1_player_2_count);
        outState.putInt(Constants.PLAYER_2_SHIP_TYPE_2_COUNT,ship_type_2_player_2_count);
        outState.putInt(Constants.PLAYER_2_SHIP_TYPE_3_COUNT,ship_type_3_player_2_count);
        outState.putInt(Constants.PLAYER_2_SHIP_TYPE_4_COUNT,ship_type_4_player_2_count);
        outState.putString(Constants.PLAYER1_NAME,mPlayer_1_name.getText().toString());
        outState.putString(Constants.PLAYER2_NAME,mPlayer_2_name.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ship_type_1_player_1_count=savedInstanceState.getInt(Constants.PLAYER_1_SHIP_TYPE_1_COUNT);
        ship_type_2_player_1_count=savedInstanceState.getInt(Constants.PLAYER_1_SHIP_TYPE_2_COUNT);
        ship_type_3_player_1_count=savedInstanceState.getInt(Constants.PLAYER_1_SHIP_TYPE_3_COUNT);
        ship_type_4_player_1_count=savedInstanceState.getInt(Constants.PLAYER_1_SHIP_TYPE_4_COUNT);
        ship_type_1_player_2_count=savedInstanceState.getInt(Constants.PLAYER_2_SHIP_TYPE_1_COUNT);
        ship_type_2_player_2_count=savedInstanceState.getInt(Constants.PLAYER_2_SHIP_TYPE_2_COUNT);
        ship_type_3_player_2_count=savedInstanceState.getInt(Constants.PLAYER_2_SHIP_TYPE_3_COUNT);
        ship_type_4_player_2_count=savedInstanceState.getInt(Constants.PLAYER_2_SHIP_TYPE_4_COUNT);
        mPlayer_1_name.setText(savedInstanceState.getString(Constants.PLAYER1_NAME));
        mPlayer_2_name.setText(savedInstanceState.getString(Constants.PLAYER2_NAME));


        calculateScore();
        updateUI();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer_1_name=findViewById(R.id.player_one_name);
        mPlayer_2_name=findViewById(R.id.player_two_name);
        mPlayer_1_score=findViewById(R.id.player_one_score);
        mPlayer_2_score=findViewById(R.id.player_two_score);
        mShipType_1_player_1_score=findViewById(R.id.ship_type_1_player_left_score);
        mShipType_2_player_1_score=findViewById(R.id.ship_type_2_player_left_score);
        mShipType_3_player_1_score=findViewById(R.id.ship_type_3_player_left_score);
        mShipType_4_player_1_score=findViewById(R.id.ship_type_4_player_left_score);
        mShipType_1_player_2_score=findViewById(R.id.ship_type_1_player_right_score);
        mShipType_2_player_2_score=findViewById(R.id.ship_type_2_player_right_score);
        mShipType_3_player_2_score=findViewById(R.id.ship_type_3_player_right_score);
        mShipType_4_player_2_score=findViewById(R.id.ship_type_4_player_right_score);

        mShip_1_player_1_button=findViewById(R.id.add_ship_type_1_player_1);
        mShip_1_player_1_button.setOnClickListener(this);
        mShip_2_player_1_button=findViewById(R.id.add_ship_type_2_player_1);
        mShip_2_player_1_button.setOnClickListener(this);
        mShip_3_player_1_button=findViewById(R.id.add_ship_type_3_player_1);
        mShip_3_player_1_button.setOnClickListener(this);
        mShip_4_player_1_button=findViewById(R.id.add_ship_type_4_player_1);
        mShip_4_player_1_button.setOnClickListener(this);

        mShip_1_player_2_button=findViewById(R.id.add_ship_type_1_player_2);
        mShip_1_player_2_button.setOnClickListener(this);
        mShip_2_player_2_button=findViewById(R.id.add_ship_type_2_player_2);
        mShip_2_player_2_button.setOnClickListener(this);
        mShip_3_player_2_button=findViewById(R.id.add_ship_type_3_player_2);
        mShip_3_player_2_button.setOnClickListener(this);
        mShip_4_player_2_button=findViewById(R.id.add_ship_type_4_player_2);
        mShip_4_player_2_button.setOnClickListener(this);

        mInstructions=findViewById(R.id.instructions_button);
        mInstructions.setOnClickListener(this);
        mReset=findViewById(R.id.reset_button);
        mReset.setOnClickListener(this);
        points=getResources().getString(R.string.points);
        // ?? is this a good idea
        mPlayer_2_name.setOnClickListener(this);
        mPlayer_1_name.setOnClickListener(this);

        calculateScore();
        updateUI();
    }

    @Override
    public void onClick(View view) {
        // get the reset click
        if(view.getId()==R.id.reset_button) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.reset_scores));

            builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Action for "Delete".
                    resetScores();
                }
            })
                    .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Action for "Cancel".
                        }
                    });
            AlertDialog dialog=builder.create();

            dialog.show();
            return;
        }
        // instructions
        if(view.getId()==R.id.instructions_button) {
            showInstructionsDialog();
            return ;
        }
        if(view.getId()==R.id.player_one_name) {
           updatePlayerName(1);
           return;
        }
        if(view.getId()==R.id.player_two_name) {
            updatePlayerName(2);
            return;
        }

        // if game over exit method and void further updating of the score.
        if(gameOver) {
            showWinner();
            return ;
        }

        switch (view.getId()) {
            case (R.id.add_ship_type_1_player_1):
                ship_type_1_player_1_count++;
                break;
            case (R.id.add_ship_type_2_player_1):
                ship_type_2_player_1_count++;
                break;
            case (R.id.add_ship_type_3_player_1):
                ship_type_3_player_1_count++;
                break;
            case (R.id.add_ship_type_4_player_1):
                ship_type_4_player_1_count++;
                break;
            case (R.id.add_ship_type_1_player_2):
                ship_type_1_player_2_count++;
                break;
            case (R.id.add_ship_type_2_player_2):
                ship_type_2_player_2_count++;
                break;
            case (R.id.add_ship_type_3_player_2):
                ship_type_3_player_2_count++;
                break;
            case (R.id.add_ship_type_4_player_2):
                ship_type_4_player_2_count++;
                break;
        }
        calculateScore();
        updateUI();
        if(checkWinner())
            showWinner();
    }

    private void showInstructionsDialog() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        LayoutInflater inflater= LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.instructions_dialog,null);
        Button closeButton=(Button) view.findViewById(R.id.close_button);
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  dialog.dismiss();
            }
        });
        return ;
    }

    private void updatePlayerName(final int player_no) {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        LayoutInflater inflater= LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.playername_dialog,null);
        final EditText player_name=view.findViewById(R.id.edit_text_change_player_name);
        String temp_playerName;
        if(player_no==1)
            temp_playerName=mPlayer_1_name.getText().toString();
        else
            temp_playerName=mPlayer_2_name.getText().toString();
        player_name.setText(temp_playerName);
        Button okButton=(Button) view.findViewById(R.id.ok_button);
        Button cancelButton=(Button) view.findViewById(R.id.cancel_button);

        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player_name.getText().toString().length()==0)
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.empty_player_name),Toast.LENGTH_LONG).show();
                if(player_no==1 && player_name.getText().toString().length()>0)
                    mPlayer_1_name.setText(player_name.getText().toString());
                if(player_no==2 && player_name.getText().toString().length()>0)
                    mPlayer_2_name.setText(player_name.getText().toString());

                dialog.dismiss();
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void showWinner() {
        Toast.makeText(this,gameMessage,Toast.LENGTH_SHORT).show();
    }
    private void resetScores() {
        scorePlayer_2=0;
        scorePlayer_1=0;
        ship_type_1_player_1_count=0;
        ship_type_2_player_1_count=0;
        ship_type_3_player_1_count=0;
        ship_type_4_player_1_count=0;
        ship_type_1_player_2_count=0;
        ship_type_2_player_2_count=0;
        ship_type_3_player_2_count=0;
        ship_type_4_player_2_count=0;
        gameOver=false;
        gameMessage="";
        calculateScore();
        updateUI();

    }
    private boolean checkWinner() {
        if(scorePlayer_1>=2000) {
            gameMessage=getResources().getString(R.string.game_over_2000_points,mPlayer_1_name.getText().toString(),Constants.SCORE_TARGET);
            gameOver=true;
            return true;
         }
         if(scorePlayer_2>=2000) {
             gameMessage=getResources().getString(R.string.game_over_2000_points,mPlayer_2_name.getText().toString(),Constants.SCORE_TARGET);
             gameOver=true;
            return true;
         }
         if(ship_type_4_player_1_count>=30) {
             gameMessage=getResources().getString(R.string.game_over_stars_collected,mPlayer_1_name.getText().toString(),Constants.STARS_TARGET);
             gameOver=true;
             return true;
         }
        if(ship_type_4_player_2_count>=30) {
            gameMessage=getResources().getString(R.string.game_over_stars_collected,mPlayer_2_name.getText().toString(),Constants.STARS_TARGET);
            gameOver=true;
            return true;
        }
        return false;
    }

    public void calculateScore() {
        shipType_1_player_1_score=ship_type_1_player_1_count*Constants.SHIPTYPE_1_POINTS;
        shipType_2_player_1_score=ship_type_2_player_1_count*Constants.SHIPTYPE_2_POINTS;
        shipType_3_player_1_score=ship_type_3_player_1_count*Constants.SHIPTYPE_3_POINTS;
        shipType_4_player_1_score=ship_type_4_player_1_count*Constants.SHIPTYPE_4_POINTS;
        scorePlayer_1=shipType_1_player_1_score+shipType_2_player_1_score+shipType_3_player_1_score+shipType_4_player_1_score;
        shipType_1_player_2_score=ship_type_1_player_2_count*Constants.SHIPTYPE_1_POINTS;
        shipType_2_player_2_score=ship_type_2_player_2_count*Constants.SHIPTYPE_2_POINTS;
        shipType_3_player_2_score=ship_type_3_player_2_count*Constants.SHIPTYPE_3_POINTS;
        shipType_4_player_2_score=ship_type_4_player_2_count*Constants.SHIPTYPE_4_POINTS;
        scorePlayer_2=shipType_1_player_2_score+shipType_2_player_2_score+shipType_3_player_2_score+shipType_4_player_2_score;

        // shipType_2_player_1_score=ship_type_2_player_1_count*Const
    }
    public void updateUI() {

        String temp;
        temp=padLeft(shipType_1_player_1_score,4)+" = "+padLeft(Constants.SHIPTYPE_1_POINTS,3)+points+" * "+padLeft(ship_type_1_player_1_count,3);
        mShipType_1_player_1_score.setText(temp);
        temp=padRight(ship_type_1_player_2_count,3)+" * "+padRight(Constants.SHIPTYPE_1_POINTS,3)+points+" = "+
                padRight(shipType_1_player_2_score,4);
        mShipType_1_player_2_score.setText(temp);

        temp=padLeft(shipType_2_player_1_score,4)+" = "+padLeft(Constants.SHIPTYPE_2_POINTS,3)+points+" * "+padLeft(ship_type_2_player_1_count,3);
        mShipType_2_player_1_score.setText(temp);
        temp=padRight(ship_type_2_player_2_count,3)+" * "+padRight(Constants.SHIPTYPE_2_POINTS,3)+points+" = "+
                padRight(shipType_2_player_2_score,4);
        mShipType_2_player_2_score.setText(temp);

        temp=padLeft(shipType_3_player_1_score,4)+" = "+padLeft(Constants.SHIPTYPE_3_POINTS,3)+points+" * "+padLeft(ship_type_3_player_1_count,3);
        mShipType_3_player_1_score.setText(temp);
        temp=padRight(ship_type_3_player_2_count,3)+" * "+padRight(Constants.SHIPTYPE_3_POINTS,3)+points+" = "+
                padRight(shipType_3_player_2_score,4);
        mShipType_3_player_2_score.setText(temp);

        temp=padLeft(shipType_4_player_1_score,4)+" = "+padLeft(Constants.SHIPTYPE_4_POINTS,3)+points+" * "+padLeft(ship_type_4_player_1_count,3);
        mShipType_4_player_1_score.setText(temp);
        temp=padRight(ship_type_4_player_2_count,3)+" * "+padRight(Constants.SHIPTYPE_4_POINTS,3)+points+" = "+
                padRight(shipType_4_player_2_score,4);
        mShipType_4_player_2_score.setText(temp);



        temp=String.format("%05d",scorePlayer_1);
        mPlayer_1_score.setText(temp);
        temp=String.format("%05d",scorePlayer_2);
        mPlayer_2_score.setText(temp);
    }
    String padLeft(int x,int padSize) {
        String input =String.valueOf(x);
        StringBuilder result=new StringBuilder("");
        for(int i=input.length();i<padSize;i++)
            result.append(" ");
        return result.toString()+input;
    }
    String padRight(int x,int padSize) {
        String input =String.valueOf(x);
        StringBuilder result=new StringBuilder("");
        for(int i=input.length();i<padSize;i++)
            result.append(" ");
        return input+result.toString();    }
}
