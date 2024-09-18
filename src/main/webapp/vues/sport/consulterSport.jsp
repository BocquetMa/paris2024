<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sio.paris2024.model.Sport" %>
<%@ page import="sio.paris2024.model.Epreuve" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PARIS 2024</title>
</head>
<body>
    <%
        Sport s = (Sport) request.getAttribute("pSport");
    %>

    <table>
        <tr>
            <td>
                <% if (s != null && s.getLesEpreuves() != null && !s.getLesEpreuves().isEmpty()) { %>
                    <% for (Epreuve ep : s.getLesEpreuves()) { %>
                        <%= ep.getId() %> <%= ep.getNom() %><br>
                    <% } %>
                <% } else { %>
                    Aucune épreuve disponible pour ce site.
                <% } %>
            </td>
        </tr>
    </table>
</body>
</html>
