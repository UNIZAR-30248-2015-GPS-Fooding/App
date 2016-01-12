package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import org.junit.Test;

import java.io.File;


public class testFavoritos extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public testFavoritos() {
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
        solo.waitForActivity(com.GPS.app.fooding.MainActivity.class, 2000);
        //Set default small timeout to 15575 milliseconds
        Timeout.setSmallTimeout(15575);
        //Click on "Macarrones" de prueba
        solo.clickOnText("Macarrones normales y corrientes");

        //Hacemos scroll
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        int fromX, toX, fromY, toY = 0, stepCount = 10;
        // Scroll Down // Drag Up
        fromX = screenWidth / 2;
        toX = screenWidth / 2;
        fromY = (screenHeight / 2) + (screenHeight / 3);
        toY = (screenHeight / 2) - (screenHeight / 3);
        solo.drag(fromX, toX, fromY, toY, stepCount);
        //TODO verificar que no se puede marcar como favorito si no esas registrado


        try {

            //nos registramos
            LoginFragment.doLoginStatic("test@testfooding.test", "testingu");
            //TODO Pulsar el boton para añadir a favorito y verficar que está como favorito con el metodo de la interface

            //TODO Pulsar el boton para añadir a favorito y verficar que ya NO está favorito con el metodo de la interface

            //TODO Pulsar el boton para añadir a favorito y verficar que está de nuevo como favorito con el metodo de la interface

            //Click on ImageView
            solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
            // Scroll Down // Drag Up
            fromX = screenWidth / 2;
            toX = screenWidth / 2;
            fromY = (screenHeight / 2) + (screenHeight / 3);
            toY = (screenHeight / 2) - (screenHeight / 3);
            solo.drag(fromX, toX, fromY, toY, stepCount);
            //Click on Favoritos
            solo.clickOnText(java.util.regex.Pattern.quote("Mis Favoritos"));
            //TODO Verificar que se muestra la receta
            assertTrue(solo.searchText("Macarrones normales y corrientes"));

        } finally {
            File root = new File(MainActivity.mPath, "ficheroUsuarios.txt");
            if (root.exists() && root.isFile() && MainActivity.registrado) {
                root.delete();
                MainActivity.registrado = false;
            }
        }
    }
}
