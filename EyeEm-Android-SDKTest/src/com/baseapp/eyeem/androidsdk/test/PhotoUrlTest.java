package com.baseapp.eyeem.androidsdk.test;



public class PhotoUrlTest extends EyeEmAbstractTest {


    private static final int IMAGE_SIZE = 250;
    private static final int IMAGE_SIZE_WRONG = 0;

    private static final int WIDTH = 200;
    private static final int WIDTH_WRONG = -500;

    private static final int HEIGHT = 600;
    private static final int HEIGHT_WRONG = -30;
    
    
    private static final String PHOTO_URL = "http://www.eyeem.com/thumb/h/450/7fe8299e24c954472047d1479ebc6ce01cac46b3-1350281567";
    private static final String PHOTO_URL_WRONG = "http://www.google.com";

    private static final String PHOTO_URL_SQUARE = "http://www.eyeem.com/thumb/sq/"+IMAGE_SIZE +"/7fe8299e24c954472047d1479ebc6ce01cac46b3-1350281567";
    private static final String PHOTO_URL_HEIGHT = "http://www.eyeem.com/thumb/h/"+HEIGHT +"/7fe8299e24c954472047d1479ebc6ce01cac46b3-1350281567";
    private static final String PHOTO_URL_WIDTH = "http://www.eyeem.com/thumb/w/"+WIDTH +"/7fe8299e24c954472047d1479ebc6ce01cac46b3-1350281567";
    private static final String PHOTO_URL_WIDTH_HEIGHT = "http://www.eyeem.com/thumb/"+WIDTH+"/"+HEIGHT +"/7fe8299e24c954472047d1479ebc6ce01cac46b3-1350281567";



	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSquare()
	{
		assertEquals(PHOTO_URL_SQUARE, api.urlFromPathForPhotoWithSquareSize(PHOTO_URL, IMAGE_SIZE));
		assertNull(api.urlFromPathForPhotoWithSquareSize(PHOTO_URL_WRONG, IMAGE_SIZE));
		assertNull(api.urlFromPathForPhotoWithSquareSize(PHOTO_URL, IMAGE_SIZE_WRONG));
		assertNull(api.urlFromPathForPhotoWithSquareSize(null, IMAGE_SIZE));

	}
	public void testWidth()
	{
		assertEquals(PHOTO_URL_WIDTH, api.urlFromPathForPhotoWithWidth(PHOTO_URL, WIDTH));
		assertNull(api.urlFromPathForPhotoWithWidth(PHOTO_URL_WRONG, WIDTH));
		assertNull(api.urlFromPathForPhotoWithWidth(PHOTO_URL, WIDTH_WRONG));
		assertNull(api.urlFromPathForPhotoWithWidth(null, WIDTH));

	}
	public void testHeight()
	{
		assertEquals(PHOTO_URL_HEIGHT, api.urlFromPathForPhotoWithHeight(PHOTO_URL, HEIGHT));
		assertNull(api.urlFromPathForPhotoWithHeight(PHOTO_URL_WRONG, HEIGHT));
		assertNull(api.urlFromPathForPhotoWithHeight(PHOTO_URL, HEIGHT_WRONG));
		assertNull(api.urlFromPathForPhotoWithHeight(null, HEIGHT));

	}
	public void testHeightAndWidth()
	{
		assertEquals(PHOTO_URL_WIDTH_HEIGHT, api.urlFromPathForPhotoWithWidthAndHeight(PHOTO_URL, WIDTH, HEIGHT));
		assertNull(api.urlFromPathForPhotoWithWidthAndHeight(PHOTO_URL_WRONG, WIDTH, HEIGHT));
		assertNull(api.urlFromPathForPhotoWithWidthAndHeight(PHOTO_URL, WIDTH_WRONG, HEIGHT));
		assertNull(api.urlFromPathForPhotoWithWidthAndHeight(PHOTO_URL, HEIGHT, WIDTH_WRONG));
		assertNull(api.urlFromPathForPhotoWithWidthAndHeight(null, WIDTH, HEIGHT));

	}
}
