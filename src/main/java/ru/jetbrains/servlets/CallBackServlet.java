package ru.jetbrains.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.jetbrains.utils.GoogleApiHelper;
import ru.jetbrains.utils.HtmlStorage;

import java.io.IOException;

public class CallBackServlet extends HttpServlet {

    /**
     * Display "/home" page after logging into account.
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        var code = request.getParameter("code");
        GoogleApiHelper.initAccessToken(code);

        var name = GoogleApiHelper.getUserName();

        var htmlContent = name.equals("")
                ? HtmlStorage.getUnauthorizedUserHtml()
                : HtmlStorage.getAuthorizedUserHtml(name);

        response.getWriter().println(htmlContent);
    }

    /**
     * Display "/home" page after logging out.
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        GoogleApiHelper.signOut();

        response.getWriter().println(
                HtmlStorage.getUnauthorizedUserHtml()
        );

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
