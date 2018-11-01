package kr.appfactory.billiardya;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MoviePlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String mApiKey = "AIzaSyBGR1bYVMxCyHqOry0UKSs4K2PtA4VA8D8";
    YouTubePlayerView youtubeView;

    YouTubePlayer mPlayer;
    public String videoId;
    Toolbar myToolbar;

    public String subject;

    YouTubePlayer.OnInitializedListener listener;


    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_play);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        myToolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView title = findViewById(R.id.toolbar_title);
        TextView desc = findViewById(R.id.movie_desc);

        Intent intent = getIntent();
        subject = intent.getStringExtra("title");
        videoId = intent.getStringExtra("videoId");
        String videodesc = intent.getStringExtra("videodesc");
        title.setText(subject);
        desc.setText(videodesc);


        //Toast.makeText (getApplicationContext(), "클릭" + videoId , Toast.LENGTH_LONG).show();


        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        youtubeView.initialize(mApiKey, this);

        //youtubeView.initialize(mApiKey, listener);

        ImageView btn_go_back = (ImageView) findViewById(R.id.back_button);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Go Back", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Button favoritesButton = (Button) findViewById(R.id.favoritesButton);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "즐겨찾기에 등록되었습니다~", Toast.LENGTH_LONG).show();
            }
        });
        Button shereButton = (Button) findViewById(R.id.shereButton);
        shereButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String EXTRA_TEXT ="https://www.youtube.com/watch?v="+videoId;
                 EXTRA_TEXT +="\n\n" +
                         "언제나 함께하는 당구강좌영상 설치\n" +
                         "https://play.google.com/store/apps/details?id=kr.co.pointn.billiardya";

                //Toast.makeText(getApplicationContext(), "Go Back", Toast.LENGTH_LONG).show();
                Intent msg = new Intent (Intent.ACTION_SEND);
                msg.addCategory(Intent.CATEGORY_DEFAULT);
                msg.putExtra(Intent.EXTRA_SUBJECT, subject);
                msg.setType("text/plain");

                msg.putExtra(Intent.EXTRA_TEXT,EXTRA_TEXT);
                startActivity(Intent.createChooser(msg, "공유하기"));

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


        //mPlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
        // 비디오 아이디
        Intent intent = getIntent();
        String videoId = intent.getStringExtra("videoId");
        enableDisableView(youtubeView, true);
        mPlayer = youTubePlayer;
        mPlayer.loadVideo(""+ videoId);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            mPlayer.release();
        }
    }
}
