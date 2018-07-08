package com.example.rebeccacarroll.tictactoe;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int[] game = {1,-1,1,-1,1,1,-1,1,1};
    private int[] tiles = {R.id.tile0, R.id.tile1,R.id.tile2,R.id.tile3,R.id.tile4,R.id.tile5,R.id.tile6,R.id.tile7,R.id.tile8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        setContentView(R.layout.activity_main);
        gameSetup();

    }
    
    protected void gameSetup(){
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
