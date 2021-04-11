package ru.jetbrains.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.jetbrains.servlets.CallBackServlet;
import ru.jetbrains.servlets.SignInServlet;

public class Main {

    private static final String HOME_PAGE = "../home.html";

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignInServlet()), "/signin");
        context.addServlet(new ServletHolder(new CallBackServlet()), "/home");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase(HOME_PAGE);
        HandlerList handlers = new HandlerList();
        final Handler[] handler = {resource_handler, context};
        handlers.setHandlers(handler);

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
