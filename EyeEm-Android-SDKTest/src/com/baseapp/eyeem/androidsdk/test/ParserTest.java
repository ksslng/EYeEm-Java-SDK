package com.baseapp.eyeem.androidsdk.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.baseapp.eyeem.androidsdk.models.EyeemAlbum;
import com.baseapp.eyeem.androidsdk.models.EyeemComment;
import com.baseapp.eyeem.androidsdk.models.EyeemNewsItem;
import com.baseapp.eyeem.androidsdk.models.EyeemNewsPagination;
import com.baseapp.eyeem.androidsdk.models.EyeemPhoto;
import com.baseapp.eyeem.androidsdk.models.EyeemUser;


public class ParserTest extends EyeEmAbstractTest {

	protected void setUp() throws Exception {
		super.setUp();
		
	}
	public void testAlbums(){
		String file = "res/raw/albums.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	     int i;
	  try {
	   i = in.read();
	   while (i != -1)
	      {
	       byteArrayOutputStream.write(i);
	       i = in.read();
	      }
	   in.close();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  try {
		HashMap<String,Object> response =  api.sortResponse(new JSONObject( byteArrayOutputStream.toString()));
		@SuppressWarnings("unchecked")
		ArrayList<EyeemAlbum> albums =  (ArrayList<EyeemAlbum>) response.get("albums");
		EyeemAlbum album = albums.get(20);
		assertNotNull(album.albumId);
		assertNotNull(album.name);
		assertNotNull(album.type);
		assertNotNull(album.thumbUrl);
		assertNotNull(album.updated);
		assertNotNull(Integer.valueOf(album.totalPhotos));
		assertNotNull(Integer.valueOf(album.totalContributors));

	  } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	
	}
	public void testUsers(){
		String file = "res/raw/users.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	     int i;
	  try {
	   i = in.read();
	   while (i != -1)
	      {
	       byteArrayOutputStream.write(i);
	       i = in.read();
	      }
	   in.close();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  try {
		HashMap<String,Object> response =  api.sortResponse(new JSONObject( byteArrayOutputStream.toString()));
		@SuppressWarnings("unchecked")
		ArrayList<EyeemUser> users =  (ArrayList<EyeemUser>) response.get("users");
		EyeemUser user = users.get(0);

		assertEquals(4446, user.userId);
		assertEquals("http://www.eyeem.com/thumb/sq/50/74c1b3246e8c0f6a14935934e2674c814d431667.jpg", user.thumbUrl);
		assertEquals("team", user.nickname);
		assertEquals("EyeEm Team", user.fullname);
		assertEquals("http://www.eyeem.com/thumb/sq/200/74c1b3246e8c0f6a14935934e2674c814d431667.jpg", user.photoUrl);
		assertEquals(47, user.totalPhotos);
		assertEquals(8290, user.totalFollowers);		
		assertEquals(14, user.totalFriends);
		assertEquals("The official EyeEm Team account. Follow us for a behind the scenes view at EyeEm Studio from Berlin with <3", user.description);
		
		
	  } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}
	public void testPhoto(){
		String file = "res/raw/photo.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	     int i;
	  try {
	   i = in.read();
	   while (i != -1)
	      {
	       byteArrayOutputStream.write(i);
	       i = in.read();
	      }
	   in.close();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  try {
		HashMap<String,Object> response =  api.sortResponse(new JSONObject( byteArrayOutputStream.toString()));
		EyeemPhoto photo =  (EyeemPhoto) response.get("photo");
		assertNotNull(photo.photoId);
		assertNotNull(photo.caption); 
		assertNotNull(photo.filename);
		assertNotNull(photo.photoUrl);
		assertNotNull(photo.thumbUrl);
		assertNotNull(photo.updatedString);
		assertNotNull(photo.title);
		assertNotNull(photo.height);
		assertNotNull(photo.width);
		assertNotNull(photo.totalLikes);
		assertNotNull(photo.totalComments);
		EyeemUser user = photo.user;
		assertNotNull(user.userId);
		assertNotNull(user.thumbUrl);
		assertNotNull(user.nickname);
		assertNotNull(user.fullname);
		assertNotNull(user.photoUrl);


		
		
	  } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	
	}
	public void testComment(){
		String file = "res/raw/comment.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	     int i;
	  try {
	   i = in.read();
	   while (i != -1)
	      {
	       byteArrayOutputStream.write(i);
	       i = in.read();
	      }
	   in.close();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  try {
		HashMap<String,Object> response =  api.sortResponse(new JSONObject( byteArrayOutputStream.toString()));
		EyeemComment comment =  (EyeemComment) response.get("comment");
		
		assertEquals(408594, comment.commentId);
		assertEquals(939584, comment.photoId);
		assertEquals("Congrats @Jason ! Your picture just got featured on \"In 24 Pictures Around the World\". Check it out on the EyeEm blog!", comment.message);
		assertNotNull(comment.author);
		assertNotNull(comment.mentionedUsers.get(0));		
		
		
	  } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}
	public void testNews(){
		String file = "res/raw/news.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	     int i;
	  try {
	   i = in.read();
	   while (i != -1)
	      {
	       byteArrayOutputStream.write(i);
	       i = in.read();
	      }
	   in.close();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  try {
		HashMap<String,Object> response =  api.sortResponse(new JSONObject( byteArrayOutputStream.toString()));
		@SuppressWarnings("unchecked")
		ArrayList<EyeemNewsItem> news =   (ArrayList<EyeemNewsItem>) response.get("news");
		for(int j = 0; j < news.size(); j++)
			assertNotNull(news.get(j));	
		EyeemNewsItem item = news.get(0);
		assertNotNull(item.itemType);
		assertNotNull(item.newsType);
		assertNotNull(item.message);
		assertNotNull(item.thumbUrl);
		EyeemNewsPagination paginationNews =   (EyeemNewsPagination) response.get("paginationNews");
		Log.d("parserTest", response.toString());
		assertEquals(10, paginationNews.limit);
		assertEquals(0, paginationNews.offset);
		assertEquals(1171, paginationNews.total);
		assertEquals(73, paginationNews.unseen);

		
		
	  } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}
	
	
	
}
