package com.baseapp.eyeem.androidsdk.test;

import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;

import junit.framework.TestCase;

public class EyeEmAbstractTest extends TestCase {
	protected EyeEmAPI api;
    protected static final String CLIENT_ID = "P90rwnWXzCrvyO7wEAFJ9tmo7XaCnoz9";
    protected static final String ACCESS_TOKEN = "98de606cb2a748e351b959b2afc079dae6b0c00e";
    protected static final String REDIRECT_URI = "redirect://authorize";

    public final static String baseURL = "https://www.eyeem.com/api/v2";

	protected void setUp() throws Exception {
		super.setUp();
		api = new EyeEmAPI(CLIENT_ID,REDIRECT_URI, ACCESS_TOKEN);

	}

}
