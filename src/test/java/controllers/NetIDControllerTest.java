package controllers;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class NetIDControllerTest {

    private NetIDController netIDController;

    @Before
    public void initialize () {
        netIDController = new NetIDController();
    }

    @Test
    public void testGetID() {
        assertThat("Net ID should be yz2352", netIDController.getID() == "yz2352");
    }
}