<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Q43 Insert Student Record</title>
</head>
<body>
    <h2>Question 43 - Student Record Insert</h2>
    <p>devName: Ritesh Dhekane</p>
<%
Connection con = null;
PreparedStatement ps = null;

try {
    int rno = Integer.parseInt(request.getParameter("rno"));
    String sname = request.getParameter("sname");
    double percentage = Double.parseDouble(request.getParameter("percentage"));

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");

    ps = con.prepareStatement("insert into Student (rno, sname, percentage) values (?, ?, ?)");
    ps.setInt(1, rno);
    ps.setString(2, sname);
    ps.setDouble(3, percentage);

    int result = ps.executeUpdate();

    if (result > 0) {
%>
    <p>Student record inserted successfully.</p>
    <table border="1" cellpadding="8">
        <tr>
            <th>Rno</th>
            <th>SName</th>
            <th>Percentage</th>
        </tr>
        <tr>
            <td><%= rno %></td>
            <td><%= sname %></td>
            <td><%= percentage %></td>
        </tr>
    </table>
<%
    } else {
%>
    <p>Record not inserted.</p>
<%
    }
} catch (Exception e) {
%>
    <p>Error: <%= e.getMessage() %></p>
<%
} finally {
    if (ps != null) try { ps.close(); } catch (Exception e) { }
    if (con != null) try { con.close(); } catch (Exception e) { }
}
%>
    <br><a href="index.html">Back</a>
</body>
</html>
