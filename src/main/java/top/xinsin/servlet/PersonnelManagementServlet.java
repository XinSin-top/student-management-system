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
        //解决跨域访问问题
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept,"
                + "content-Type,origin,x-requested-with,content-type,accept,authorization,token,id,X-Custom-Header,X-Cookie,Connection,User-Agent,Cookie,*");
        resp.setHeader("Access-Control-Request-Headers", "Authorization,Origin, X-Requested-With,content-Type,Accept");
        resp.setHeader("Access-Control-Expose-Headers", "*");

        int min = Integer.parseInt(req.getParameter("min"));
        int max = Integer.parseInt(req.getParameter("max"));
        String person = null;
        Integer total = null;
        try {
            person = new PersonnelManagementDao().getPerson(min,max);
            total = new PersonnelManagementDao().getPersonTotal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String Result = "{" + "\"total\":" + total + ",\"data\":" + person + "}";
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write(Result);
    }
}
