<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

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



<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="Tweet Application" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actTop}&command=${commIdx}' />">Tweet Application</a></h1>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    <a href="<c:url value='?action=${actFol}&command=${commFridx}&id=${sessionScope.login_user.id}' />">フォロー</a>&nbsp;
                    <a href="<c:url value='?action=${actFol}&command=${commFeidx}&id=${sessionScope.login_user.id}' />">フォロワー</a>&nbsp;
                    <a href="<c:url value='?action=${actPos}&command=${commIdx}&id=${sessionScope.login_user.id}' />">ツイート機能</a>&nbsp;
                    <a href="<c:url value='?action=${actUse}&command=${commIdx}' />">ユーザー一覧</a>&nbsp;
                    <a href="<c:url value='?action=${actSch}&command=${commTop}' />">検索</a>&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_user != null}">
                <div id="user_name">
                    <c:if test="${sessionScope.login_user != null}">
                        <a href="<c:url value='?action=${actUse}&command=${commShow}' />">
                    <c:out value="${sessionScope.login_user.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;</a>&nbsp;
                </c:if>
                    <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Haruma Takesue.</div>
    </div>
</body>
</html>