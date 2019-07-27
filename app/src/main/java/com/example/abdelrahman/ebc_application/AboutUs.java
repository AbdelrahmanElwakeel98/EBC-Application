package com.example.abdelrahman.ebc_application;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class AboutUs extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private ArrayList<AboutUsHolder> data = new ArrayList<>();
    private AboutUsHolder aboutUsHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        init();
    }

    private void init(){
        aboutUsHolder = new AboutUsHolder("التعريف الخاص بنا:",
                "هي اول مسابقة من نوعها في تاريخ قسم مدني بكلية الهندسة جامعة الإسكندرية تهدف إلي محاولة تطبيق ما يتم دراستها من خلال تنفيذ \nبعض المنشآت الهندسية علي هيئة نماذج و عمل الإختبارات اللازمة علي مواد الإنشاء وكذلك علي النموذج النهائي وذلك بإشراف \nدكاترة و معيدين بالقسم.");
        data.add(aboutUsHolder);
        aboutUsHolder = new AboutUsHolder("Our doctor supervisor",
                "https://firebasestorage.googleapis.com/v0/b/ebc-application.appspot.com/o/c91.png?alt=media&token=36d25b0b-5bcc-4bce-8586-be4f8a760076");
        data.add(aboutUsHolder);
        aboutUsHolder = new AboutUsHolder("Our teaching assistant supervisor",
                "https://firebasestorage.googleapis.com/v0/b/ebc-application.appspot.com/o/x1.png?alt=media&token=6e40f44d-26fc-4c49-bc25-24ae29141554");
        data.add(aboutUsHolder);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new AboutUsAdapter(data, this));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == data.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


    }

}
