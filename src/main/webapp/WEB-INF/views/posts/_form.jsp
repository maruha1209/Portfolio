<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<%--  <fmt:parseDate value="${post.postDate}" pattern="yyyy-MM-dd" var="postDay" type="date" />
<label for="${AttributeConst.POS_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.POS_DATE.getValue()}" id="${AttributeConst.POS_DATE.getValue()}" value="<fmt:formatDate value='${postDay}' pattern='yyyy-MM-dd' />" />
<br /><br /> --%>

<label>ユーザーネーム:</label>
<c:out value="${sessionScope.login_user.name}" />
<br /><br />

<label for="${AttributeConst.POS_CONTENT.getValue()}">内容</label><br />
<textarea  name="${AttributeConst.POS_CONTENT.getValue()}" id="${AttributeConst.POS_CONTENT.getValue()}" rows="10" cols="50">${post.content}</textarea>
<br /><br />

<input type="hidden" name="${AttributeConst.POS_ID.getValue()}" value="${post.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>