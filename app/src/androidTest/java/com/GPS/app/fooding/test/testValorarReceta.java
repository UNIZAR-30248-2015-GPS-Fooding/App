package com.GPS.app.fooding.test;

import android.test.ActivityInstrumentationTestCase2;
import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.connection.Access;
import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import org.junit.Test;
import java.io.File;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;


public class testValorarReceta extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public testValorarReceta() {
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
        File rootInit = new File(MainActivity.mPath, "ficheroUsuarios.txt");
        if (rootInit.exists() && rootInit.isFile() && MainActivity.registrado) {
            rootInit.delete();
            MainActivity.registrado = false;
        }

        //Click on "Macarrones" de prueba
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            ;
        }
        solo.clickOnText("Macarrones normales y corrientes");
        Receta data1 = Access.getReceta(34);
        double media = 0.0;
        if(data1.getMe_gusta()+data1.getNo_me_gusta()!=0) {
            media = (double) (data1.getMe_gusta()) / (double) (data1.getNo_me_gusta() + data1.getMe_gusta());
        }
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

        //Verificamos que tiene 0% de votos
        try {
            assertTrue(solo.searchText(MainActivity.DF.format(media * 100) + "% de votos positivos"));
            LoginFragment.doLoginStatic("test@testfooding.test", "testingu");
            //Pulsamos boton MeGusta
            solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.buttonMeGusta));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                ;
            }
            Receta data2 = Access.getReceta(34);
            media = 0.0;
            if(data2.getMe_gusta()+data2.getNo_me_gusta()!=0) {
                media = (double) (data2.getMe_gusta()) / (double) (data2.getNo_me_gusta() + data2.getMe_gusta());
            }
            assertTrue(solo.searchText(MainActivity.DF.format(media * 100) + "% de votos positivos"));
            //Pulsamos boton NO MeGusta
            solo.clickOnView(solo.getView(com.GPS.app.fooding.R.id.buttonNoMeGusta));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                ;
            }
            Receta data3 = Access.getReceta(34);
            media = 0.0;
            if(data3.getMe_gusta()+data3.getNo_me_gusta()!=0) {
                media = (double) (data3.getMe_gusta()) / (double) (data3.getNo_me_gusta() + data3.getMe_gusta());
            }
            assertTrue(solo.searchText(MainActivity.DF.format(media * 100) + "% de votos positivos"));
        } finally {
            File root = new File(MainActivity.mPath, "ficheroUsuarios.txt");
            if (root.exists() && root.isFile() && MainActivity.registrado) {
                root.delete();
                MainActivity.registrado = false;
            }
        }
    }
}
