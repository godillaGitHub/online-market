package ru.geekbrains.online.market.core.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.online.market.api.OrderDto;
import ru.geekbrains.online.market.core.converters.OrderConverter;
import ru.geekbrains.online.market.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username /*, @RequestBody OrderData orderData */) {
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getUserOrders(
            @Parameter(description = "Имя текущего пользователя", required = true)
            @RequestHeader String username
    ) {
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
