package ru.jetbrains.utils;

public class HtmlStorage {

    public static String getUnauthorizedUserHtml() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns=\"https://www.w3.org/1999/xhtml\" xmlns=\"https://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\\\"UTF-8\\\"/>\n" +
                "    <title>task2</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"text-align: center;\">\n" +
                "    <form action=\"/signin\" method=\"POST\">\n" +
                "        <p>Welcome new user! This is a home page</p>\n" +
                "        <input type=\"submit\" value=\"Sign in\" style=\"height:50px;width:200px\">\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }

    public static String getAuthorizedUserHtml(String name) {
        return "<!DOCTYPE html>\n" +
                "<html xmlns=\"https://www.w3.org/1999/xhtml\" xmlns=\"https://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\\\"UTF-8\\\"/>\n" +
                "    <title>task2</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"text-align: center;\">\n" +
                "    <form action=\"/home\" method=\"POST\">\n" +
                "        <p>Welcome " + name + "! This is a home page</p>\n" +
                "        <input type=\"submit\" value=\"Sign out\" style=\"height:50px;width:200px\">\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }
}
