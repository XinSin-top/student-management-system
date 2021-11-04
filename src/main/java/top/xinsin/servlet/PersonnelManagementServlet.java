package top.xinsin.servlet;

import top.xinsin.dao.PersonnelManagementDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class PersonnelManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String person = null;
        try {
            person = new PersonnelManagementDao().getPerson();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write(person);
    }
}
