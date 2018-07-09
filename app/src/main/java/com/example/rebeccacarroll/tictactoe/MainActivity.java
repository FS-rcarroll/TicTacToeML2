package com.example.rebeccacarroll.tictactoe;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public int[] game =    { 0,0,0,
                              0,0,0,
                              0,0,0};

    public int[] tiles = {R.id.tile0, R.id.tile1,R.id.tile2,R.id.tile3,R.id.tile4,R.id.tile5,R.id.tile6,R.id.tile7,R.id.tile8};
    public int player = 1;
    public int numMoves = 0;
    public Boolean humanMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);

        init();
    }
    private void init(){
        updateBoard();
        numMoves = 0;
        player = 1;
        //remove reset button
        findViewById(R.id.play_again_btn).setVisibility(View.INVISIBLE);
        findViewById(R.id.play_again_btn).setOnClickListener(null);
        //setup switch
        CompoundButton sm = findViewById(R.id.mode_switch);
        sm.setOnCheckedChangeListener(modeListener);

        //set Player banner to player 1
        ImageView v = findViewById(R.id.player);
        v.setImageResource(R.drawable.player1);
        //reset tiles
        for(int i: tiles){
            ImageView tiles  = findViewById(i);
            tiles.setOnClickListener(tileListener);
            tiles.setImageResource(R.drawable.tile);
        }
        //reset game array
        for(int i=0;i<game.length; i++){
            game[i] = 0;
            Log.d("STATE","resetting g");

        }
    }
    public CompoundButton.OnCheckedChangeListener modeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton b, boolean isChecked) {
            init();
            ImageView mode  = findViewById(R.id.mode_type);
            if(b.isChecked()){
                Log.d("STATE","checked");
                mode.setImageResource(R.drawable.computer);
            }else{
                Log.d("STATE","unchecked");
                mode.setImageResource(R.drawable.human);
            }
        }
    };
    private void move(){
        numMoves++;
        //toggle player
        if(!checkWin()) {
            ImageView v = findViewById(R.id.player);
            if (player == 1) {
                player = 2;
                v.setImageResource(R.drawable.player2);
            } else {
                player = 1;
                v.setImageResource(R.drawable.player1);
            }
        }

    }


    public View.OnClickListener resetListener  =  new View.OnClickListener() {
        public void onClick(View v) {
            init();
        }
    };
    public View.OnClickListener tileListener  =  new View.OnClickListener() {
        public void onClick(View v) {
            ImageView tile = (ImageView) v;
            int tileSelected = Integer.parseInt((String)tile.getTag());
            if(player == 2){
                tile.setImageResource(R.drawable.o);
                game[tileSelected] = 1;
                Log.d("STATE",  tile.getTag()+ "    player1");
            }else{
                tile.setImageResource(R.drawable.x);
                game[tileSelected] = -1;
                //game[Arrays.asList(tiles).indexOf(v)] = 1;
                Log.d("STATE",  tile.getTag()+"    player2");
            }
            tile.setOnClickListener(null);
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
    private void endGame(Boolean win){
        ImageView banner = findViewById(R.id.player);
        if(win){
            //who won?
            if(player == 1){
                //player 1 won
                banner.setImageResource(R.drawable.p1wins);
            }else{
                //player 2 won
                banner.setImageResource(R.drawable.p2wins);
            }
            //Log.d("STATE","win");
            for (int g: game) {
                Log.d("STATE","win"+g);
            }
        }else{
            //tie
            banner.setImageResource(R.drawable.noonewins);
            Log.d("STATE"," tie");
        }
        for(int i: tiles){
            ImageView tiles  = findViewById(i);
            tiles.setOnClickListener(null);
        }
        findViewById(R.id.play_again_btn).setVisibility(View.VISIBLE);
        findViewById(R.id.play_again_btn).setOnClickListener(resetListener);
    }
    private Boolean checkWin(){
        //horizontal checks
        if(game[0] != 0 && game[0] == game[1] && game[1] == game[2]){
            endGame(true);
            return true;
        }else if (game[3] != 0 && game[3] == game[4] && game[4]== game[5]){
            endGame(true);
            return true;
        }else if (game[6] != 0 && game[6] == game[7] && game[7] == game[8]){
            endGame(true);
            return true;
        //vertical checks
        }else if (game[0] != 0 && game[0] == game[3] && game[3] == game[6]){
            endGame(true);
            return true;
        }else if (game[1] != 0 && game[1] == game[4] && game[4] == game[7]){
            endGame(true);
            return true;
        }else if (game[2] != 0 && game[2] == game[5] && game[5] == game[8]){
            endGame(true);
            return true;
        //diagonal checks
        }else if (game[0] != 0 && game[0] == game[4] && game[4] == game[8]){
            endGame(true);
            return true;
        }else if (game[2] != 0 && game[2] == game[4] && game[4] == game[6]){
            endGame(true);
            return true;
        }else if(numMoves > 8){
            endGame(false);
            return true;
        }
        return false;
    }

}
