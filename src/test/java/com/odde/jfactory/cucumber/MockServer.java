package com.odde.jfactory.cucumber;

import io.cucumber.java.Before;
import lombok.SneakyThrows;
import org.mockserver.integration.ClientAndServer;

public class MockServer {

    private static final ClientAndServer clientAndServer = ClientAndServer.startClientAndServer(9081);

    public static ClientAndServer getClientAndServer() {
        return clientAndServer;
    }

    @Before(order = 0)
    @SneakyThrows
    public void resetMockServer() {
        clientAndServer.reset();
    }

}