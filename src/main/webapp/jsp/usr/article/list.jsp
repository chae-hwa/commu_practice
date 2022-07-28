<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ll.exam.article.dto.ArticleDto" %>

<%
List<ArticleDto> articles = (List<ArticleDto>)request.getAttribute("articles");
%>

<script src="https://cdn.tailwindcss.com"></script>


<!-- 구획을 나눈다-->
<section>
    <!-- 너비를 반응형으로 잃지 않는다.-->
    <div class="container px-3 mx-auto">
        <h1 class="font-bold test-lg">게시물 리스트</h1>
        <ul class="mt-5">
            <% for ( ArticleDto article : articles ) { %>
            <li class="flex">
                <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/detail/free/<%=article.getId()%>"><%=article.getId()%></a>
                <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/<%=article.getId()%>"><%=article.getTitle()%></a>
                <a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" class="mr-2 hover:underline hover:text-[red]" href="/usr/article/delete/free/<%=article.getId()%>">삭제</a>
                <a class="w-[100px] hover:underline hover:text-[red]" href="/usr/article/modify/free/<%=article.getId()%>">수정</a>
            </li>
            <% } %>
        </ul>
    </div>
</section>