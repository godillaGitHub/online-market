package ru.geekbrains.online.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.online.market.api.CartDto;
import ru.geekbrains.online.market.core.entities.Order;
import ru.geekbrains.online.market.core.entities.OrderItem;
import ru.geekbrains.online.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.online.market.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Order createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clear(username);
        return order;
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}
