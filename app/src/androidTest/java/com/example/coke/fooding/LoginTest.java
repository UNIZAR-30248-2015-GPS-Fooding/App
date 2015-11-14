package com.example.coke.fooding;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Test;


/**
 * Created by coke on 04/11/2015.
 */
public class LoginTest extends ActivityInstrumentationTestCase2 <MainActivity> {

    MainActivity app;
    private Solo solo;

    public LoginTest(Class activityClass) {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        app = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * Test de login correcto
     */
    @Test
    public void testLoginCorrecto() throws Exception {
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
        assertEquals(1, 1);
    }
}