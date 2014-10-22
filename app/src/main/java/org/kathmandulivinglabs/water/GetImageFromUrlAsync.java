package org.kathmandulivinglabs.water;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class GetImageFromUrlAsync extends AsyncTask<String, Void, Bitmap> {

    ImageView mImageView;
    String mSize;

    public GetImageFromUrlAsync(ImageView imageView, String size) {
        mImageView = imageView;
        mSize = size;
    }

    @Override
    protected Bitmap doInBackground(String... photo) {
        String photoId = photo[0];
        String url = Utils.FORMHUB_IMAGE_URL + mSize + "?media_file=nirabpudasaini/attachments/" + photoId;
        Bitmap image = null;

        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        mImageView.setImageBitmap(result);
    }
}
