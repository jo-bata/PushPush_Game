package kr.ac.skuniv.pushpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RankingActivity extends Activity {
    /*********************************************************************************************/
    // 멤버 변수
    ImageButton gomenu_bt;
    int stage_num = 0;
    ImageButton left_bt, right_bt;
    TextView stage;
    TextView nickname1, nickname2, nickname3, nickname4, nickname5, nickname6, nickname7, nickname8, nickname9;
    TextView moves1, moves2, moves3, moves4, moves5, moves6, moves7, moves8, moves9;
    TextView time1, time2, time3, time4, time5, time6, time7, time8, time9;
    TextView grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8, grade9;
    /*********************************************************************************************/
    // onCreate()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "RANKING.db", null, 1);
        final DBHelper dbHelper2 = new DBHelper(getApplicationContext(), "RANKING2.db", null, 1);
        final DBHelper dbHelper3 = new DBHelper(getApplicationContext(), "RANKING3.db", null, 1);
        stage = (TextView)findViewById(R.id.stage);
        nickname1 = (TextView)findViewById(R.id.nickname1); nickname2 = (TextView)findViewById(R.id.nickname2); nickname3 = (TextView)findViewById(R.id.nickname3);
        nickname4 = (TextView)findViewById(R.id.nickname4); nickname5 = (TextView)findViewById(R.id.nickname5); nickname6 = (TextView)findViewById(R.id.nickname6);
        nickname7 = (TextView)findViewById(R.id.nickname7); nickname8 = (TextView)findViewById(R.id.nickname8); nickname9 = (TextView)findViewById(R.id.nickname9);
        moves1 = (TextView)findViewById(R.id.moves1); moves2 = (TextView)findViewById(R.id.moves2); moves3 = (TextView)findViewById(R.id.moves3);
        moves4 = (TextView)findViewById(R.id.moves4); moves5 = (TextView)findViewById(R.id.moves5); moves6 = (TextView)findViewById(R.id.moves6);
        moves7 = (TextView)findViewById(R.id.moves7); moves8 = (TextView)findViewById(R.id.moves8); moves9 = (TextView)findViewById(R.id.moves9);
        time1 = (TextView)findViewById(R.id.time1); time2 = (TextView)findViewById(R.id.time2); time3 = (TextView)findViewById(R.id.time3);
        time4 = (TextView)findViewById(R.id.time4); time5 = (TextView)findViewById(R.id.time5); time6 = (TextView)findViewById(R.id.time6);
        time7 = (TextView)findViewById(R.id.time7); time8 = (TextView)findViewById(R.id.time8); time9 = (TextView)findViewById(R.id.time9);
        grade1 = (TextView)findViewById(R.id.grade1); grade2 = (TextView)findViewById(R.id.grade2); grade3 = (TextView)findViewById(R.id.grade3);
        grade4 = (TextView)findViewById(R.id.grade4); grade5 = (TextView)findViewById(R.id.grade5); grade6 = (TextView)findViewById(R.id.grade6);
        grade7 = (TextView)findViewById(R.id.grade7); grade8 = (TextView)findViewById(R.id.grade8); grade9 = (TextView)findViewById(R.id.grade9);
        nickname1.setText(dbHelper.getNickname(0, 1)); moves1.setText(dbHelper.getMoves(0, 1)); time1.setText(dbHelper.getTime(0, 1)); grade1.setText(dbHelper.getGrade(0, 1));
        nickname2.setText(dbHelper.getNickname(0, 2)); moves2.setText(dbHelper.getMoves(0, 2)); time2.setText(dbHelper.getTime(0, 2)); grade2.setText(dbHelper.getGrade(0, 2));
        nickname3.setText(dbHelper.getNickname(0, 3)); moves3.setText(dbHelper.getMoves(0, 3)); time3.setText(dbHelper.getTime(0, 3)); grade3.setText(dbHelper.getGrade(0, 3));
        nickname4.setText(dbHelper.getNickname(0, 4)); moves4.setText(dbHelper.getMoves(0, 4)); time4.setText(dbHelper.getTime(0, 4)); grade4.setText(dbHelper.getGrade(0, 4));
        nickname5.setText(dbHelper.getNickname(0, 5)); moves5.setText(dbHelper.getMoves(0, 5)); time5.setText(dbHelper.getTime(0, 5)); grade5.setText(dbHelper.getGrade(0, 5));
        nickname6.setText(dbHelper.getNickname(0, 6)); moves6.setText(dbHelper.getMoves(0, 6)); time6.setText(dbHelper.getTime(0, 6)); grade6.setText(dbHelper.getGrade(0, 6));
        nickname7.setText(dbHelper.getNickname(0, 7)); moves7.setText(dbHelper.getMoves(0, 7)); time7.setText(dbHelper.getTime(0, 7)); grade7.setText(dbHelper.getGrade(0, 7));
        nickname8.setText(dbHelper.getNickname(0, 8)); moves8.setText(dbHelper.getMoves(0, 8)); time8.setText(dbHelper.getTime(0, 8)); grade8.setText(dbHelper.getGrade(0, 8));
        nickname9.setText(dbHelper.getNickname(0, 9)); moves9.setText(dbHelper.getMoves(0, 9)); time9.setText(dbHelper.getTime(0, 9)); grade9.setText(dbHelper.getGrade(0, 9));
        left_bt = (ImageButton)findViewById(R.id.left_bt);
        /*********************************************************************************************/
        // OnClickListener : left_bt
        left_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stage_num == 0)
                    stage_num = 2;
                else if(stage_num == 1)
                    stage_num = 0;
                else if(stage_num == 2)
                    stage_num = 1;
                stage.setText("STAGE " + (stage_num + 1));
                if(stage_num == 0) {
                    nickname1.setText(dbHelper.getNickname(0, 1)); moves1.setText(dbHelper.getMoves(0, 1)); time1.setText(dbHelper.getTime(0, 1)); grade1.setText(dbHelper.getGrade(0, 1));
                    nickname2.setText(dbHelper.getNickname(0, 2)); moves2.setText(dbHelper.getMoves(0, 2)); time2.setText(dbHelper.getTime(0, 2)); grade2.setText(dbHelper.getGrade(0, 2));
                    nickname3.setText(dbHelper.getNickname(0, 3)); moves3.setText(dbHelper.getMoves(0, 3)); time3.setText(dbHelper.getTime(0, 3)); grade3.setText(dbHelper.getGrade(0, 3));
                    nickname4.setText(dbHelper.getNickname(0, 4)); moves4.setText(dbHelper.getMoves(0, 4)); time4.setText(dbHelper.getTime(0, 4)); grade4.setText(dbHelper.getGrade(0, 4));
                    nickname5.setText(dbHelper.getNickname(0, 5)); moves5.setText(dbHelper.getMoves(0, 5)); time5.setText(dbHelper.getTime(0, 5)); grade5.setText(dbHelper.getGrade(0, 5));
                    nickname6.setText(dbHelper.getNickname(0, 6)); moves6.setText(dbHelper.getMoves(0, 6)); time6.setText(dbHelper.getTime(0, 6)); grade6.setText(dbHelper.getGrade(0, 6));
                    nickname7.setText(dbHelper.getNickname(0, 7)); moves7.setText(dbHelper.getMoves(0, 7)); time7.setText(dbHelper.getTime(0, 7)); grade7.setText(dbHelper.getGrade(0, 7));
                    nickname8.setText(dbHelper.getNickname(0, 8)); moves8.setText(dbHelper.getMoves(0, 8)); time8.setText(dbHelper.getTime(0, 8)); grade8.setText(dbHelper.getGrade(0, 8));
                    nickname9.setText(dbHelper.getNickname(0, 9)); moves9.setText(dbHelper.getMoves(0, 9)); time9.setText(dbHelper.getTime(0, 9)); grade9.setText(dbHelper.getGrade(0, 9));
                }
                else if(stage_num == 1) {
                    nickname1.setText(dbHelper2.getNickname(1, 1)); moves1.setText(dbHelper2.getMoves(1, 1)); time1.setText(dbHelper2.getTime(1, 1)); grade1.setText(dbHelper2.getGrade(1, 1));
                    nickname2.setText(dbHelper2.getNickname(1, 2)); moves2.setText(dbHelper2.getMoves(1, 2)); time2.setText(dbHelper2.getTime(1, 2)); grade2.setText(dbHelper2.getGrade(1, 2));
                    nickname3.setText(dbHelper2.getNickname(1, 3)); moves3.setText(dbHelper2.getMoves(1, 3)); time3.setText(dbHelper2.getTime(1, 3)); grade3.setText(dbHelper2.getGrade(1, 3));
                    nickname4.setText(dbHelper2.getNickname(1, 4)); moves4.setText(dbHelper2.getMoves(1, 4)); time4.setText(dbHelper2.getTime(1, 4)); grade4.setText(dbHelper2.getGrade(1, 4));
                    nickname5.setText(dbHelper2.getNickname(1, 5)); moves5.setText(dbHelper2.getMoves(1, 5)); time5.setText(dbHelper2.getTime(1, 5)); grade5.setText(dbHelper2.getGrade(1, 5));
                    nickname6.setText(dbHelper2.getNickname(1, 6)); moves6.setText(dbHelper2.getMoves(1, 6)); time6.setText(dbHelper2.getTime(1, 6)); grade6.setText(dbHelper2.getGrade(1, 6));
                    nickname7.setText(dbHelper2.getNickname(1, 7)); moves7.setText(dbHelper2.getMoves(1, 7)); time7.setText(dbHelper2.getTime(1, 7)); grade7.setText(dbHelper2.getGrade(1, 7));
                    nickname8.setText(dbHelper2.getNickname(1, 8)); moves8.setText(dbHelper2.getMoves(1, 8)); time8.setText(dbHelper2.getTime(1, 8)); grade8.setText(dbHelper2.getGrade(1, 8));
                    nickname9.setText(dbHelper2.getNickname(1, 9)); moves9.setText(dbHelper2.getMoves(1, 9)); time9.setText(dbHelper2.getTime(1, 9)); grade9.setText(dbHelper2.getGrade(1, 9));
                }
                else if(stage_num == 2) {
                    nickname1.setText(dbHelper3.getNickname(2, 1)); moves1.setText(dbHelper3.getMoves(2, 1)); time1.setText(dbHelper3.getTime(2, 1)); grade1.setText(dbHelper3.getGrade(2, 1));
                    nickname2.setText(dbHelper3.getNickname(2, 2)); moves2.setText(dbHelper3.getMoves(2, 2)); time2.setText(dbHelper3.getTime(2, 2)); grade2.setText(dbHelper3.getGrade(2, 2));
                    nickname3.setText(dbHelper3.getNickname(2, 3)); moves3.setText(dbHelper3.getMoves(2, 3)); time3.setText(dbHelper3.getTime(2, 3)); grade3.setText(dbHelper3.getGrade(2, 3));
                    nickname4.setText(dbHelper3.getNickname(2, 4)); moves4.setText(dbHelper3.getMoves(2, 4)); time4.setText(dbHelper3.getTime(2, 4)); grade4.setText(dbHelper3.getGrade(2, 4));
                    nickname5.setText(dbHelper3.getNickname(2, 5)); moves5.setText(dbHelper3.getMoves(2, 5)); time5.setText(dbHelper3.getTime(2, 5)); grade5.setText(dbHelper3.getGrade(2, 5));
                    nickname6.setText(dbHelper3.getNickname(2, 6)); moves6.setText(dbHelper3.getMoves(2, 6)); time6.setText(dbHelper3.getTime(2, 6)); grade6.setText(dbHelper3.getGrade(2, 6));
                    nickname7.setText(dbHelper3.getNickname(2, 7)); moves7.setText(dbHelper3.getMoves(2, 7)); time7.setText(dbHelper3.getTime(2, 7)); grade7.setText(dbHelper3.getGrade(2, 7));
                    nickname8.setText(dbHelper3.getNickname(2, 8)); moves8.setText(dbHelper3.getMoves(2, 8)); time8.setText(dbHelper3.getTime(2, 8)); grade8.setText(dbHelper3.getGrade(2, 8));
                    nickname9.setText(dbHelper3.getNickname(2, 9)); moves9.setText(dbHelper3.getMoves(2, 9)); time9.setText(dbHelper3.getTime(2, 9)); grade9.setText(dbHelper3.getGrade(2, 9));
                }
            }
        });
        right_bt = (ImageButton)findViewById(R.id.right_bt);
        /*********************************************************************************************/
        // OnClickListener : right_bt
        right_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stage_num == 0)
                    stage_num = 1;
                else if(stage_num == 1)
                    stage_num = 2;
                else if(stage_num == 2)
                    stage_num = 0;
                stage.setText("STAGE " + (stage_num + 1));
                if(stage_num == 0) {
                    nickname1.setText(dbHelper.getNickname(0, 1)); moves1.setText(dbHelper.getMoves(0, 1)); time1.setText(dbHelper.getTime(0, 1)); grade1.setText(dbHelper.getGrade(0, 1));
                    nickname2.setText(dbHelper.getNickname(0, 2)); moves2.setText(dbHelper.getMoves(0, 2)); time2.setText(dbHelper.getTime(0, 2)); grade2.setText(dbHelper.getGrade(0, 2));
                    nickname3.setText(dbHelper.getNickname(0, 3)); moves3.setText(dbHelper.getMoves(0, 3)); time3.setText(dbHelper.getTime(0, 3)); grade3.setText(dbHelper.getGrade(0, 3));
                    nickname4.setText(dbHelper.getNickname(0, 4)); moves4.setText(dbHelper.getMoves(0, 4)); time4.setText(dbHelper.getTime(0, 4)); grade4.setText(dbHelper.getGrade(0, 4));
                    nickname5.setText(dbHelper.getNickname(0, 5)); moves5.setText(dbHelper.getMoves(0, 5)); time5.setText(dbHelper.getTime(0, 5)); grade5.setText(dbHelper.getGrade(0, 5));
                    nickname6.setText(dbHelper.getNickname(0, 6)); moves6.setText(dbHelper.getMoves(0, 6)); time6.setText(dbHelper.getTime(0, 6)); grade6.setText(dbHelper.getGrade(0, 6));
                    nickname7.setText(dbHelper.getNickname(0, 7)); moves7.setText(dbHelper.getMoves(0, 7)); time7.setText(dbHelper.getTime(0, 7)); grade7.setText(dbHelper.getGrade(0, 7));
                    nickname8.setText(dbHelper.getNickname(0, 8)); moves8.setText(dbHelper.getMoves(0, 8)); time8.setText(dbHelper.getTime(0, 8)); grade8.setText(dbHelper.getGrade(0, 8));
                    nickname9.setText(dbHelper.getNickname(0, 9)); moves9.setText(dbHelper.getMoves(0, 9)); time9.setText(dbHelper.getTime(0, 9)); grade9.setText(dbHelper.getGrade(0, 9));
                }
                else if(stage_num == 1) {
                    nickname1.setText(dbHelper2.getNickname(1, 1)); moves1.setText(dbHelper2.getMoves(1, 1)); time1.setText(dbHelper2.getTime(1, 1)); grade1.setText(dbHelper2.getGrade(1, 1));
                    nickname2.setText(dbHelper2.getNickname(1, 2)); moves2.setText(dbHelper2.getMoves(1, 2)); time2.setText(dbHelper2.getTime(1, 2)); grade2.setText(dbHelper2.getGrade(1, 2));
                    nickname3.setText(dbHelper2.getNickname(1, 3)); moves3.setText(dbHelper2.getMoves(1, 3)); time3.setText(dbHelper2.getTime(1, 3)); grade3.setText(dbHelper2.getGrade(1, 3));
                    nickname4.setText(dbHelper2.getNickname(1, 4)); moves4.setText(dbHelper2.getMoves(1, 4)); time4.setText(dbHelper2.getTime(1, 4)); grade4.setText(dbHelper2.getGrade(1, 4));
                    nickname5.setText(dbHelper2.getNickname(1, 5)); moves5.setText(dbHelper2.getMoves(1, 5)); time5.setText(dbHelper2.getTime(1, 5)); grade5.setText(dbHelper2.getGrade(1, 5));
                    nickname6.setText(dbHelper2.getNickname(1, 6)); moves6.setText(dbHelper2.getMoves(1, 6)); time6.setText(dbHelper2.getTime(1, 6)); grade6.setText(dbHelper2.getGrade(1, 6));
                    nickname7.setText(dbHelper2.getNickname(1, 7)); moves7.setText(dbHelper2.getMoves(1, 7)); time7.setText(dbHelper2.getTime(1, 7)); grade7.setText(dbHelper2.getGrade(1, 7));
                    nickname8.setText(dbHelper2.getNickname(1, 8)); moves8.setText(dbHelper2.getMoves(1, 8)); time8.setText(dbHelper2.getTime(1, 8)); grade8.setText(dbHelper2.getGrade(1, 8));
                    nickname9.setText(dbHelper2.getNickname(1, 9)); moves9.setText(dbHelper2.getMoves(1, 9)); time9.setText(dbHelper2.getTime(1, 9)); grade9.setText(dbHelper2.getGrade(1, 9));
                }
                else if(stage_num == 2) {
                    nickname1.setText(dbHelper3.getNickname(2, 1)); moves1.setText(dbHelper3.getMoves(2, 1)); time1.setText(dbHelper3.getTime(2, 1)); grade1.setText(dbHelper3.getGrade(2, 1));
                    nickname2.setText(dbHelper3.getNickname(2, 2)); moves2.setText(dbHelper3.getMoves(2, 2)); time2.setText(dbHelper3.getTime(2, 2)); grade2.setText(dbHelper3.getGrade(2, 2));
                    nickname3.setText(dbHelper3.getNickname(2, 3)); moves3.setText(dbHelper3.getMoves(2, 3)); time3.setText(dbHelper3.getTime(2, 3)); grade3.setText(dbHelper3.getGrade(2, 3));
                    nickname4.setText(dbHelper3.getNickname(2, 4)); moves4.setText(dbHelper3.getMoves(2, 4)); time4.setText(dbHelper3.getTime(2, 4)); grade4.setText(dbHelper3.getGrade(2, 4));
                    nickname5.setText(dbHelper3.getNickname(2, 5)); moves5.setText(dbHelper3.getMoves(2, 5)); time5.setText(dbHelper3.getTime(2, 5)); grade5.setText(dbHelper3.getGrade(2, 5));
                    nickname6.setText(dbHelper3.getNickname(2, 6)); moves6.setText(dbHelper3.getMoves(2, 6)); time6.setText(dbHelper3.getTime(2, 6)); grade6.setText(dbHelper3.getGrade(2, 6));
                    nickname7.setText(dbHelper3.getNickname(2, 7)); moves7.setText(dbHelper3.getMoves(2, 7)); time7.setText(dbHelper3.getTime(2, 7)); grade7.setText(dbHelper3.getGrade(2, 7));
                    nickname8.setText(dbHelper3.getNickname(2, 8)); moves8.setText(dbHelper3.getMoves(2, 8)); time8.setText(dbHelper3.getTime(2, 8)); grade8.setText(dbHelper3.getGrade(2, 8));
                    nickname9.setText(dbHelper3.getNickname(2, 9)); moves9.setText(dbHelper3.getMoves(2, 9)); time9.setText(dbHelper3.getTime(2, 9)); grade9.setText(dbHelper3.getGrade(2, 9));
                }
            }
        });
        gomenu_bt = (ImageButton)findViewById(R.id.gomenu_bt);
        /*********************************************************************************************/
        // OnClickListener : gomenu_bt
        gomenu_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
