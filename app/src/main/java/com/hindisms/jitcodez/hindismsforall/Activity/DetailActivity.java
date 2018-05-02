package com.hindisms.jitcodez.hindismsforall.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hindisms.jitcodez.hindismsforall.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ArrayList<String> sms=new ArrayList<>();
    int position;
    TextView smsText,next,previous;
    Button whatsapp,facebook,hike,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        smsText=(TextView)findViewById(R.id.sms_text);
        next=(TextView)findViewById(R.id.next);
        previous=(TextView)findViewById(R.id.prev);

        whatsapp=(Button)findViewById(R.id.whatsapp);
        facebook=(Button)findViewById(R.id.facebook);
        hike=(Button)findViewById(R.id.hike);
        share=(Button)findViewById(R.id.share);

        sms=getIntent().getStringArrayListExtra("SMS");
        position=getIntent().getIntExtra("Position",0);
        updateUI();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<sms.size()-1)
                position++;
                else
                {
                    position=0;
                }
                updateUI();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>0)
                {
                    position--;
                }
                else
                    position=sms.size()-1;
                updateUI();

            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, sms.get(position));
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailActivity.this,"Whatsapp not installed",Toast.LENGTH_SHORT).show();                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent = new Intent(Intent.ACTION_SEND);
                fbIntent.setType("text/plain");
                fbIntent.setPackage("com.facebook.katana");
                fbIntent.putExtra(Intent.EXTRA_TEXT, sms.get(position));
                try {
                    startActivity(fbIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailActivity.this,"Facebook App not installed",Toast.LENGTH_SHORT).show();                }
            }

        });
        hike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hikeIntent = new Intent(Intent.ACTION_SEND);
                hikeIntent.setType("text/plain");
                hikeIntent.setPackage("com.bsb.hike");
                hikeIntent.putExtra(Intent.EXTRA_TEXT, sms.get(position));
                try {
                    startActivity(hikeIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailActivity.this,"Hike not installed",Toast.LENGTH_SHORT).show();                }
            }

        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sms.get(position));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    private void updateUI() {

        smsText.setText(sms.get(position));

    }
}
