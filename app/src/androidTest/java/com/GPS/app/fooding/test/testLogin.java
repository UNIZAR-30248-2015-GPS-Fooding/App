package com.GPS.app.fooding.test;

import com.GPS.app.fooding.fragments.LoginFragment;
import com.GPS.app.fooding.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;


public class testLogin extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public testLogin() {
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
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on Iniciar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Iniciar Sesion"));
        //Enter the text: 'asd@gmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.email), "prueba@gmail.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.password));
        //Enter the text: 'holaquetal'
		solo.clearEditText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.password));
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.password), "prueba");
        //Click on Iniciar Sesion en modo Test y modo !Test
		LoginFragment.pruebaTest = false;
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.email_sign_in_button));
		assertTrue(solo.searchText("Email o passwd fallido"));
		LoginFragment.pruebaTest = true;
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.email_sign_in_button));
		assertTrue(solo.searchText("Logeado correctamente"));
	}
}
