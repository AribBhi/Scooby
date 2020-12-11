package com.vyy.scooby.webhook;

import com.sun.net.httpserver.*;
import com.vyy.scooby.util.WebUtils;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerHook {
    public HttpServerHook() throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext context = server.createContext("/dblwebhook");
        context.setHandler(WebUtils::handleRequest);
        server.start();
    }
}
