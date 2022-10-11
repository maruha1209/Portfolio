<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>${sessionScope.login_user.name} さんのトップページ</h2>

        <p><a href="<c:url value='?action=${actPos}&command=${commNew}' />">投稿する</a></p>
    </c:param>
</c:import>