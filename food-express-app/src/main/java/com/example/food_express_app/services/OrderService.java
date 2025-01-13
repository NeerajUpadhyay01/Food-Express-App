package com.example.food_express_app.services;

import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.User;
import com.example.food_express_app.entities.CartItem;
import com.example.food_express_app.repositories.OrderRepository;
import com.example.food_express_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    public Order placeOrder(Long userId, Order order) {
        List<CartItem> cartItems = cartService.findByUserId(userId);
        order.setCartItems(cartItems);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);
        order.setRestaurant(cartItems.get(0).getMenuItem().getRestaurant());
        order.setStatus(Order.Status.PENDING);
        order.setAddress(user.getAddress());
        order.setTotalAmount(cartItems.stream()
                .map(CartItem::getPrice)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        Order placedOrder = orderRepository.save(order);

        cartItems.forEach(cartItem -> {
            cartItem.setOrder(placedOrder);
            cartService.updateCartItem(cartItem);
        });

        cartService.clearCart(userId);
        return placedOrder;
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // public Order updateOrder(Long orderId, Order orderDetails) {
    // return orderRepository.findById(orderId).map(order -> {
    // order.setUser(orderDetails.getUser());
    // order.setRestaurant(orderDetails.getRestaurant());
    // order.setCreatedAt(orderDetails.getCreatedAt());

    // // Update cart items
    // order.getCartItems().clear();
    // order.getCartItems().addAll(orderDetails.getCartItems());

    // return orderRepository.save(order);
    // }).orElseThrow(() -> new RuntimeException("Order not found"));
    // }

    public Order updateOrderStatus(Long orderId, Order.Status status) {
        return orderRepository.findById(orderId).map(order -> {
            order.setStatus(status);
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void cancelOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}