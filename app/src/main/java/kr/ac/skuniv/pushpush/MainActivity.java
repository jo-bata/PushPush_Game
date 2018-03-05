package kr.ac.skuniv.pushpush;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    /*********************************************************************************************/
    // 멤버 변수
    GameModel gameModel = new GameModel();
    ImageView imageView;
    ImageButton left_button, right_button, up_button, down_button, goback_button, restage_button, gomenu_button;
    TextView stage, moves, time, name;
    Timer timer;
    MediaPlayer mPlayer, mPlayer2;
    SoundPool sPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    Boolean chance = true;
    /*********************************************************************************************/
    // onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = new ImageView(this);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.mainLayout);
        left_button = (ImageButton)findViewById(R.id.leftButton);
        right_button = (ImageButton)findViewById(R.id.rightButton);
        up_button = (ImageButton)findViewById(R.id.upButton);
        down_button = (ImageButton)findViewById(R.id.downButton);
        goback_button = (ImageButton)findViewById(R.id.goback_button);
        restage_button = (ImageButton)findViewById(R.id.restage_button);
        gomenu_button = (ImageButton)findViewById(R.id.gomenu_button);
        final int move = sPool.load(MainActivity.this, R.raw.move, 1);
        final int banana2 = sPool.load(MainActivity.this, R.raw.banana2, 1);
        final int menuclick = sPool.load(MainActivity.this, R.raw.menuclick, 1);
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.djbj);
        mPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.fail);
        stage = (TextView)findViewById(R.id.stage);
        stage.setText("" + (gameModel.getStageNum() + 1));
        moves = (TextView)findViewById(R.id.moves);
        moves.setText("" + gameModel.getMovesNum());
        time = (TextView)findViewById(R.id.time);
        time.setText("00:00");
        /*********************************************************************************************/
        // Timer class의 객체를 생성하여 1초 단위로 시간을 계산
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.setTimeNum(gameModel.getTimeNum() + 1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(gameModel.getTimeNum() < 10)
                            time.setText("00:0" + gameModel.getTimeNum());
                        else if(gameModel.getTimeNum() >= 10 && gameModel.getTimeNum() < 60)
                            time.setText("00:" + gameModel.getTimeNum());
                        else if(gameModel.getTimeNum() == 60)
                            time.setText("01:00");
                        else if(gameModel.getTimeNum() > 60 && gameModel.getTimeNum() < 70)
                            time.setText("01:0" + gameModel.getTimeNum() % 60);
                        else if(gameModel.getTimeNum() >= 70 && gameModel.getTimeNum() < 120)
                            time.setText("01:" + gameModel.getTimeNum() % 60);
                        else if(gameModel.getTimeNum() == 120)
                            time.setText("02:00");
                        else if(gameModel.getTimeNum() > 120){
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                        }
                    }
                });
            }
        }, 1000, 1000);
        /*********************************************************************************************/
        // OnClickListener : left_button
        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameModel.setCharacterXY(); // character의 x, y index 값 저장하기.
                if(gameModel.checkPossibleMove(gameModel.LEFT_KEY, gameModel.getCharacterX(), gameModel.getCharacterY())) {  // 움직일 수 있는지
                    if(gameModel.getMapArr(gameModel.getCharacterX(), gameModel.getCharacterY() - 1) == gameModel.BOX_IMG)
                        sPool.play(banana2, 1.0F, 1.0F, 1, 0, 1.0F);
                    else
                        sPool.play(move, 1.0F, 1.0F, 1, 0, 1.0F);
                    gameModel.setSaveMap();
                    gameModel.updateMap(gameModel.LEFT_KEY, gameModel.getCharacterX(), gameModel.getCharacterY());  // map을 update
                    gameModel.setMovesNum(gameModel.getMovesNum() + 1);
                    moves.setText("" + gameModel.getMovesNum());
                    imageView.invalidate();
                }
                switch (gameModel.getStageNum()) {
                    case 0:
                        if (gameModel.checkGoalBox() == 2) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 29) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 1:
                        if (gameModel.checkGoalBox() == 3) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 40) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 2:
                        if (gameModel.checkGoalBox() == 4) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 47) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                }
            }
        });
        /*********************************************************************************************/
        // OnClickListener : right_button
        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameModel.setCharacterXY(); // character의 x, y index 값 저장하기.
                if(gameModel.checkPossibleMove(gameModel.RIGHT_KEY, gameModel.getCharacterX(), gameModel.getCharacterY())) {  // 움직일 수 있는지 체크
                    if(gameModel.getMapArr(gameModel.getCharacterX(), gameModel.getCharacterY() + 1) == gameModel.BOX_IMG)
                        sPool.play(banana2, 1.0F, 1.0F, 1, 0, 1.0F);
                    else
                        sPool.play(move, 1.0F, 1.0F, 1, 0, 1.0F);
                    gameModel.setSaveMap();
                    gameModel.updateMap(gameModel.RIGHT_KEY, gameModel.getCharacterX(), gameModel.getCharacterY());  // map을 update
                    gameModel.setMovesNum(gameModel.getMovesNum() + 1);
                    moves.setText("" + gameModel.getMovesNum());
                    imageView.invalidate();
                }
                switch (gameModel.getStageNum()) {
                    case 0:
                        if (gameModel.checkGoalBox() == 2) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 29) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 1:
                        if (gameModel.checkGoalBox() == 3) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 40) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 2:
                        if (gameModel.checkGoalBox() == 4) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 47) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                }
            }
        });
        /*********************************************************************************************/
        // OnClickListener : up_button
        up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameModel.setCharacterXY(); // character의 x, y index 값 저장하기.
                if(gameModel.checkPossibleMove(gameModel.UP_KEY, gameModel.getCharacterX(), gameModel.getCharacterY())) {
                    if(gameModel.getMapArr(gameModel.getCharacterX() - 1, gameModel.getCharacterY()) == gameModel.BOX_IMG)
                        sPool.play(banana2, 1.0F, 1.0F, 1, 0, 1.0F);
                    else
                        sPool.play(move, 1.0F, 1.0F, 1, 0, 1.0F);
                    gameModel.setSaveMap();
                    gameModel.updateMap(gameModel.UP_KEY, gameModel.getCharacterX(), gameModel.getCharacterY());  // map을 update
                    gameModel.setMovesNum(gameModel.getMovesNum() + 1);
                    moves.setText("" + gameModel.getMovesNum());
                    imageView.invalidate();
                }
                switch (gameModel.getStageNum()) {
                    case 0:
                        if (gameModel.checkGoalBox() == 2) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 29) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 1:
                        if (gameModel.checkGoalBox() == 3) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 40) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 2:
                        if (gameModel.checkGoalBox() == 4) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 47) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                }
            }
        });
        /*********************************************************************************************/
        // OnClickListener : down_button
        down_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameModel.setCharacterXY(); // character의 x, y index 값 저장하기.
                if(gameModel.checkPossibleMove(gameModel.DOWN_KEY, gameModel.getCharacterX(), gameModel.getCharacterY())) {  // 움직일 수 있는지 체크
                    if(gameModel.getMapArr(gameModel.getCharacterX() + 1, gameModel.getCharacterY()) == gameModel.BOX_IMG)
                        sPool.play(banana2, 1.0F, 1.0F, 1, 0, 1.0F);
                    else
                        sPool.play(move, 1.0F, 1.0F, 1, 0, 1.0F);
                    gameModel.setSaveMap();
                    gameModel.updateMap(gameModel.DOWN_KEY, gameModel.getCharacterX(), gameModel.getCharacterY());  // map을 update
                    gameModel.setMovesNum(gameModel.getMovesNum() + 1);
                    moves.setText("" + gameModel.getMovesNum());
                    imageView.invalidate();
                }
                switch (gameModel.getStageNum()) {
                    case 0:
                        if (gameModel.checkGoalBox() == 2) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 29) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 1:
                        if (gameModel.checkGoalBox() == 3) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 40) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                    case 2:
                        if (gameModel.checkGoalBox() == 4) {
                            timer.cancel();
                            mPlayer.start();
                            mPlayer.setLooping(true);
                            ClearDialog clearDialog = new ClearDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer);
                            clearDialog.show();
                            break;
                        }
                        if(gameModel.getMovesNum() > 47) {
                            timer.cancel();
                            mPlayer2.start();
                            mPlayer2.setLooping(true);
                            FailDialog failDialog = new FailDialog(MainActivity.this, gameModel, MainActivity.this, mPlayer2);
                            failDialog.show();
                            break;
                        }
                }
            }
        });
        /*********************************************************************************************/
        // OnClickListener : goback_button
        goback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                if(chance) {
                    gameModel.backMap();
                    chance = false;
                    imageView.invalidate();
                    goback_button.setSelected(true);
                }
            }
        });
        /*********************************************************************************************/
        // OnClickListener : restage_button
        restage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                recreate();
            }
        });
        /*********************************************************************************************/
        // OnClickListener : gomenu_button
        gomenu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPool.play(menuclick, 1.0F, 1.0F, 1, 0, 1.0F);
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });
        frameLayout.addView(imageView, 0);
    }
    /*********************************************************************************************/
    // View를 상속받은 ImageView Inner Class
    public class ImageView extends View{
        /*********************************************************************************************/
        // 멤버 변수
        int png_image[]= { R.drawable.outside, R.drawable.wall, R.drawable.inside, R.drawable.goal, R.drawable.inside_box, R.drawable.inside_character1, R.drawable.goal_box, R.drawable.goal_character1 };
        private Bitmap image[] = new Bitmap[png_image.length]; // 이미지
        private Bitmap after_image[] = new Bitmap[png_image.length];  // 크기 변환 한 이미지
        /*********************************************************************************************/
        // ImageView 생성자
        public ImageView(Context context) {
            super(context);
            gameModel.setStageNum(getIntent().getIntExtra("stageNum", 0));
            gameModel.setNameUser(getIntent().getStringExtra("nameUser"));
            gameModel.setCharacterUser(getIntent().getIntExtra("characterUser", 0));
            switch (gameModel.getCharacterUser()) {
                case 0: png_image[5] = R.drawable.inside_character1; png_image[7] = R.drawable.goal_character1; break;
                case 1: png_image[5] = R.drawable.inside_character2; png_image[7] = R.drawable.goal_character2; break;
                case 2: png_image[5] = R.drawable.inside_character3; png_image[7] = R.drawable.goal_character3; break;
            }
            setBackgroundColor(Color.WHITE);
            // 그림 읽어들이기
            Resources r = context.getResources();
            for (int i = 0 ; i < png_image.length; i++ ) {
                image[i] = BitmapFactory.decodeResource(r, png_image[i]);
                after_image[i] = Bitmap.createScaledBitmap(image[i], gameModel.mapWidth, gameModel.mapHeight, false);
            }
        }
        /*********************************************************************************************/
        // View 클래스의 onDraw 메소드
        @Override
        protected void onDraw(Canvas canvas) {
            // 이미지 그리기
            for (int i = 0 ; i < gameModel.MAP_ROW; i ++) {
                for (int j = 0 ; j < gameModel.MAP_COL; j++) {
                    canvas.drawBitmap(after_image[gameModel.getMapArr(i,j)], gameModel.mapX + (j * gameModel.mapWidth), gameModel.mapY + (i * gameModel.mapHeight), null);
                }
            }
            super.onDraw(canvas);
        }
    }
}