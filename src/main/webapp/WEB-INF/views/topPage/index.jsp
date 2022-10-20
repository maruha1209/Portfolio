<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actUse" value="${ForwardConst.ACT_USE.getValue()}" />
<c:set var="actPos" value="${ForwardConst.ACT_POS.getValue()}" />
<c:set var="actFol" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="actSch" value="${ForwardConst.ACT_SCH.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commFridx" value="${ForwardConst.CMD_FOLLOWER_INDEX.getValue()}" />
<c:set var="commFeidx" value="${ForwardConst.CMD_FOLLOWEE_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commTop" value="${ForwardConst.CMD_TOP.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>${sessionScope.login_user.name} さんのタイムライン</h2>

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