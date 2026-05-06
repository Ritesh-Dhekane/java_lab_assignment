import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String college = request.getParameter("college");
        String city = request.getParameter("city");
        String title = request.getParameter("title");
        String gender = request.getParameter("gender");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Q41 Output</title></head>");
        out.println("<body>");
        out.println("<h2>Question 41 - Entered Details</h2>");
        out.println("<p>devName: Ritesh Dhekane</p>");
        out.println("<table border='1' cellpadding='8'>");
        out.println("<tr><th>Field</th><th>Value</th></tr>");
        out.println("<tr><td>Username</td><td>" + username + "</td></tr>");
        out.println("<tr><td>College</td><td>" + college + "</td></tr>");
        out.println("<tr><td>City</td><td>" + city + "</td></tr>");
        out.println("<tr><td>Title</td><td>" + title + "</td></tr>");
        out.println("<tr><td>Gender</td><td>" + gender + "</td></tr>");
        out.println("</table>");
        out.println("<br><a href='index.html'>Back</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
