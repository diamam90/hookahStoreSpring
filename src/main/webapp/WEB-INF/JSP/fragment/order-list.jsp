<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


                                            <tbody>
                                            <c:forEach items="${orderList}" var="item">
                                            <tr class="order">
                                                <td class="order-number" data-title="Order">
                                                    <a href="/order/${item.id}">#${item.id}</a>
                                                </td>
                                                <td class="order-date" data-title="Date">
                                                    <time>${item.created}</time>
                                                </td>
                                                <td class="order-total" data-title="Total">
													<span class="woocommerce-Price-amount amount">
													</span>${item.totalCost}<span class="woocommerce-Price-currencySymbol"> ₽</span> за ${item.totalCount} товара
                                                </td>

                                                <td class="order-actions" data-title="&nbsp;">
                                                    <a href="/order/${item.id}" class="button view">View</a>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                            </tbody>