package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;


public class testPuntuacionUsuarios extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testPuntuacionUsuarios() {
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
		solo.waitForActivity(MainActivity.class, 2000);
        //Set default small timeout to 15575 milliseconds
		Timeout.setSmallTimeout(15575);
		//Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
		//Click on Lista Usuarios
		solo.clickOnText(java.util.regex.Pattern.quote("Lista de Usuarios"));
		//Verificamos que se muestra el porcentaje de
		//TODO Verificar que se muestra porcentaje de votos
		//solo.clickOnText("Fooding");
		//assertTrue(solo.searchText("Puntos: 1000"));

		//Pulsamos en Fooding para verificar que aparece los puntos en la pantalla del usuario
		solo.clickOnText(java.util.regex.Pattern.quote("Fooding"));
		//Verificamos que muestra el total de votos
		//TODO Verificar que se muestra porcentaje de votos
		assertTrue(solo.searchText("Puntos: 1000"));
	}
}
