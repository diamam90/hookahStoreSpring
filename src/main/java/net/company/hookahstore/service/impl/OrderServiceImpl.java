package net.company.hookahstore.service.impl;


import net.company.hookahstore.entity.Order;

import net.company.hookahstore.entity.OrderItem;
import net.company.hookahstore.entity.Product;
import net.company.hookahstore.exception.AccessDeniedException;
import net.company.hookahstore.form.ProductForm;

import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.model.ShoppingCartItem;
import net.company.hookahstore.repository.OrderItemRepository;
import net.company.hookahstore.repository.OrderRepository;
import net.company.hookahstore.service.OrderService;

import net.company.hookahstore.service.ProductService;
import net.company.hookahstore.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public ProductService productService;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderItemRepository orderItemRepository;

    private static final Logger LOGGER= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        Product product = productService.getProductById(productForm.getIdProduct());
        shoppingCart.addProduct(product,productForm.getCount());
    }

    @Override
    public void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(productForm.getIdProduct(),productForm.getCount());
    }

    @Override
    public void updateShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.updateProduct(productForm.getIdProduct(),productForm.getCount());
    }
    @Transactional
    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        List<ShoppingCartItem> items = (List<ShoppingCartItem>) shoppingCart.getItems();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (ShoppingCartItem item: items){
            OrderItem oi=new OrderItem();
            oi.setProduct(item.getProduct());
            oi.setCount(item.getCount());
            orderItemRepository.save(oi);
            orderItemList.add(oi);
        }
        Order order = new Order();
        order.setItemList(orderItemList);
        order.setIdAccount(currentAccount.getId());
        order.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public Order findOrderById(long id, CurrentAccount currentAccount) {
        Order order = orderRepository.findOrderById(id);
        if (order.getIdAccount()==currentAccount.getId()){
            return order;
        } else {
            throw new AccessDeniedException("Access denied account: " + currentAccount + "for order id : " + id);
        }
    }

    @Override
    public Page<Order> listMyOrders(CurrentAccount currentAccount, Pageable pageable) {
        return orderRepository.findAllByIdAccount(currentAccount.getId(),pageable);
    }

    @Override
    public int countMyOrders(CurrentAccount currentAccount) {
        return 0;
    }

//    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items){
//        List<Object[]> parameterList = new ArrayList<>();
//        for (ShoppingCartItem item: items){
//            parameterList.add(new Object[]{idOrder,item.getProduct().getId(),item.getCount()});
//        }
//        return parameterList;
//    }
    @Override
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
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
