package ru.petclinic.servlets.servlets;

import ru.petclinic.servlets.models.User;
import ru.petclinic.servlets.store.UserCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateServlet extends HttpServlet {


    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.USER_CACHE.add(new User(USER_CACHE.generateId(), req.getParameter("login"), req.getParameter("email")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }
}