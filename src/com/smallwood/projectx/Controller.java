package com.smallwood.projectx;

import com.smallwood.projectx.helpers.ControllerHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Override;

@WebServlet(name="com.smallwood.projectx.Controller", displayName="com.smallwood.projectx.Controller", urlPatterns = {"/controller", "/com/projectx/controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequests(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequests(req, resp);
    }

    private void handleRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerHelper helper = new ControllerHelper(request, response, this);
        helper.doGet();
    }
}