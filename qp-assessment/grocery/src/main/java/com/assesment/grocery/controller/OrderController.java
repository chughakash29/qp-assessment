package com.assesment.grocery.controller;

import com.assesment.grocery.dto.RequestPlaceOrderDTO;
import com.assesment.grocery.dto.RequestQuantityUpdateDTO;
import com.assesment.grocery.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/order")
@EnableMethodSecurity
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> placeOrder(@RequestBody RequestPlaceOrderDTO requestPlaceOrderDTO){
        if(checkPlaceOrderDto(requestPlaceOrderDTO)) {
            return orderService.placeOrder(requestPlaceOrderDTO);
        }
        return ResponseEntity.badRequest().body("Invalid Data");
    }

    private Boolean checkPlaceOrderDto(RequestPlaceOrderDTO requestPlaceOrderDTO){
        if(Objects.isNull(requestPlaceOrderDTO) || Objects.isNull(requestPlaceOrderDTO.getOrderRequest()) || requestPlaceOrderDTO.getOrderRequest().isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
