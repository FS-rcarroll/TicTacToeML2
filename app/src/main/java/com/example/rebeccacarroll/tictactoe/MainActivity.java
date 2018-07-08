package com.example.rebeccacarroll.tictactoe;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int[] game =    { 0,0,0,
                              0,0,0,
                              0,0,0};

    private int[] tiles = {R.id.tile0, R.id.tile1,R.id.tile2,R.id.tile3,R.id.tile4,R.id.tile5,R.id.tile6,R.id.tile7,R.id.tile8};
    public int player = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        setContentView(R.layout.activity_main);

        init();
    }
    private void init(){
        updateBoard();

        for(int i: tiles){
            ImageView tiles  = findViewById(i);
            tiles.setOnClickListener(tileListener);
        }
    }
    private void move(){
        //toggle player
        checkWin();

        ImageView v = findViewById(R.id.player);
        if(player == 1){
            player = 0;
            v.setImageResource(R.drawable.player1);
        }else{
            player = 1;
            v.setImageResource(R.drawable.player2);
        }

    }
    private void checkWin(){

    }
    public View.OnClickListener tileListener  =  new View.OnClickListener() {
        public void onClick(View v) {
            ImageView tile = (ImageView) v;
            if(player == 1){
                tile.setImageResource(R.drawable.x);
            }else{
                tile.setImageResource(R.drawable.o);
            }
            move();
        }
    };
    protected void updateBoard(){
        //cycles through tiles to match them to match them to game array
        for (int i=0;i<tiles.length; i++) {
            ImageView view  = findViewById(tiles[i]);

            if(game[i] == -1){
                view.setImageResource(R.drawable.x);
            }else if(game[i] == 1){
                view.setImageResource(R.drawable.o);
            }else{
                view.setImageResource(R.drawable.tile);
            }
        }
    }
}
