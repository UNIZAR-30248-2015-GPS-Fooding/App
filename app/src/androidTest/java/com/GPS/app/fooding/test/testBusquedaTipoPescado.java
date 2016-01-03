package com.GPS.app.fooding.test;

import com.GPS.app.fooding.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testBusquedaTipoPescado extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public testBusquedaTipoPescado() {
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
		solo.waitForActivity(com.GPS.app.fooding.MainActivity.class, 2000);
        //Click on BUSCAR
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.menu_item_buscar));
        //Set default small timeout to 11647 milliseconds
		Timeout.setSmallTimeout(11647);
        //Click on Ninguno
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.dialog_spinner2));
        //Click on Pescado
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
        //Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertTrue(solo.searchText("Bonito"));
		assertTrue(solo.searchText("Fish"));
		assertTrue(solo.searchText("microondas"));
		assertTrue(solo.searchText("encebollado al jerez"));
	}
}
