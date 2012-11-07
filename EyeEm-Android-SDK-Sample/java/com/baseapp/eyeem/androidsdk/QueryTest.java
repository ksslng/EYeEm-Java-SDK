package com.baseapp.eyeem.androidsdk;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.junit.*;
import org.junit.runner.RunWith;


import com.baseapp.eyeem.androidsdk.query.EyeemAlbumsQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemAlbumsQuery.EESpecificAlbumsEndpoint;
import com.baseapp.eyeem.androidsdk.query.EyeemNewsQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemPhotosQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemSearchQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemTopicsQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemUsersQuery;
import com.baseapp.eyeem.androidsdk.query.EyeemPhotosQuery.EESpecificPhotosEndpoint;
import com.baseapp.eyeem.androidsdk.query.EyeemUsersQuery.EESpecificUsersEndpoint;
import com.baseapp.eyeem.androidsdk.query.EyeemVenueQuery;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class QueryTest extends EyeEmAbstractTest {
	protected static final boolean CONTRIBUTORS = false;
	protected static final boolean DETAILED = true;

	protected static final int FIRST_ID = 345643;
	protected static final String FOURSQUARE_ID = "345643";
	protected static final String GEOSEARCH = "foursquareVenue";
	protected static final String IDS = "2345,34536,456";
	protected static final double LATITUDE = 4.234523;
	protected static final double LONGITUDE = -34.234523;
	protected static final int MIN_PHOTOS = 15;
	protected static final boolean PHOTOS = true;
	protected static final boolean ONLY_ID = false;
	protected static final boolean PHOTOS_DETAILS = false;
	protected static final int SECOND_ID = 2342;
	protected static final String NAME = "name name name";
	protected static final String Q = "be";
	protected static final boolean TRENDING = true;
	protected static final String TYPE = "venue";

	protected static final boolean SUGGESTED = true;
	protected static final String CLOSEST_VENUE_IDS = "23645,3566,4656,657";
	protected static final int VENUE_ID = 36543;
	protected static final String SERVICES = "foursquare";
	
	protected static final String FIRST_ID_STRING = "me";
	protected static final String SECOND_ID_STRING = "ramz";
	protected static final boolean ALBUM_LIKE = true;
	protected static final boolean CALLBACK = false;
	protected static final String CITY_NAME = "Berlin";

	protected static final String AUTO_COMPLETE = "Ber";
	protected static final boolean ALBUMS = true;
	protected static final boolean USERS = true;
	protected static final boolean AGGREGATED = true;
	protected static final boolean COUNT_UNSEEN = true;

	@Before
	public void setUp() throws Exception {
		super.setUp();

	}
	@Test
	public void testAlbumsQuery(){
		EyeemAlbumsQuery query = new EyeemAlbumsQuery();
		query.setContributors(CONTRIBUTORS);
		query.firstId = FIRST_ID;
		query.setDetailed(DETAILED);
		query.foursquareId = FOURSQUARE_ID;
		query.geoSearch = GEOSEARCH;
		query.ids = IDS;
		query.latitude = LATITUDE;
		query.longitude = LONGITUDE;
		query.minPhotos = MIN_PHOTOS;
		query.setPhotos(PHOTOS);
		query.name = NAME;
		query.setOnlyId(ONLY_ID);
		query.setPhotoDetails(PHOTOS_DETAILS);
		query.q = Q;
		query.secondId = SECOND_ID;
		query.specificEndpoint = EESpecificAlbumsEndpoint.EEspecificAlbumsEndpointLikers;
		query.setTrending(TRENDING);
		query.type = TYPE;
		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/albums/"+FIRST_ID+"/likers/"+SECOND_ID, pair.getValue());
			if (pair.getName().equals("type"))
				assertEquals(TYPE, pair.getValue());
			if (pair.getName().equals("ids"))
				assertEquals(IDS, pair.getValue());					
			if (pair.getName().equals("q"))
				assertEquals( Q, pair.getValue());
			if (pair.getName().equals("onlyId"))
				assertEquals(Boolean.valueOf(ONLY_ID), Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("detailed"))
				assertEquals(Boolean.valueOf(DETAILED), Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("name"))
				assertEquals(NAME, pair.getValue());
			if (pair.getName().equals("trending"))
				assertEquals(Boolean.valueOf(TRENDING), Boolean.valueOf(pair.getValue()));	
			if (pair.getName().equals("foursquareId"))
				assertEquals(FOURSQUARE_ID, pair.getValue());
			if (pair.getName().equals("minPhotos"))	
				assertEquals(Integer.valueOf(MIN_PHOTOS), Integer.valueOf(pair.getValue()));
			if (pair.getName().equals("latitude"))					
				assertEquals(Double.valueOf(LATITUDE), Double.valueOf(pair.getValue()));
			if (pair.getName().equals("longitude"))					
				assertEquals(Double.valueOf(LONGITUDE), Double.valueOf(pair.getValue()));
			if (pair.getName().equals("photos"))	
				assertEquals(Boolean.valueOf(PHOTOS), Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("photoDetails"))	
				assertEquals(Boolean.valueOf(PHOTOS_DETAILS), Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("contributors"))	
				assertEquals(Boolean.valueOf(CONTRIBUTORS), Boolean.valueOf(pair.getValue()));

		}

	}
	
	@Test
	public void testNewsQuery(){
		EyeemNewsQuery query = new EyeemNewsQuery();
		query.setAggregated(AGGREGATED);
		query.setCountUnseen(COUNT_UNSEEN);
		
		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/news", pair.getValue());
			if (pair.getName().equals("aggregated"))
				assertEquals(Boolean.valueOf(AGGREGATED), Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("countUnseen"))
				assertEquals(Boolean.valueOf(COUNT_UNSEEN), Boolean.valueOf(pair.getValue()));
		}
	}
	
	@Test
	public void testPhotosQuery(){
		EyeemPhotosQuery query = new EyeemPhotosQuery();
		query.firstId = FIRST_ID;
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointAlbums;

		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/photos/"+FIRST_ID+"/albums", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointBgImages;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/photos/bgImages", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointComments;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/photos/"+FIRST_ID+"/comments", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointDiscover;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/photos/"+FIRST_ID+"/discover", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointFlags;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/photos/"+FIRST_ID+"/flag", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointHide;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/photos/"+FIRST_ID+"/hide", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointLikers;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/photos/"+FIRST_ID+"/likers", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointShare;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/photos/"+FIRST_ID+"/share", pair.getValue());

		}
		query.specificEndpoint = EESpecificPhotosEndpoint.EEspecificPhotosEndpointTopics;

		array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/photos/"+FIRST_ID+"/topics", pair.getValue());

		}
	}
	@Test
	public void testSearchQuery(){
		EyeemSearchQuery query = new EyeemSearchQuery();
		query.setUsers(USERS);
		query.setAlbums(ALBUMS);



		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/search", pair.getValue());
			if (pair.getName().equals("users"))
				assertEquals( Boolean.valueOf(USERS), Boolean.valueOf(pair.getValue()));					
			if (pair.getName().equals("albums"))
				assertEquals( Boolean.valueOf(ALBUMS), Boolean.valueOf(pair.getValue()));		
		

		}
	}
	@Test
	public void testTopicsQuery(){
		EyeemTopicsQuery query = new EyeemTopicsQuery();
		query.autoComplete = AUTO_COMPLETE;



		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals( "/topics", pair.getValue());
			if (pair.getName().equals("autoComplete"))
				assertEquals( AUTO_COMPLETE, pair.getValue());					

		

		}
	}
	@Test
	public void testUsersQuery(){
		
		EyeemUsersQuery query = new EyeemUsersQuery();
		query.firstIdString = FIRST_ID_STRING;
		query.setDetailed(DETAILED);
		query.ids = IDS;
		query.name = NAME;
		query.setOnlyId(ONLY_ID);
		query.q = Q;
		query.secondIdString = SECOND_ID_STRING;
		query.specificEndpoint = EESpecificUsersEndpoint.EEspecificUsersEndpointFollowers;
		query.setAlbumLike(ALBUM_LIKE);
		query.setCallback(CALLBACK);
		query.cityName = CITY_NAME;

		
		query.type = TYPE;
		
		query.setSuggested(SUGGESTED);
		query.closestVenueFsIds = CLOSEST_VENUE_IDS;
		
		
		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/users/"+FIRST_ID_STRING+"/followers/"+SECOND_ID_STRING , pair.getValue());
			if (pair.getName().equals("ids"))
				assertEquals(IDS , pair.getValue());					
			if (pair.getName().equals("q"))
				assertEquals( Q , pair.getValue());
			if (pair.getName().equals("onlyId"))
				assertEquals( Boolean.valueOf(ONLY_ID) , Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("detailed"))
				assertEquals( Boolean.valueOf(DETAILED) , Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("name"))
				assertEquals( NAME , pair.getValue());
			if (pair.getName().equals("albumLike"))
				assertEquals( Boolean.valueOf(ALBUM_LIKE) , Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("callback"))
				assertEquals( Boolean.valueOf(CALLBACK) , Boolean.valueOf(pair.getValue()));
			if (pair.getName().equals("suggested"))
				assertEquals( Boolean.valueOf(SUGGESTED) , Boolean.valueOf(pair.getValue()));			
			if (pair.getName().equals("closestVenueFsIds"))
				assertEquals(pair.getValue(), CLOSEST_VENUE_IDS , pair.getValue());			

		}
			
	}
	@Test
	public void testVenueQuery(){
		EyeemVenueQuery query = new EyeemVenueQuery();
		query.services = SERVICES;
		query.venueId = VENUE_ID;
		query.name = NAME;



		ArrayList<NameValuePair> array = query.transformQuery();
		for(NameValuePair pair : array){
			if (pair.getName().equals("endpoint"))
				assertEquals("/venues", pair.getValue());
			if (pair.getName().equals("venueId"))
				assertEquals( Integer.valueOf(VENUE_ID), Integer.valueOf(pair.getValue()));					
			if (pair.getName().equals("services"))
				assertEquals(SERVICES, pair.getValue());
			if (pair.getName().equals("name"))
				assertEquals(NAME, pair.getValue());
		

		}
	}
	
}
