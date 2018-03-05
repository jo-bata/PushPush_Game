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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SelectDialog extends Dialog implements OnClickListener {
    /*********************************************************************************************/
    // 멤버 변수
    GameModel gameModel;
    GameModel2 gameModel2;
    Context mContext = getContext();
    Activity activity;
    ImageView character_img;
    ImageButton bt_start, bt_start2, bt_left, bt_right;
    EditText edit_name;
    MediaPlayer mediaPlayer;
    /*********************************************************************************************/
    // onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select);
        setCanceledOnTouchOutside(false);
        character_img = (ImageView)findViewById(R.id.character_img);
        edit_name = (EditText)findViewById(R.id.dialog_edit);
        bt_left = (ImageButton)findViewById(R.id.bt_left);
        bt_right = (ImageButton)findViewById(R.id.bt_right);
        bt_start = (ImageButton)findViewById(R.id.bt_start);
        bt_start2 = (ImageButton)findViewById(R.id.bt_start2);
        bt_left.setOnClickListener(this);
        bt_right.setOnClickListener(this);
        bt_start.setOnClickListener(this);
        bt_start2.setOnClickListener(this);
    }
    /*********************************************************************************************/
    // OnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_left:
                switch (gameModel.getCharacterUser()) {
                    case 0:
                        character_img.setImageResource(R.drawable.character3);
                        gameModel.setCharacterUser(2);
                        break;
                    case 1:
                        character_img.setImageResource(R.drawable.character1);
                        gameModel.setCharacterUser(0);
                        break;
                    case 2:
                        character_img.setImageResource(R.drawable.character2);
                        gameModel.setCharacterUser(1);
                        break;
                }
                character_img.invalidate();
                break;
            case R.id.bt_right:
                switch (gameModel.getCharacterUser()) {
                    case 0:
                        character_img.setImageResource(R.drawable.character2);
                        gameModel.setCharacterUser(1);
                        break;
                    case 1:
                        character_img.setImageResource(R.drawable.character3);
                        gameModel.setCharacterUser(2);
                        break;
                    case 2:
                        character_img.setImageResource(R.drawable.character1);
                        gameModel.setCharacterUser(0);
                        break;
                }
                character_img.invalidate();
                break;
            case R.id.bt_start:
                gameModel.setNameUser(edit_name.getText().toString());
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("nameUser", gameModel.getNameUser());
                intent.putExtra("characterUser", gameModel.getCharacterUser());
                mContext.startActivity(intent);
                mediaPlayer.stop();
                activity.finish();
                dismiss();
                break;
            case R.id.bt_start2:
                gameModel2.setNameUser(edit_name.getText().toString());
                Intent intent1 = new Intent(mContext, MainActivity2.class);
                intent1.putExtra("nameUser", gameModel2.getNameUser());
                intent1.putExtra("characterUser", gameModel2.getCharacterUser());
                mContext.startActivity(intent1);
                mediaPlayer.stop();
                activity.finish();
                dismiss();
                break;
        }
    }
    /*********************************************************************************************/
    // SelectDialog 생성자
    public SelectDialog(Context context, GameModel gameModel, GameModel2 gameModel2, MediaPlayer mediaPlayer, Activity activity) {
        super(context);
        this.gameModel = gameModel;
        this.gameModel2 = gameModel2;
        this.mediaPlayer = mediaPlayer;
        this.activity = activity;
    }
}


