package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//        String url ="https://instagram.ftlv1-1.fna.fbcdn.net/v/t51.2885-15/e35/19932366_111802176122106_559666614132277248_n.jpg?tp=1&_nc_ht=instagram.ftlv1-1.fna.fbcdn.net&_nc_cat=106&_nc_ohc=B_m06H7p03AAX8TBDxQ&ccb=7-4&oh=02c58c14b39f69dc3079329359b61f17&oe=608AEF20&_nc_sid=4efc9f";
// new UriPic.DownloadImageTask(im)
//         .execute(url);

public class UrlPic {
    String url;
    ImageView imageView;

    public UrlPic(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
        new DownloadImageTask(imageView)
                .execute(url);

    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }

}



