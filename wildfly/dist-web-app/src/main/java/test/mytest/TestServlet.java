package test.mytest;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    private static final String SESSION_ID = "mysession";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();

        if(session.getAttribute(SESSION_ID) == null){
            var tmp = new MyProtoStreamSession();
            tmp.setTimestamp(LocalDateTime.now());
            session.setAttribute(SESSION_ID, tmp);
        }

        var mysession = (MyProtoStreamSession) session.getAttribute(SESSION_ID);


        var current = mysession.getRequestCount();
        var currentTimestamp = mysession.getTimestamp();
        mysession.setRequestCount(current += 1);
        resp.sendRedirect(resp.encodeRedirectURL("http://localhost:8080/TestServlet"));
        mysession.setTimestamp(LocalDateTime.now());

        //resp.setContentType("text/plain");
        //PrintWriter out = resp.getWriter();
        //out.println("counter: " + current + " prev timestamp: " + currentTimestamp);
    }
}
