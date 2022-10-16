<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actPos" value="${ForwardConst.ACT_POS.getValue()}" />
<c:set var="actFol" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commDes" value="${ForwardConst.CMD_DESTROY.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><c:out value="${user.name}" /> さんのフォロー一覧</h2>

        <table id="user_list">
            <tbody>
                <tr>
                    <th class="user_name">ユーザーネーム</th>
                    <th class="user_action">操作</th>

                </tr>
                <c:forEach var="user" items="${users}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="user_name"><a href="<c:url value='?action=${actPos}&command=${commIdx}&id=${user.id}' />">
                        <c:out value="${user.name}" /></a></td>
                        <td class="user_action"><a href="<c:url value='?action=${actPos}&command=${commShow}&id=${user.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:param>
</c:import>