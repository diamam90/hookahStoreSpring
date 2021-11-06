<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <jsp:include page="../fragment/product-list.jsp"/>
</div>
<div class="text-center loadMore">
    <a id="loadMore" class="btn btn-secondary">Загрузить еще</a>
</div>