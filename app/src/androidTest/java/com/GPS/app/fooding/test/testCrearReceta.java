package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.fragments.CrearRecetaFragment;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;


public class testCrearReceta extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public testCrearReceta() {
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

		LoginFragment.doLoginStatic("test@testfooding.test", "testingu");

		//Click on Crear Receta
		solo.clickOnText(java.util.regex.Pattern.quote("Crear receta"));
		//Introducimos nombre receta
		long tiempo = System.nanoTime();
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.nombreReceta), "RecetaPrueba"+ tiempo);
		//Introducimos tipo
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.spinnerTipoReceta));
		//Click on Pescado
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		//Introducimos cant
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.cant), "10");
		//Introducimos uds
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.uds), "Gramos");
		//Introducimos Ingrediente
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.TextoIng));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		//Introducimos elaboracion
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.elaboracion), "elaboracionTest");
		//Click on Crear Receta
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.CrearRecetaButton));
		assertTrue(solo.searchText("Receta creada correctamente"));

		//Ahora buscamos la receta
        //Click on BUSCAR
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.menu_item_buscar));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.textoNom));
        //Enter the text: 'macarrones'
		solo.clearEditText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.textoNom));
		solo.enterText((android.widget.EditText) solo.getView(com.GPS.app.fooding.R.id.textoNom), "RecetaPrueba"+tiempo);
        //Click on Filtrar
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertTrue(solo.searchText("RecetaPrueba"+tiempo));
	}
}
