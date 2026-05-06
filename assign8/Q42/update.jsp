<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Q42 Updated Employee Record</title>
</head>
<body>
    <h2>Question 42 - Updated Employee Record</h2>
    <p>devName: Ritesh Dhekane</p>
<%
Connection con = null;
PreparedStatement psUpdate = null;
PreparedStatement psSelect = null;
ResultSet rs = null;

try {
    int empno = Integer.parseInt(request.getParameter("empno"));
    double salary = Double.parseDouble(request.getParameter("salary"));

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");

    psUpdate = con.prepareStatement("update Employee set salary=? where empno=?");
    psUpdate.setDouble(1, salary);
    psUpdate.setInt(2, empno);

    int updated = psUpdate.executeUpdate();

    if (updated > 0) {
        psSelect = con.prepareStatement("select * from Employee where empno=?");
        psSelect.setInt(1, empno);
        rs = psSelect.executeQuery();

        if (rs.next()) {
%>
    <p>Record updated successfully.</p>
    <table border="1" cellpadding="8">
        <tr>
            <th>Emp No</th>
            <th>Employee Name</th>
            <th>Salary</th>
        </tr>
        <tr>
            <td><%= rs.getInt("empno") %></td>
            <td><%= rs.getString("ename") %></td>
            <td><%= rs.getDouble("salary") %></td>
        </tr>
    </table>
<%
        }
    } else {
%>
    <p>No employee found for empno <%= empno %>.</p>
<%
    }
} catch (Exception e) {
%>
    <p>Error: <%= e.getMessage() %></p>
<%
} finally {
    if (rs != null) try { rs.close(); } catch (Exception e) { }
    if (psSelect != null) try { psSelect.close(); } catch (Exception e) { }
    if (psUpdate != null) try { psUpdate.close(); } catch (Exception e) { }
    if (con != null) try { con.close(); } catch (Exception e) { }
}
%>
    <br><a href="index.html">Back</a>
</body>
</html>
