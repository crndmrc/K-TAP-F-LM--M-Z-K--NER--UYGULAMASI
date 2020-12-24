package com.mobiluygulama.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager pager;

    private Button nextBtn;
    private Button backBtn;

    private int fragmentCount;
    private int currentInd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());

        nextBtn = findViewById(R.id.buttonNext);
        backBtn = findViewById(R.id.buttonBack);
        pager = findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new IntroCarouselAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);


        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("onPageScrolled", String.valueOf(pager.getCurrentItem()));

                currentInd = position;
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("SONRAKİ");
                backBtn.setText("ÖNCEKİ");

                fragmentCount =  pagerAdapter.getCount();

                if (position == 0) {
                    nextBtn.setEnabled(true);
                    backBtn.setEnabled(false);
                    backBtn.setVisibility(View.INVISIBLE);
                } else if (position == fragmentCount - 1) {

                    nextBtn.setText("BİTİR");
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("onPageSelected", String.valueOf(pager.getCurrentItem()));


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("onPageScrollState", String.valueOf(pager.getCurrentItem()));


            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(currentInd - 1);

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(currentInd + 1);

                if(currentInd == fragmentCount - 1){
                    Intent intent;
                    intent = new Intent(getApplicationContext(), HosgeldinActivity.class);
                    startActivity(intent);
                    finish(); // önceki ekranı kapatmak için eğer kapatılmazsa stack'ta kalır ve her geri gidildiğinde gözükür
                }
            }
        });

    }
}