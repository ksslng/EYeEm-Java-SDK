package com.baseapp.eyeem.androidsdk.test;


import com.baseapp.eyeem.androidsdk.api.EyeemConnect;

public class ConnectTest extends EyeEmAbstractTest {
	protected String redirect_Uri_escaped = "redirect%3A%2F%2Fauthorize";
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testAuthorizationURL() {
		EyeemConnect connect = new EyeemConnect();
		connect.setEyeEmAPI(api);
		assertEquals("http://www.eyeem.com/oauth/authorize?response_type=code" + 
		    "&client_id=" + EyeEmAbstractTest.CLIENT_ID + "&redirect_uri=" + redirect_Uri_escaped
, connect.authorizationURL());
	}

}
