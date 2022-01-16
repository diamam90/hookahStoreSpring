<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main" class="theme-clearfix" role="document">
    <div class="breadcrumbs theme-clearfix">
        <div class="container">
            <ul class="breadcrumb">
                <li>
                    <a id="home" href="/products">Home</a>
                    <span class="go-page"></span>
                </li>

                <li class="active">
                    <span>Create Account</span>
                </li>
            </ul>
        </div>
    </div>

    <div class="container">
        <div class="">
            <div id="contents" role="main" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="single post-29 page type-page status-publish hentry">
                    <div class="entry">
                        <div class="entry-content">
                            <header>
                                <h2 class="entry-title">Мой аккаунт</h2>
                            </header>

                            <div class="entry-content">
                                <div class="woocommerce">
                                    <div class="col2-set row" id="customer_login">
                                        <form method="post" class="register" action="/create-account">
                                            <h2>Регистрация</h2>
                                            <div class="col-lg-6">
                                                <p class="form-row form-row-wide">
                                                    <label for="regLogin">Логин<span class="required">*</span>
                                                    </label>
                                                    <input type="text" class="input-text" name="login" id="regLogin"/>
                                                </p>

                                                <p class="form-row form-row-wide">
                                                    <label for="regPassword">Пароль<span class="required">*</span>
                                                    </label>
                                                    <input class="input-text" type="password" name="password"
                                                           id="regPassword"/>
                                                </p>
                                                <p class="form-row form-row-wide">
                                                    <label for="email">Электронная почта<span class="required">*</span>
                                                    </label>
                                                    <input type="email" class="input-text" name="email" id="email"
                                                           value=""/>
                                                </p>
                                            </div>
                                            <div class="col-lg-6">
                                                <p class="form-row form-row-wide">
                                                    <label for="name">Имя<span class="required">*</span>
                                                    </label>
                                                    <input type="text" class="input-text" name="name" id="name" value=""/>
                                                </p>

                                                <p class="form-row form-row-wide">
                                                    <label for="lastName">Фамилия<span class="required">*</span>
                                                    </label>
                                                    <input type="text" class="input-text" name="lastName" id="lastName"
                                                           value=""/>
                                                </p>
                                                <p class="form-row form-row-wide">
                                                    <label for="phone">Телефон<span class="required">*</span>
                                                    </label>
                                                    <input type="phone" class="input-text" name="phone" id="phone"
                                                           value=""/>
                                                </p>
                                            </div>
                                            <!-- Spam Trap -->
                                            <div class="spam-trap" style="left: -999em;">
                                                <label for="trap">Anti-spam</label>
                                                <input type="text" name="email_2" id="trap" tabindex="-1"/>
                                            </div>

                                            <p class="form-row">
                                                <input type="submit" class="button" name="register" value="Register"/>
                                            </p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>