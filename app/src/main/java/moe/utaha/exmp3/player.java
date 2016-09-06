package moe.utaha.exmp3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class player extends AppCompatActivity implements View.OnClickListener{
    Button btn_pre,btn_play,btn_next;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_pre = (Button) findViewById(R.id.btn_pre);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_next = (Button) findViewById(R.id.btn_next);

        btn_pre.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_play.setOnClickListener(this);

        id = R.raw.asdf;
        mediaPlayer = MediaPlayer.create(this,id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_pre:
                break;
            case R.id.btn_play:
                break;
            case R.id.btn_next:
                break;
        }
    }
}
