package com.baseapp.eyeem.androidsdk;


import static org.junit.Assert.*;


import org.junit.*;
import org.junit.runner.RunWith;

import com.baseapp.eyeem.androidsdk.api.EyeemConnect;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ConnectTest extends EyeEmAbstractTest {
	protected String redirect_Uri_escaped = "redirect%3A%2F%2Fauthorize";
	
	@Before	
		public void setUp() throws Exception {
			super.setUp();
		}

	@Test
		public void testAuthorizationURL() {
			EyeemConnect connect = new EyeemConnect();
			connect.setEyeEmAPI(api);
			assertEquals("http://www.eyeem.com/oauth/authorize?response_type=code" + 
					"&client_id=" + EyeEmAbstractTest.CLIENT_ID + "&redirect_uri=" + redirect_Uri_escaped
						, connect.authorizationURL());
		}

}
