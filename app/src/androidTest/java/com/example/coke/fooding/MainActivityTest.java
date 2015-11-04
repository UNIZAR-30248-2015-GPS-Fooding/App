package com.example.coke.fooding;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.TestCase;


/**
 * Created by coke on 04/11/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 <MainActivity> {

    MainActivity app;
    private Solo solo;

    public MainActivityTest(Class activityClass) {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        app = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * Test de prueba
     */
    public void testCrearMetadato() throws Exception {
        assertEquals("FALLO", 1, 1);
    }
}