<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Q44 Scholarship Holder Students</title>
</head>
<body>
    <h2>Question 44 - Scholarship Holder Students</h2>
    <p>devName: Ritesh Dhekane</p>
<%
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;
boolean found = false;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");

    ps = con.prepareStatement("select * from Student where percentage >= 75");
    rs = ps.executeQuery();
%>
    <table border="1" cellpadding="8">
        <tr>
            <th>Rno</th>
            <th>SName</th>
            <th>Percentage</th>
        </tr>
<%
    while (rs.next()) {
        found = true;
%>
        <tr>
            <td><%= rs.getInt("rno") %></td>
            <td><%= rs.getString("sname") %></td>
            <td><%= rs.getDouble("percentage") %></td>
        </tr>
<%
    }
%>
    </table>
<%
    if (!found) {
%>
    <p>No scholarship holder students found.</p>
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
</body>
</html>
