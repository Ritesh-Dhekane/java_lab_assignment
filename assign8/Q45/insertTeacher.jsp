<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Q45 Insert Teacher</title>
</head>
<body>
    <h2>Question 45 - Teacher Record Insert</h2>
    <p>devName: Ritesh Dhekane</p>
<%
Connection con = null;
PreparedStatement ps = null;

try {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String subject = request.getParameter("subject");

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");

    ps = con.prepareStatement("insert into Teacher (id, name, subject) values (?, ?, ?)");
    ps.setInt(1, id);
    ps.setString(2, name);
    ps.setString(3, subject);

    int result = ps.executeUpdate();

    if (result > 0) {
%>
    <p>Teacher record inserted successfully.</p>
    <table border="1" cellpadding="8">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Subject</th>
        </tr>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= subject %></td>
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
    <br><a href="displayTeacher.jsp">Display Teacher Records</a>
</body>
</html>
