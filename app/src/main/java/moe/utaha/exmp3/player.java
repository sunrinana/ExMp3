package moe.utaha.exmp3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class player extends AppCompatActivity implements View.OnClickListener{
    Button btn_pre,btn_play,btn_next;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    TextView txt_title,txt_time;
    int id,playbackPos = 0;
    boolean isPlay;

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

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_time = (TextView) findViewById(R.id.txt_time);

        id = R.raw.asdf;

        try {
            playMusic();
            Thread();
        } catch (IOException e) {
            e.printStackTrace();
        }

        seekBar = (SeekBar) findViewById(R.id.player_seekbar);
        seekBar.setVisibility(ProgressBar.VISIBLE);
        seekBar.setMax(mediaPlayer.getDuration());
        Log.e("media duration","-----------"+mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
                int m = progress / 60000;
                int s = (progress % 60000) / 1000;
                String strTime = String.format("%02d:%02d", m, s);
                txt_time.setText(strTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_pre:
                break;
            case R.id.btn_play:
                if (isPlay) //while playing media
                {
                    mediaPlayer.pause();
                    playbackPos = mediaPlayer.getCurrentPosition();
                    isPlay = false;
                }else{
                    mediaPlayer.start();
                    mediaPlayer.seekTo(playbackPos);
                    Thread();
                    isPlay = true;
                }
                break;
            case R.id.btn_next:
                break;
        }
    }

    private void playMusic() throws IOException {
        stopMedia();
        isPlay = true;
        mediaPlayer = MediaPlayer.create(getApplicationContext(),id);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        stopMedia();
    }

    private void stopMedia()
    {
        if (mediaPlayer != null)
        {
            try {
                mediaPlayer.release();
                isPlay = false;
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void Thread(){
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                int progress = mediaPlayer.getCurrentPosition();
                int m = progress / 60000;
                int s = (progress % 60000) / 1000;

                String strTime = String.format("%02d:%02d", m, s);
                Log.e("time-----------",strTime+progress);
                txt_time.setText(strTime);
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        };

        Runnable task = new Runnable() {
            public void run() {
                while (mediaPlayer.isPlaying())
                {
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (isPlay)
                    {
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
