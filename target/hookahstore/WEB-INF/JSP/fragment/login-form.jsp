<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <div class="breadcrumbs theme-clearfix">
        <div class="container">
            <ul class="breadcrumb">
                <li>
                    <a id="home" href="/products">Home</a>
                    <span class="go-page"></span>
                </li>

                <li class="active">
                    <span>My Account</span>
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
                                <h2 class="entry-title">My Account</h2>
                            </header>

                            <div class="entry-content">
                                <div class="woocommerce">
                                    <div class="col2-set row" id="customer_login">
                                        <div class="col-lg-6">
                                            <h2>Login</h2>

                                            <form method="post" class="login" action="/login">
                                                <p class="form-row form-row-wide">
                                                    <label for="username">
                                                        Username or email address <span class="required">*</span>
                                                    </label>

                                                    <input type="text" class="input-text" name="username" id="username" />
                                                </p>

                                                <p class="form-row form-row-wide">
                                                    <label for="password">
                                                        Password <span class="required">*</span>
                                                    </label>

                                                    <input class="input-text" type="password" name="password" id="password" />
                                                </p>


                                                <p class="form-row">
                                                    <input type="submit" class="button" name="login" value="Login" />
                                                </p>


                                            </form>
                                        </div>

                                        <div class="col-lg-6">
                                            <h2>Register</h2>

                                            <form method="post" class="register" action="/register">
                                                <p class="form-row form-row-wide">
                                                    <label for="reg_email">
                                                        Email address <span class="required">*</span>
                                                    </label>

                                                    <input type="email" class="input-text" name="email" id="reg_email" value="" />
                                                </p>

                                                <p class="form-row form-row-wide">
                                                    <label for="reg_password">
                                                        Password <span class="required">*</span>
                                                    </label>

                                                    <input type="password" class="input-text" name="password" id="reg_password" />
                                                </p>

                                                <!-- Spam Trap -->
                                                <div class="spam-trap" style="left: -999em;">
                                                    <label for="trap">Anti-spam</label>
                                                    <input type="text" name="email_2" id="trap" tabindex="-1" />
                                                </div>

                                                <p class="form-row">
                                                    <input type="submit" class="button" name="register" value="Register" />
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
