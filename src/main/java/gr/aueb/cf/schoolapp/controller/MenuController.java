package gr.aueb.cf.schoolapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapp/menu")
public class MenuController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("teachersNotFound", false);
        request.setAttribute("isError", false);
        request.setAttribute("error", "");
        request.getRequestDispatcher("/school/static/templates/teachersmenu.jsp").forward(request, response);
    }
}
