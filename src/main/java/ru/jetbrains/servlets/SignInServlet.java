package ru.jetbrains.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.jetbrains.utils.GoogleApiHelper;

import java.io.IOException;

public class SignInServlet extends HttpServlet {

    /**
     * Start gmail authorization.
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        var redirect = GoogleApiHelper.getOauthUri();

        response.sendRedirect(redirect);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
