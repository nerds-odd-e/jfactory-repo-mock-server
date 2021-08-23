package com.odde.jfactory;

import io.cucumber.java.Before;
import lombok.SneakyThrows;
import org.mockserver.integration.ClientAndServer;

public class MockServer {

    private static ClientAndServer clientAndServer;

    public static ClientAndServer getClientAndServer() {
        return clientAndServer;
    }

    @Before(order = 0)
    @SneakyThrows
    public void resetMockServer() {
        if (clientAndServer == null) {
            clientAndServer = ClientAndServer.startClientAndServer(9081);
        }
        clientAndServer.reset();
    }

}