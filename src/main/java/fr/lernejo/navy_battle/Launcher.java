package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(Integer.parseInt(args[0])), 0);
        httpServer.setExecutor(Executors.newSingleThreadExecutor());

        httpServer.createContext("/ping", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                final byte[] out = "Hello".getBytes("UTF-8");

                httpExchange.sendResponseHeaders(200, out.length);

                OutputStream os = httpExchange.getResponseBody();
                os.write(out);
                os.close();
            }
        });
        httpServer.start();

        System.out.println("HttpServer Test Start!");
    }
}

