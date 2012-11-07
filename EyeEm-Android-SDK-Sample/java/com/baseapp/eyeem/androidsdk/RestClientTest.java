package com.baseapp.eyeem.androidsdk;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.junit.*;
import org.junit.runner.RunWith;



import com.baseapp.eyeem.androidsdk.api.RestClient;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RestClientTest extends EyeEmAbstractTest {
	
	protected NameValuePair endpointAlbums = new BasicNameValuePair("endpoint", "/albums");
	protected NameValuePair endpointSearch = new BasicNameValuePair("endpoint", "/search");
	protected NameValuePair endpointPhotosComment = new BasicNameValuePair("endpoint", "/photos/1052366/comments");
	protected NameValuePair endpointPhotosLike = new BasicNameValuePair("endpoint", "/photos/453556/likers/me");

	protected NameValuePair detailed = new BasicNameValuePair("detailed", "true");
	protected NameValuePair numPhotos = new BasicNameValuePair("numPhotos", "45");
	protected NameValuePair includeContributors = new BasicNameValuePair("includeContributors", "true");
	protected NameValuePair q = new BasicNameValuePair("q", "Berlin Meetup");
	protected NameValuePair qNoResult = new BasicNameValuePair("q", "ghjkjjzr fashkdjfhkasjdhf akjsdhflkjsahdlkj");
	protected NameValuePair message = new BasicNameValuePair("message", "testing");

	protected NameValuePair trending = new BasicNameValuePair("trending", "true");
	protected NameValuePair includeComments = new BasicNameValuePair("includeComments", "false");
	protected NameValuePair includeLikers = new BasicNameValuePair("includeComments", "false");
	protected NameValuePair includeAlbums = new BasicNameValuePair("includeAlbums", "true");

	protected String expectedEndpointDetailed = "/albums?detailed=true";
	protected String expectedQ = "?q=Berlin+Meetup";
	protected String expectedNumPhotosIncludeContributors = "?numPhotos=45&includeContributors=true";
	protected String expectedIncludeContributorsNumPhotos = "?includeContributors=true&numPhotos=45";
	
	
	protected String postedComment = "{\"comment\":{\"id\":";
	protected String noResult = "{\"totalAlbums";
	protected String alreadyFavorited = "{\"message\":\"Already favorited this photo";
	protected String successfullyFavorited =  "{\"message\":\"Successfully favorited photo";

	protected int responseCodeEndpointTrending = 200; 
	protected int responseCodeEndpointTrendingNoClientId = 401; 
	
	protected ArrayList<NameValuePair> params;
	protected RestClient client;
	protected HttpGet request;
	@Before
	public void setUp() throws Exception {
		super.setUp();
		params = new ArrayList<NameValuePair>();
		client = new RestClient(params, baseURL);
		request = new HttpGet(client.getURLWithParams());



	}

	@Test
	public void testGetCombinedParams() {


		client.RemoveParam(endpointAlbums.getName(), endpointAlbums.getValue());
		client.RemoveParam(detailed.getName(), detailed.getValue());
		
		client.AddParam(q.getName(), q.getValue());
		assertEquals(expectedQ, client.getCombinedParams());
		client.RemoveParam(q.getName(), q.getValue());
		
		
		client.AddParam(numPhotos.getName(), numPhotos.getValue());
		client.AddParam(includeContributors.getName(), includeContributors.getValue());
		if(client.getCombinedParams().equals(expectedIncludeContributorsNumPhotos))
			assertEquals(expectedIncludeContributorsNumPhotos, client.getCombinedParams());
		else 
			assertEquals( expectedNumPhotosIncludeContributors, client.getCombinedParams());

		client.RemoveParam(numPhotos.getName(), numPhotos.getValue());
		client.RemoveParam(includeContributors.getName(), includeContributors.getValue());
		
		
		
		

	}
	@Test
	public void testExecute0() {
		params.add(endpointAlbums);
		Robolectric.addPendingHttpResponse(responseCodeEndpointTrending, "OK");

		params.add(trending);
		client.AddParam("client_id", CLIENT_ID);			
		client.executeRequest(request);
		assertEquals(responseCodeEndpointTrending, client.getResponseCode());
		Robolectric.clearPendingHttpResponses();
	}
	@Test
	public void testExecute1() {	
		Robolectric.addPendingHttpResponse(responseCodeEndpointTrendingNoClientId, "");

		client.RemoveParam("client_id", CLIENT_ID);			
		request = new HttpGet(client.getURLWithParams());
		client.executeRequest(request);
		assertEquals(responseCodeEndpointTrendingNoClientId, client.getResponseCode());
		
		params.clear();
		Robolectric.clearPendingHttpResponses();

	}	
	@Test
	public void testExecute2() {		
		params.add(endpointSearch);
		params.add(includeAlbums);
		params.add(qNoResult);
		Robolectric.addPendingHttpResponse(responseCodeEndpointTrending, noResult);

		client = new RestClient(params, baseURL);
		client.AddParam("client_id", CLIENT_ID);			
		request = new HttpGet(client.getURLWithParams());
		client.executeRequest(request);
		assertTrue(client.getResponse().startsWith(noResult) );
		params.clear();
		Robolectric.clearPendingHttpResponses();

	}	
	@Test
	public void testExecute3() {
		Robolectric.addPendingHttpResponse(responseCodeEndpointTrending, postedComment);

		params.add(endpointPhotosComment);
		params.add(message);
		client = new RestClient(params, baseURL);
		client.AddParam("access_token", ACCESS_TOKEN);			

		HttpPost postRequest = new HttpPost(client.getURLWithParams());
		client.executeRequest(postRequest);
		assertTrue(client.getResponse().startsWith(postedComment) );
		
		params.clear();
		Robolectric.clearPendingHttpResponses();

	}	
	@Test
	public void testExecute4() {
		Robolectric.addPendingHttpResponse(responseCodeEndpointTrending, alreadyFavorited);

		params.add(endpointPhotosLike);
		client = new RestClient(params, baseURL);
		client.AddParam("access_token", ACCESS_TOKEN);			

		HttpPut putRequest = new HttpPut(client.getURLWithParams());
		client.executeRequest(putRequest);
		assertTrue(client.getResponse().startsWith(alreadyFavorited) );

		Robolectric.clearPendingHttpResponses();

	}


			
}
