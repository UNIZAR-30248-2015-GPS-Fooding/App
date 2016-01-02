package com.example.coke.fooding.test;

import com.example.coke.fooding.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testModificarComensales extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public testModificarComensales() {
        super(MainActivity.class);
    }

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

    public void testRun() {
        //Wait for activity: 'com.example.coke.fooding.MainActivity'
        solo.waitForActivity(com.example.coke.fooding.MainActivity.class, 2000);
        //Wait for activity: 'com.example.coke.fooding.MainActivity'
        assertTrue("com.example.coke.fooding.MainActivity is not found!", solo.waitForActivity(com.example.coke.fooding.MainActivity.class));
        //Click on "MAccarrones" de prueba
        solo.clickOnText("Macarrones normales y corrientes");
        //Verifica la cantidad inicial
        assertTrue(solo.searchText("100 gramos Macarrones"));
        assertTrue(solo.searchText("200 gramos Tomate"));
        //Click on "- comensales"
        solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.button2));
        //Verifica que no se han modificado la cantidad ya que el minimo es 1
        assertTrue(solo.searchText("100 gramos Macarrones"));
        assertTrue(solo.searchText("200 gramos Tomate"));
        //Click on "+ comensales"
        solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.button));
        //Verifica que se han modificado la cantidad al doble
        assertTrue(solo.searchText("200 gramos Macarrones"));
        assertTrue(solo.searchText("400 gramos Tomate"));
    }
}
