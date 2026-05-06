<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Q45 Display Teacher Records</title>
</head>
<body>
    <h2>Question 45 - Teacher Records</h2>
    <p>devName: Ritesh Dhekane</p>
<%
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;
boolean found = false;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");

    ps = con.prepareStatement("select * from Teacher");
    rs = ps.executeQuery();
%>
    <table border="1" cellpadding="8">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Subject</th>
        </tr>
<%
    while (rs.next()) {
        found = true;
%>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("subject") %></td>
        </tr>
<%
    }
%>
    </table>
<%
    if (!found) {
%>
    <p>No teacher records found.</p>
<%
    }
} catch (Exception e) {
%>
    <p>Error: <%= e.getMessage() %></p>
<%
} finally {
    if (rs != null) try { rs.close(); } catch (Exception e) { }
    if (ps != null) try { ps.close(); } catch (Exception e) { }
    if (con != null) try { con.close(); } catch (Exception e) { }
}
%>
    <br><a href="index.html">Back</a>
</body>
</html>
