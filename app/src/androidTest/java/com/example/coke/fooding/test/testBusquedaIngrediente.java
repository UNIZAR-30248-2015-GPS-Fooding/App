package com.example.coke.fooding.test;

import com.example.coke.fooding.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testBusquedaIngrediente extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public testBusquedaIngrediente() {
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
		//Click on BUSCAR
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.menu_item_buscar));
		//Click on Ninguno
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.TextoIng));
		//Set default small timeout to 10968 milliseconds
		Timeout.setSmallTimeout(10968);
		//Scroll to Pimienta
		android.widget.ListView listView0 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 0);
		solo.scrollListToLine(listView0, 68);
		//Click on Pimienta
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
		//Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertTrue(solo.searchText("Ensalada Waldorf"));
		assertTrue(solo.searchText("Pavo al ajillo"));
		assertTrue(solo.searchText("encebollado al jerez"));
		assertTrue(solo.searchText("Fish and chips"));


		//Click on BUSCAR
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.menu_item_buscar));
		//Click on Añadir Ingrediente
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.angry_btn));
		//Click on Ninguno
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.TextoIng2));
		//Scroll to Sal
		android.widget.ListView listView1 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 0);
		solo.scrollListToLine(listView1, 74);
		//Click on Sal
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
		//Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertTrue(solo.searchText("Ensalada Waldorf"));
		assertTrue(solo.searchText("Pavo al ajillo"));
		assertTrue(solo.searchText("encebollado al jerez"));
		assertTrue(solo.searchText("Fish and chips"));



		//Click on BUSCAR
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.menu_item_buscar));
		//Click on Añadir Ingrediente
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.angry_btn));
		//Click on Ninguno
		solo.clickOnView(solo.getView(com.example.coke.fooding.R.id.TextoIng3));
		//Scroll to Ajo
		android.widget.ListView listView2 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 0);
		solo.scrollListToLine(listView2, 1);
		//Click on Ajo
		solo.clickOnView(solo.getView(android.R.id.text1, 4));
		//Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertTrue(solo.searchText("Pavo al ajillo"));
		assertTrue(solo.searchText("encebollado al jerez"));
	}
}
