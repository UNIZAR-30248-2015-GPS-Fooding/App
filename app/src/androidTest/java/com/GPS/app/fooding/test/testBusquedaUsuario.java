package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import java.util.concurrent.TimeUnit;


public class testBusquedaUsuario extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testBusquedaUsuario() {
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
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e) {
			;
		}
		//Click on Lista Usuarios
		solo.clickOnText(java.util.regex.Pattern.quote("Lista de Usuarios"));
		//Verificamos que existe usuario Fooding
		assertTrue(solo.searchText("Fooding"));
        //Click on BUSCAR USUARIO
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.menu_item_buscar_usuario));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.textoNom_user));
        //Enter the text: 'oodin' , PAra mostrar unicamente el usuario Fooding
		solo.clearEditText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.textoNom_user));
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.textoNom_user), "oodin");
        //Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		//Verificamos que ha encontrado Fooding
		assertTrue(solo.searchText("Fooding"));
		//Pulsamos en Fooding para verificar que aparece la pantalla del usuario
		solo.clickOnText(java.util.regex.Pattern.quote("Fooding"));
		//Verificamos que ha encontrado Fooding
		assertTrue(solo.searchText("Fooding"));
	}
}
