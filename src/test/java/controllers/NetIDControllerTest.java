package controllers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class NetIDControllerTest {

    private NetIDController controller = new NetIDController();

    @Test
    public void testGetID() {
        assertThat("Net ID should be yz2352", controller.getID() == "yz2352");
    }
}