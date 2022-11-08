<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_USE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>${user.name} さんのアカウント情報 編集ページ</h2>
        <p>（パスワードは変更する場合のみ入力してください）</p>
        <form method="POST"
            action="<c:url value='?action=${action}&command=${commUpd}' />">
            <label for="${AttributeConst.USE_NAME.getValue()}">氏名</label><br />
            <input type="text" name="${AttributeConst.USE_NAME.getValue()}" id="${AttributeConst.USE_NAME.getValue()}" value="${user.name}" />
            <br /><br />

            <label for="${AttributeConst.USE_PASS.getValue()}">パスワード</label><br />
            <input type="password" name="${AttributeConst.USE_PASS.getValue()}" id="${AttributeConst.USE_PASS.getValue()}" />
            <br /><br />

            <br /><br />
            <input type="hidden" name="${AttributeConst.USE_ID.getValue()}" value="${user.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">投稿</button>
        </form>

        <p>
            <a href="#" onclick="confirmDestroy();">このユーザー情報を削除する</a>
        </p>
        <form method="POST"
            action="<c:url value='?action=${action}&command=${commDel}' />">
            <input type="hidden" name="${AttributeConst.USE_ID.getValue()}" value="${user.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if (confirm("本当に削除してよろしいですか？")) {
                    document.forms[1].submit();
                }
            }
        </script>

        <p>
            <a href="<c:url value='?action=${action}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>