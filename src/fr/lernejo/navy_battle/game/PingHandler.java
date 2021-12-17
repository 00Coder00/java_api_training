package fr.lernejo.navy_battle.game;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PingHandler implements HttpHandler {
    private int _count = 0;

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        _count++;
        String out = "Hello";

        exchange.sendResponseHeaders(200, out.length());

        OutputStream os = exchange.getResponseBody();
        os.write(out.getBytes(StandardCharsets.UTF_8));
        os.close();
        System.out.println("Number: " + exchange.getRequestMethod());
    }
}
