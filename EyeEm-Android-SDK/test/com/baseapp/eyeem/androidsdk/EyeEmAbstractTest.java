package com.baseapp.eyeem.androidsdk;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;



import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class EyeEmAbstractTest  {
	protected EyeEmAPI api;
    protected static final String CLIENT_ID = "P90rwnWXzCrvyO7wEAFJ9tmo7XaCnoz9";
    protected static final String ACCESS_TOKEN = "98de606cb2a748e351b959b2afc079dae6b0c00e";
    protected static final String REDIRECT_URI = "redirect://authorize";

    public final static String baseURL = "https://www.eyeem.com/api/v2";
    
    @Before
		public void setUp() throws Exception {
			api = new EyeEmAPI(CLIENT_ID,REDIRECT_URI, ACCESS_TOKEN);

		}
    @Test
		public void testTest() {
    		assertTrue(true);
    	}

}
