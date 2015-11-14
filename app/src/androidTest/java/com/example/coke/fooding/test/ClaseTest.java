package com.example.coke.fooding.test;

import com.example.coke.fooding.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;


public class ClaseTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public ClaseTest() {
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
		solo.waitForActivity(com.example.coke.fooding.MainActivity.class, 2000);
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on Iniciar Sesion
		solo.clickOnText(java.util.regex.Pattern.quote("Iniciar Sesion"));
        //Enter the text: 'asd@gmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.email), "asd@gmail.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.password));
        //Enter the text: 'holaquetal'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.password));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.password), "holaquetal");
        //Click on Iniciar Sesion
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.email_sign_in_button));
		assertEquals(1,1);
	}
}
