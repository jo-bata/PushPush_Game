package kr.ac.skuniv.pushpush;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ClearDialog extends Dialog implements OnClickListener {
    /*********************************************************************************************/
    // 멤버 변수
    GameModel gameModel = new GameModel();
    Activity activity;
    MediaPlayer mediaPlayer;
    Context mContext = getContext();
    TextView stage_num, moves_num, time_num, grade_char;
    ImageView grade_img;
    ImageButton bt_next, bt_main;
    /*********************************************************************************************/
    // onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_clear);
        setCanceledOnTouchOutside(false);
        final DBHelper dbHelper = new DBHelper(mContext, "RANKING.db", null, 1);
        final DBHelper dbHelper2 = new DBHelper(mContext, "RANKING2.db", null, 1);
        final DBHelper dbHelper3 = new DBHelper(mContext, "RANKING3.db", null, 1);
        if(gameModel.getStageNum() == 0)
            dbHelper.insert(gameModel.getStageNum(), gameModel.getNameUser(), gameModel.getMovesNum(), gameModel.getTimeNum(), gameModel.checkGrade(gameModel.getStageNum(), gameModel.getMovesNum(), gameModel.getTimeNum()));
        if(gameModel.getStageNum() == 1)
            dbHelper2.insert(gameModel.getStageNum(), gameModel.getNameUser(), gameModel.getMovesNum(), gameModel.getTimeNum(), gameModel.checkGrade(gameModel.getStageNum(), gameModel.getMovesNum(), gameModel.getTimeNum()));
        if(gameModel.getStageNum() == 2)
            dbHelper3.insert(gameModel.getStageNum(), gameModel.getNameUser(), gameModel.getMovesNum(), gameModel.getTimeNum(), gameModel.checkGrade(gameModel.getStageNum(), gameModel.getMovesNum(), gameModel.getTimeNum()));
        grade_img = (ImageView)findViewById(R.id.grade_img);
        bt_next = (ImageButton) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
        if(gameModel.getStageNum() == 2)
            bt_next.setImageResource(R.drawable.end_bt);
        bt_main = (ImageButton)findViewById(R.id.bt_main);
        bt_main.setOnClickListener(this);
        stage_num = (TextView)findViewById(R.id.stage_num);
        moves_num = (TextView)findViewById(R.id.moves_num);
        stage_num.setText("Stage " + (gameModel.getStageNum() + 1) + " Clear !");
        moves_num.setText("" + gameModel.getMovesNum());
        time_num = (TextView)findViewById(R.id.time_num);
        if(gameModel.getTimeNum() < 10)
            time_num.setText("00:0" + gameModel.getTimeNum());
        else if(gameModel.getTimeNum() >= 10 && gameModel.getTimeNum() < 60)
            time_num.setText("00:" + gameModel.getTimeNum());
        else if(gameModel.getTimeNum() == 60)
            time_num.setText("01:00");
        else if(gameModel.getTimeNum() > 60 && gameModel.getTimeNum() < 70)
            time_num.setText("01:0" + gameModel.getTimeNum() % 60);
        else if(gameModel.getTimeNum() >= 70 && gameModel.getTimeNum() < 120)
            time_num.setText("01:" + gameModel.getTimeNum() % 60);
        else if(gameModel.getTimeNum() == 120)
            time_num.setText("02:00");
        grade_char = (TextView)findViewById(R.id.grade_char);
        grade_char.setText(""+gameModel.checkGrade(gameModel.getStageNum(), gameModel.getMovesNum(), gameModel.getTimeNum()));
        switch (gameModel.checkGrade(gameModel.getStageNum(), gameModel.getMovesNum(), gameModel.getTimeNum())) {
            case 'A': grade_img.setImageResource(R.drawable.gradea); break;
            case 'B': grade_img.setImageResource(R.drawable.gradeb); break;
            case 'C': grade_img.setImageResource(R.drawable.gradec); break;
            case 'D': grade_img.setImageResource(R.drawable.graded); break;
            case 'F': grade_img.setImageResource(R.drawable.fail); break;
        }
    }
    /*********************************************************************************************/
    // OnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                if(gameModel.getStageNum() < gameModel.MAX_MAP_STAGE) {
                    System.out.println(gameModel.getStageNum());
                    gameModel.setStageNum(gameModel.getStageNum() + 1);
                    System.out.println(gameModel.getStageNum());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("nameUser", gameModel.getNameUser());
                    intent.putExtra("characterUser", gameModel.getCharacterUser());
                    intent.putExtra("stageNum", gameModel.getStageNum());
                    mContext.startActivity(intent);
                    activity.finish();
                }
                else if(gameModel.getStageNum() == gameModel.MAX_MAP_STAGE) {
                    Intent intent1 = new Intent(mContext, IntroActivity.class);
                    mContext.startActivity(intent1);
                    activity.finish();
                }
                dismiss();
                mediaPlayer.stop();
                break;
            case R.id.bt_main:
                Intent intent1 = new Intent(mContext, IntroActivity.class);
                mContext.startActivity(intent1);
                activity.finish();
                dismiss();
                mediaPlayer.stop();
                break;
        }
    }
    /*********************************************************************************************/
    // ClearDialog 생성자
    public ClearDialog(Context context, GameModel gameModel, Activity activity, MediaPlayer mediaPlayer) {
        super(context);
        this.activity = activity;
        this.gameModel = gameModel;
        this.mediaPlayer = mediaPlayer;
    }
}
