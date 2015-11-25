package com.example.coke.fooding.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.coke.fooding.LoginFragment;
import com.example.coke.fooding.MainActivity;
import com.example.coke.fooding.R;
import com.example.coke.fooding.RegistrarseFragment;
import com.robotium.solo.Solo;

import org.junit.Test;


public class testRegistrarse extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testRegistrarse() {
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
        //Click on Registrarse
		solo.clickOnText(java.util.regex.Pattern.quote("Registrarse"));

        //Enter the text: 'asd@gmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email), "prueba@gmail.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.password));
        //Enter the text: 'holaquetal'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.password));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.password), "prueba");
		//Click on Empty Text View
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.nombre));
		//Enter the text: 'holaquetal'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.nombre));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.nombre), "prueba");
        //Click on Registrarse
		RegistrarseFragment.test = true;
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.RegistrarseButton));
		assertTrue(solo.searchText("Se ha enviado un e-mail de verificación al correo introducido"));

		//Introducimos un email NO VALIDO:
		RegistrarseFragment.test = false;
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email), "pruebaSinMail");
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.RegistrarseButton));
		assertTrue(solo.searchText("Usuario no registrado"));

	}
}
