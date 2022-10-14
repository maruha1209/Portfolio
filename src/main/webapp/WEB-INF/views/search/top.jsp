<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actSch" value="${ForwardConst.ACT_SCH.getValue()}" />
<c:set var="commSuse" value="${ForwardConst.CMD_SEARCH_USERS.getValue()}" />
<c:set var="commSpos" value="${ForwardConst.CMD_SEARCH_POSTS.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>検索ページ</h2>

        <form method="POST" action="<c:url value='?action=${actSch}&command=${commSuse}' />">
            <label for="search_users">ユーザー検索</label><br />
            <input type="text" name="search_users" id="search_users" value="${search_users}" />
            <button type="submit">検索</button>
            <br /><br />
        </form>

        <form method="POST" action="<c:url value='?action=${actSch}&command=${commSpos}' />">
            <label for="search_posts">ツイート検索</label><br />
            <input type="text" name="search_posts" id="search_posts" value="${search_posts}" />
            <button type="submit">検索</button>
            <br /><br />
        </form>

    </c:param>
</c:import>