<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="tour-card">
    <img src="<%= request.getParameter("image") %>" alt="<%= request.getParameter("name") %>" />
    <h3><%= request.getParameter("name") %></h3>
    <p><%= request.getParameter("description") %></p>
    <a href="tourDetail.do?id=<%= request.getParameter("id") %>">자세히 보기</a>
</div>
