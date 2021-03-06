package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import org.junit.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;


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

        //Intenamos marcar como faovrito sin estar registrado
        solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.iconStar));
        //Verificamos que no lo permite
        assertTrue(solo.searchText("Debes estar"));

        try {
            // Logueamos y sacamos menu favoritos a la palestra
            LoginFragment.doLoginStatic("test@testfooding.test", "testingu");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(true);
                }
            });
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                ;
            }
            //Intenamos marcar como favorito estando registrado
            solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.iconStar));
            //Verificamos que SI lo permite
            assertTrue(solo.searchText("Favorito añadido"));

            //Intenamos QUITAR como favorito estando registrado
            solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.iconStar));
            //Verificamos que SI lo permite
            assertTrue(solo.searchText("Favorito eliminado"));

            //Volvemos a marcar como favorito
            solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.iconStar));
            //Verificamos que SI lo permite
            assertTrue(solo.searchText("Favorito añadido"));
            solo.goBack();

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
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                ;
            }
            //Verificamos que se muestra esa receta en la lista de favoritos
            assertTrue(solo.searchText("Macarrones normales y corrientes"));

        } finally {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(false);
                }
            });
            File root = new File(MainActivity.mPath, "ficheroUsuarios.txt");
            if (root.exists() && root.isFile() && MainActivity.registrado) {
                root.delete();
                MainActivity.registrado = false;
            }
        }
    }
}
