<%@ tag pageEncoding="utf-8" %>
<div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body row">

                <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                    <div id="product_img" class="product-images loading" data-vertical="false">
                        <div class="product-images-container clearfix thumbnail-bottom">

                            <div class="thumbnail">
                                <img class="product-image" width="600px" src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=" alt="Product image">
                            </div>

                        </div>
                    </div>
                </div>

                <div class="product-summary col-lg-7 col-md-7 col-sm-12 col-xs-12">
                    <h1 itemprop="name" class="product_title">Наименование</h1>

                    <div class="reviews-content">
                        <div class="star"></div>
                        <a href="#reviews" class="woocommerce-review-link" rel="nofollow">
                            <span class="count">0</span> Review(s)
                        </a>
                    </div>

                    <div class="product-stock in-stock">
                        Товар:
                        <span>в наличии</span>
                    </div>

                    <div class="product_meta"></div>

                    <div itemprop="description" class="product-description">
                        <h2 class="quick-overview">Описание</h2>
                        <p>Description #1</p>
                    </div>

                    <div>
                        <p class="price">
                            <ins>
                                <span class="woocommerce-Price-amount amount">price<span class="woocommerce-Price-currencySymbol">&#8381</span>
                                </span>
                            </ins>
                        </p>
                    </div>

                    <div class="product-summary-bottom clearfix">
                        <form class="cart">
                            <div class="quantity">
                                <input type="number" class="count" value="1" min="1" max="10">
                            </div>
                            <button class="single_add_to_cart_button button alt">Добавить в корзину</button>
                        </form>
                    </div>

                    <div class="product_meta">
                        <p class="posted_in">
                            <h4 class="category">Apparel & Accessories</h4>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>