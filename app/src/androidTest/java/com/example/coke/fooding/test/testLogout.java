package com.example.coke.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.coke.fooding.LoginFragment;
import com.example.coke.fooding.MainActivity;
import com.robotium.solo.Solo;

import org.junit.Test;


public class testLogout extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testLogout() {
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
		solo.waitForActivity(MainActivity.class, 2000);
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
		solo.waitForText("Inicio");

		int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		int fromX, toX, fromY, toY = 0,stepCount=10;
		// Scroll Down // Drag Up
		fromX = screenWidth/2;
		toX = screenWidth/2;
		fromY = (screenHeight/2) + (screenHeight/3);
		toY = (screenHeight/2) - (screenHeight / 3);
		solo.drag(fromX, toX, fromY, toY, stepCount);

		//Click on Cerrar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Cerrar Sesión"));
		assertTrue(solo.searchText("ERROR. Primero debe hacer login"));


		//Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on Iniciar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Iniciar Sesion"));
        //Enter the text: 'prueba@gmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email), "prueba@gmail.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.password));
        //Enter the text: 'holaquetal'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.password));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.password), "prueba");
        //Click on Iniciar Sesion en modo Test y modo !Test
		LoginFragment.pruebaTest = true;
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.email_sign_in_button));
		assertTrue(solo.searchText("Logeado correctamente"));

		//Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
		//Click on Cerrar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Cerrar Sesión"));
		assertTrue(solo.searchText("Sesion cerrada correctamente"));
	}
}
