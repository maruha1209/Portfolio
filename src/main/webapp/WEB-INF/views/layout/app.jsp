<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actUse" value="${ForwardConst.ACT_USE.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="Tweetシステム" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actTop}&command=${commIdx}' />">Tweetシステム</a></h1>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    <a href="<c:url value='?action=${actUse}&command=${commShow}' />">アカウント詳細</a>&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_user != null}">
                <div id="user_name">
                    <c:out value="${sessionScope.login_user.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Haruma Takesue.</div>
    </div>
</body>
</html>