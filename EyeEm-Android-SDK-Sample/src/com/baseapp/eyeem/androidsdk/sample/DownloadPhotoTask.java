package com.baseapp.eyeem.androidsdk.sample;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadPhotoTask extends AsyncTask<URL, Void, Bitmap> {
	ImageView view;
	public DownloadPhotoTask(ImageView view){
		this.view = view;
	}
	
	@Override
	protected Bitmap doInBackground(URL... urls) {
		// TODO Auto-generated method stub
		
	    try {
	    	HttpURLConnection connection = (HttpURLConnection) urls[0].openConnection();
	    	connection.setDoInput(true);

			connection.connect();

			InputStream input;

			input = connection.getInputStream();
		    Bitmap bitmap = BitmapFactory.decodeStream(input);
			return bitmap;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}

	
    protected void onPostExecute(Bitmap bitmap) {
        
        view.setImageBitmap(bitmap);
    }
}
