<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actUse" value="${ForwardConst.ACT_USE.getValue()}" />
<c:set var="actPos" value="${ForwardConst.ACT_POS.getValue()}" />
<c:set var="actSch" value="${ForwardConst.ACT_SCH.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
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

        <table id="post_list">
            <tbody>
                <tr>
                    <th class="post_name">ユーザーネーム</th>
                    <th class="post_date">投稿日</th>
                    <th class="post_content">本文</th>
                    <th class="post_action">操作</th>


                </tr>
                <c:forEach var="post" items="${posts}" varStatus="status">
                    <fmt:parseDate value="${post.createdAt}" pattern="yyyy-MM-dd" var="postDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="post_name"><a href="<c:url value='?action=${actPos}&command=${commIdx}&id=${post.user.id}' />">
                        <c:out value="${post.user.name}" /></a></td>
                        <td class="post_date"><fmt:formatDate value='${postDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="post_content"><c:out value="${post.content}" /></td>
                        <td class="post_action"><a href="<c:url value='?action=${actPos}&command=${commShow}&id=${post.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:param>
</c:import>