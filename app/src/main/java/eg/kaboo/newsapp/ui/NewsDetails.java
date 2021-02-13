package eg.kaboo.newsapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import eg.kaboo.newsapp.R;
import eg.kaboo.newsapp.databinding.ActivityNewsDetailsBinding;

public class NewsDetails extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    ActivityNewsDetailsBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        intent = getIntent();

        b.articleTxt.setText(intent.getStringExtra("article"));
        setImage();
        b.gotoNewsTxt.setOnClickListener(this);
    }

    private void setImage() {
        Glide
                .with(this)
                .load(intent.getStringExtra("imageUrl"))
                .placeholder(R.drawable.loading)
                .into(b.newsImageView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.gotoNewsTxt) {
            intent();
        }
    }

    private void intent() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.setData(Uri.parse(intent.getStringExtra("url")));
        startActivity(intent1);
    }

}