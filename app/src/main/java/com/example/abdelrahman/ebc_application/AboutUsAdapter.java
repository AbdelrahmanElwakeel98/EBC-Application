package com.example.abdelrahman.ebc_application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AboutUsAdapter extends PagerAdapter {

    private ArrayList<AboutUsHolder> data;
    private Context context;
    private LayoutInflater inflater;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View textAboutUs = null;

        switch (position) {
            case 0:
                textAboutUs = inflater.inflate(R.layout.slide_about_us, container, false);
                TextView text1 = (TextView) textAboutUs
                        .findViewById(R.id.txt1_slide);

                TextView text2 = (TextView) textAboutUs
                        .findViewById(R.id.txt2_slide);

                text1.setText(data.get(position).getHeadline());
                text2.setText(data.get(position).getBody());

                container.addView(textAboutUs, 0);
                break;
            case 1:
                textAboutUs = inflater.inflate(R.layout.slide_supervisors, container, false);
                TextView text = (TextView) textAboutUs
                        .findViewById(R.id.txt1_supervisor);

                ImageView imageView = textAboutUs.findViewById(R.id.img_supervisor);

                text.setText(data.get(position).getHeadline());
                Glide.with(imageView.getContext())
                        .load(data.get(position).getBody())
                        .into(imageView);

                container.addView(textAboutUs, 0);
                break;
            case 2:
                textAboutUs = inflater.inflate(R.layout.slide_supervisors, container, false);
                TextView txt = (TextView) textAboutUs
                        .findViewById(R.id.txt1_supervisor);

                ImageView img = textAboutUs.findViewById(R.id.img_supervisor);

                txt.setText(data.get(position).getHeadline());
                Glide.with(img.getContext())
                        .load(data.get(position).getBody())
                        .into(img);

                container.addView(textAboutUs, 0);
                break;

        }



        return textAboutUs;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public AboutUsAdapter(ArrayList<AboutUsHolder> data, Context context){
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }
}
