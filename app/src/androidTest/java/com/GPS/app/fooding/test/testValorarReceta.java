package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import org.junit.Test;


public class testValorarReceta extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testValorarReceta() {
		super(MainActivity.class);
  	}

	@Override
  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();

  	}

   	@Override
   	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	@Test
	public void testRun() {
		//Wait for activity: 'com.example.coke.fooding.MainActivity'
		solo.waitForActivity(com.GPS.app.fooding.MainActivity.class, 2000);
		//Set default small timeout to 15575 milliseconds
		Timeout.setSmallTimeout(15575);
		//Click on "MAccarrones" de prueba
		solo.clickOnText("Macarrones normales y corrientes");

		//Hacemos scroll
		int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		int fromX, toX, fromY, toY = 0,stepCount=10;
		// Scroll Down // Drag Up
		fromX = screenWidth/2;
		toX = screenWidth/2;
		fromY = (screenHeight/2) + (screenHeight/3);
		toY = (screenHeight/2) - (screenHeight / 3);
		solo.drag(fromX, toX, fromY, toY, stepCount);

		//Verificamos que tiene 0% de votos
		assertTrue(solo.searchText("Valoracion: 0.0%"));
		//Pulsamos boton MeGusta
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.buttonMeGusta));
		assertTrue(solo.searchText("Valoracion: 100.0%"));
		//Pulsamos boton NO MeGusta
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.buttonNoMeGusta));
		assertTrue(solo.searchText("Valoracion: 0.0%"));
	}
}
