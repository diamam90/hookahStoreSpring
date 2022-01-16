<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="page-numbers">
    <c:if test="${pageCount==1}">
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers first">1</a>
        </li>
    </c:if>



    <c:if test="${page==1 && pageCount-1==page}">
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page==1 && pageCount-2==page}">
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page==1 && pageCount-2>page}">
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <span class="page-numbers dots-next">...</span>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>



    <c:if test="${page==2 && pageCount==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products&page=${page}" class="page-numbers current">${page}</a>
        </li>
    </c:if>
    <c:if test="${page==2 && pageCount-1==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page==2 && pageCount-2==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page==2 && pageCount-2>page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <span class="page-numbers dots-next">...</span>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>


    <c:if test="${page==3 && pageCount==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
    </c:if>
    <c:if test="${page==3 && pageCount-1==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>

        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page==3 && pageCount-2==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page==3 && pageCount-2>page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <span class="page-numbers dots-next">...</span>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>

    <c:if test="${page>3 && pageCount==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <span class="page-numbers dots-prev">...</span>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
    </c:if>
    <c:if test="${page>3 && pageCount-1==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <span class="page-numbers dots-prev">...</span>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>

        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page>3 && pageCount-2==page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <span class="page-numbers dots-prev">...</span>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>
    <c:if test="${page>3 && pageCount-2>page}">
        <li>
            <a href="/products?page=1" class="page-numbers first">1</a>
        </li>
        <li>
            <span class="page-numbers dots-prev">...</span>
        </li>
        <li>
            <a href="/products?page=${page-1}" class="page-numbers prev">${page-1}</a>
        </li>
        <li>
            <a href="/products?page=${page}" class="page-numbers current">${page}</a>
        </li>
        <li>
            <a href="/products?page=${page+1}" class="page-numbers next">${page+1}</a>
        </li>
        <li>
            <span class="page-numbers dots-next">...</span>
        </li>
        <li>
            <a href="/products?page=${pageCount}" class="page-numbers last">${pageCount}</a>
        </li>
    </c:if>

</ul>