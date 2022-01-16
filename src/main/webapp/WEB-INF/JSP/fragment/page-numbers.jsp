<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="page-numbers">
    <c:if test="${page.number==0}">
        <li>
            <a class="page-numbers first">1</a>
        </li>
    </c:if>
    <c:if test="${page.number==1}">
        <li>
            <a class="page-numbers first">1</a>
        </li>
        <li>
            <a class="page-numbers current">${page.number+1}</a>
        </li>
    </c:if>
    <c:if test="${page.number==2}">
        <li>
            <a class="page-numbers first">1</a>
        </li>
        <li>
            <a class="page-numbers prev">${page.number}</a>
        </li>
        <li>
            <a class="page-numbers current">${page.number+1}</a>
        </li>
    </c:if>
    <c:if test="${page.number>2}">
        <li>
            <a class="page-numbers first">1</a>
        </li>
        <li>
            <span class="page-numbers dots-prev">...</span>
        </li>
        <li>
            <a  class="page-numbers prev">${page.number}</a>
        </li>
        <li>
            <a class="page-numbers current">${page.number+1}</a>
        </li>
    </c:if>


      <c:if test="${page.number+2==page.totalPages}">
        <li>
            <a class="page-numbers last">${page.totalPages}</a>
        </li>
    </c:if>
    <c:if test="${page.number+3==page.totalPages}">
        <li>
            <a class="page-numbers next">${page.number+2}</a>
        </li>
        <li>
            <a class="page-numbers last">${page.totalPages}</a>
        </li>
    </c:if>
    <c:if test="${page.number+3<page.totalPages}">
        <li>
            <a class="page-numbers next">${page.number+2}</a>
        </li>
        <li>
            <span class="page-numbers dots-next">...</span>
        </li>
        <li>
            <a class="page-numbers last">${page.totalPages}</a>
        </li>
    </c:if>

</ul>