<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actPos" value="${ForwardConst.ACT_POS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commDes" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>ツイート 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${post.user.name}" /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${post.content}" /></pre></td>
                </tr>
                <tr>
                    <th>投稿日時</th>
                    <fmt:parseDate value="${post.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>

            </tbody>
        </table>

        <c:if test="${sessionScope.login_user.id == post.user.id}">
            <p>
                <a href="<c:url value='?action=${actPos}&command=${commEdt}&id=${post.id}' />">このツイートを編集する</a>
            </p>
<!--              <p>
                <a href="<c:url value='?action=${actPos}&command=${commDes}&id=${post.id}' />">このツイートを削除する</a>
            </p> -->
        </c:if>

        <p>
            <a href="<c:url value='?action=${actPos}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>