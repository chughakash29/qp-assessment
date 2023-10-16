package com.assesment.grocery.controller;

import com.assesment.grocery.dto.OrderRequest;
import com.assesment.grocery.dto.RequestPlaceOrderDTO;
import com.assesment.grocery.dto.RequestQuantityUpdateDTO;
import com.assesment.grocery.entity.ItemQuantity;
import com.assesment.grocery.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void shouldPlaceOrderSuccessfully(){
        RequestPlaceOrderDTO requestPlaceOrderDTO = RequestPlaceOrderDTO.builder().orderRequest(Arrays.asList(OrderRequest.builder().groceryId(123L).quantity(10L).build())).build();
        Mockito.when(orderService.placeOrder(any(RequestPlaceOrderDTO.class))).thenReturn(ResponseEntity.ok().body("order placed successfully"));
        ResponseEntity<String> responseEntity = orderController.placeOrder(requestPlaceOrderDTO);
        ResponseEntity<String> expectedEntity = ResponseEntity.ok().body("order placed successfully");
        Assert.assertEquals(responseEntity.getBody(),expectedEntity.getBody());
    }
}
