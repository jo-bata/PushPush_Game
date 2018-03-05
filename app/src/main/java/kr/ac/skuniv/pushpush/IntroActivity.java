package kr.ac.skuniv.pushpush;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class IntroActivity extends Activity {
    /*********************************************************************************************/
    // 멤버 변수
    GameModel gameModel = new GameModel();
    GameModel2 gameModel2 = new GameModel2();
    ImageButton start_button, how_button, rank_button, exit_button, delete;
    MediaPlayer mPlayer;
    SoundPool sPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    /*********************************************************************************************/
    // onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.introLayout);
        start_button = (ImageButton)findViewById(R.id.startButton);
        how_button = (ImageButton)findViewById(R.id.howButton);
        rank_button = (ImageButton)findViewById(R.id.rankButton);
        exit_button = (ImageButton)findViewById(R.id.exitButton);
//        delete = (ImageButton)findViewById(R.id.delete);
        mPlayer = MediaPlayer.create(IntroActivity.this, R.raw.banana);
        final DBHelper dbHelper = new DBHelper(this, "RANKING.db", null, 1);
        final DBHelper dbHelper2 = new DBHelper(this, "RANKING2.db", null, 1);
        final DBHelper dbHelper3 = new DBHelper(this, "RANKING3.db", null, 1);
        mPlayer.start();
        mPlayer.setLooping(true);
        final int menuclick = sPool.load(IntroActivity.this, R.raw.menuclick, 1);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbHelper.deleteAll(0);
//                dbHelper2.deleteAll(1);
//                dbHelper3.deleteAll(2);
//            }
//        });
        /*********************************************************************************************/
        // OnClickListener : start_button
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                SelectDialog selectDialog = new SelectDialog(IntroActivity.this, gameModel, gameModel2, mPlayer, IntroActivity.this);
                selectDialog.show();
            }
        });
        /*********************************************************************************************/
        // OnClickListener : how_button
        how_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                Intent intent = new Intent(IntroActivity.this, HowActivity.class);
                startActivity(intent);
                finish();
                mPlayer.stop();
            }
        });
        /*********************************************************************************************/
        // OnClickListener : rank_button
        rank_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                Intent intent = new Intent(IntroActivity.this, RankingActivity.class);
                startActivity(intent);
                finish();
                mPlayer.stop();
            }
        });
        /*********************************************************************************************/
        // OnClickListener : exit_button
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                finish();
                mPlayer.stop();
            }
        });
    }
}
