package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.robotium.solo.Solo;

import org.junit.Test;

import java.io.File;


public class testPantallaCuenta extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testPantallaCuenta() {
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

		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));

		//Click on Iniciar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Iniciar Sesion"));
		//Enter the text: 'asd@gmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.email), "test@testfooding.test");
		//Click on Empty Text View
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.password));
		//Enter the text: 'holaquetal'
		solo.clearEditText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.password));
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.password), "testingu");

		LoginFragment.pruebaTest = false;
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.email_sign_in_button));

		//Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
		//Click on Iniciar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Mi Cuenta"));
		//Verificamos que muestra el correo
		assertTrue(solo.searchText("test@testfooding.test"));
	}
}
