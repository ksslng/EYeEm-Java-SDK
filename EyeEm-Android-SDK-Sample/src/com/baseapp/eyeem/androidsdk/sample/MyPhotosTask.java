package com.baseapp.eyeem.androidsdk.sample;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;


import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.baseapp.eyeem.androidsdk.errors.EyeemException;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.query.EyeemUsersQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemUsersQuery.EESpecificUsersEndpoint;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class MyPhotosTask extends AsyncTask<Void, Void, HashMap<String, Object>> {
	EyeEmAPI api;
	ImageView view;
	
	public MyPhotosTask (EyeEmAPI api, ImageView view){
		this.api = api;
		this.view = view;
	}
	@Override
	protected HashMap<String, Object> doInBackground(Void...params ) {
		
		try {

	        EyeemUsersQuery query = new EyeemUsersQuery();
	        query.firstIdString = "me";
	        query.specificEndpoint = EESpecificUsersEndpoint.EEspecificUsersEndpointPhotos;
			HashMap<String, Object> map = (HashMap<String, Object>) api.getRequest(query.transformQuery());
			return map;

		} catch (EyeemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    protected void onPostExecute(HashMap<String, Object> map) {
    	Log.d("myPhotosTals", "finished");

    	
		@SuppressWarnings("unchecked")
		ArrayList<EyeemPhoto> photos = ((ArrayList<EyeemPhoto>) map.get("photos"));

        DownloadPhotoTask task = new DownloadPhotoTask(view);
        try {
			task.execute(new URL(photos.get(0).photoUrl));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


      
    }
    
}
