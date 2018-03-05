package kr.ac.skuniv.pushpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HowActivity extends Activity {
    /*********************************************************************************************/
    // 멤버 변수
    ImageButton gomenu_button;
    /*********************************************************************************************/
    // onCreate()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplay);
        gomenu_button = (ImageButton)findViewById(R.id.gomenu);
        /*********************************************************************************************/
        // OnClickListener : gomenu_button
        gomenu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HowActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
