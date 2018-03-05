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
import android.widget.TextView;

public class FailDialog2 extends Dialog implements OnClickListener {
    /*********************************************************************************************/
    // 멤버 변수
    GameModel2 gameModel = new GameModel2();
    Activity activity;
    MediaPlayer mediaPlayer;
    Context mContext = getContext();
    TextView stage_num, moves_num, time_num;
    ImageButton bt_re, bt_main;
    /*********************************************************************************************/
    // onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_fail2);
        setCanceledOnTouchOutside(false);
        bt_re = (ImageButton)findViewById(R.id.bt_re2);
        bt_main = (ImageButton)findViewById(R.id.bt_main12);
        bt_main.setOnClickListener(this);
        bt_re.setOnClickListener(this);
        stage_num = (TextView)findViewById(R.id.stage_num12);
        moves_num = (TextView)findViewById(R.id.moves_num12);
        stage_num.setText("Stage " + (gameModel.getStageNum() + 1) + " Fail !");
        moves_num.setText("" + gameModel.getMovesNum());
        time_num = (TextView)findViewById(R.id.time_num12);
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
    }
    /*********************************************************************************************/
    // OnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_re2:
                activity.recreate();
                dismiss();
                mediaPlayer.stop();
                break;
            case R.id.bt_main12:
                Intent intent1 = new Intent(mContext, IntroActivity.class);
                mContext.startActivity(intent1);
                activity.finish();
                dismiss();
                mediaPlayer.stop();
                break;
        }
    }
    /*********************************************************************************************/
    // FailDialog2 생성자
    public FailDialog2(Context context, GameModel2 gameModel, Activity activity, MediaPlayer mediaPlayer) {
        super(context);
        this.gameModel = gameModel;
        this.activity = activity;
        this.mediaPlayer = mediaPlayer;
    }
}