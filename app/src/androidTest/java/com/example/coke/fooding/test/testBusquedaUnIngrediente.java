package com.example.coke.fooding.test;

import com.example.coke.fooding.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testBusquedaUnIngrediente extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public testBusquedaUnIngrediente() {
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
        //Set default small timeout to 11025 milliseconds
		Timeout.setSmallTimeout(11025);
        //Click on BUSCAR
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.menu_item_buscar));
        //Click on Añadir Ingrediente
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.angry_btn));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.TextoIng));
        //Enter the text: 'Pimienta'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.TextoIng));
		solo.enterText((android.widget.EditText) solo.getView(com.example.coke.fooding.R.id.TextoIng), "Pimienta");
        //Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertEquals(1,1);
	}
}
