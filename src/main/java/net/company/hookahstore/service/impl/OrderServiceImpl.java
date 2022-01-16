package net.company.hookahstore.service.impl;
/*
import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Order;
import net.company.hookahstore.entity.OrderItem;
import net.company.hookahstore.entity.Product;
import net.company.hookahstore.exception.AccessDeniedException;
import net.company.hookahstore.exception.InternalServerErrorException;
import net.company.hookahstore.exception.ResourceNotFoundException;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.jdbc.JDBCUtils;
import net.company.hookahstore.jdbc.ResultSetHandler;
import net.company.hookahstore.jdbc.ResultSetHandlerFactory;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.model.ShoppingCartItem;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final DataSource datasource;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final ResultSetHandler<Product> productResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);
    private static final ResultSetHandler<Order> orderResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);
    private static final ResultSetHandler<List<OrderItem>> orderItemsResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER);
    private static final ResultSetHandler<List<Order>> orderListResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);
    private static final ResultSetHandler<Integer> countOrdersResultSetHandler =
            ResultSetHandlerFactory.getCountResultSetHandler();

    public OrderServiceImpl(DataSource dataSource) {
        this.datasource = dataSource;
    }


    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try (Connection c = datasource.getConnection()) {
            Product product = JDBCUtils.select(c, "select p.*, c.name as category, p.name as producer from product p, category c, producer pr " +
                    "where p.id_category=c.id and p.id_producer=pr.id and p.id=?", productResultSetHandler, productForm.getIdProduct());
            if (product == null) {
                throw new InternalServerErrorException("product not found by id " + productForm.getIdProduct());
            } else {
                shoppingCart.addProduct(product, productForm.getCount());
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException("can't execute query:" + e.getMessage());
        }
    }

    @Override
    public void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(productForm.getIdProduct(), productForm.getCount());
    }

    @Override
    public void updateShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        Long id = productForm.getIdProduct();
        Product product = shoppingCart.getProducts().get(id).getProduct();
        int countFromPage = productForm.getCount();
        int countFromCart = shoppingCart.getProducts().get(id).getCount();
        if (countFromCart < countFromPage) {
            shoppingCart.addProduct(product, countFromPage - countFromCart);
        } else if (countFromCart > countFromPage) {
            shoppingCart.removeProduct(id, countFromCart - countFromPage);
        }
    }

    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        if (shoppingCart==null){
            throw new InternalServerErrorException("Shopping cart is null or empty");
        }
        try (Connection c = datasource.getConnection()) {
            Order order = JDBCUtils.insert(c,"insert into \"order\" values (nextval('order_seq'),?,?)",orderResultSetHandler,
                    currentAccount.getId(),new Timestamp(System.currentTimeMillis()));
            JDBCUtils.insertBatch(c,"insert into order_item values(nextval('order_item_seq'),?,?,?)",
                    toOrderItemParameterList(order.getId(),shoppingCart.getItems()));
            c.commit();
            return order.getId();
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't make order for account: " + currentAccount + ",cause: " + e);
        }
    }

    @Override
    public Order findOrderById(long id, CurrentAccount currentAccount) {
        try (Connection c = datasource.getConnection()){
            Order order = JDBCUtils.select(c,"select * from \"order\" where id=?",orderResultSetHandler,id);
            if (order == null){
                throw new ResourceNotFoundException("Order not found by id: " + id);
            }
            if (!order.getIdAccount().equals(currentAccount.getId())){
                throw new AccessDeniedException("Account with id = " + currentAccount.getId() + " is not owner for order with id = " +id);
            }
            List<OrderItem> list = JDBCUtils.select(c,"select o.id as oid, o.id_order as id_order, o.id_product, o.count, p.* ,c.name as category, pr.name as producer" +
                    " from order_item o,product p,category c, producer pr where pr.id=p.id_producer and c.id=p.id_category and o.id_product= p.id and o.id_order=?", orderItemsResultSetHandler,id);
            order.setItemList(list);
            return order;
        } catch (SQLException e){
            throw new InternalServerErrorException("Can't execute query: " + e);
        }
    }

    @Override
    public List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit) {
        try (Connection c = datasource.getConnection()){
            int offset = (page-1)*limit;
            List<Order> orderList = JDBCUtils.select(c,"select * from \"order\" o where o.id_account=? order by o.created desc limit ? offset ?",orderListResultSetHandler,currentAccount.getId(),Constants.MAX_ORDERS_PER_PAGE,offset);
            for (Order order:orderList){
                List<OrderItem> orderItemList = JDBCUtils.select(c,"select o.id as oid, o.id_order,o.count,p.*,c.name as category,pr.name as producer " +
                        "from order_item o,product p,category c,producer pr where o.id_product=p.id and p.id_category=c.id and p.id_producer=pr.id and o.id_order=?",
                        orderItemsResultSetHandler,order.getId());
                order.setItemList(orderItemList);
            }
            return orderList;
        } catch (SQLException e){
            throw new InternalServerErrorException("Can't execute query: " + e);
        }
    }

    @Override
    public int countMyOrders(CurrentAccount currentAccount) {
        try (Connection c = datasource.getConnection()){
            return JDBCUtils.select(c,"select count(*) from \"order\" where id_account=?",countOrdersResultSetHandler,currentAccount.getId());
        } catch (SQLException e){
            throw new InternalServerErrorException("Can't execute query: " + e);
        }
    }

    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items){
        List<Object[]> parameterList = new ArrayList<>();
        for (ShoppingCartItem item: items){
            parameterList.add(new Object[]{idOrder,item.getProduct().getId(),item.getCount()});
        }
        return parameterList;
    }
    @Override
    public String selializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder cookieStr = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            cookieStr.append(item.getProduct().getId() + "-" + item.getCount() + "|");
        }
        return cookieStr.toString();
    }

    @Override
    public ShoppingCart deserializeShoppingCart(String cookieValue) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = cookieValue.split("\\|");
        for (int i = 0; i < items.length; i++) {
            String[] data = items[i].split("-");
            Long idProduct;
            Integer count;
            try {
                idProduct = Long.parseLong(data[0]);
                count = Integer.parseInt(data[1]);
                addProductToShoppingCart(new ProductForm(idProduct, count), shoppingCart);
            } catch (RuntimeException e) {
                LOGGER.error("can't add product to shopping cart during deserialization: item = " + items, e);
            }
        }
        return shoppingCart;
    }
}
*/