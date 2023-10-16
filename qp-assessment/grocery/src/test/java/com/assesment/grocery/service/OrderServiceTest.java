package com.assesment.grocery.service;

import com.assesment.grocery.dto.OrderRequest;
import com.assesment.grocery.dto.RequestPlaceOrderDTO;
import com.assesment.grocery.entity.ItemQuantity;
import com.assesment.grocery.entity.OrderDetails;
import com.assesment.grocery.repository.ItemQuantityRepository;
import com.assesment.grocery.repository.OrderRepository;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ItemQuantityRepository itemQuantityRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void shouldPlaceOrderSuccessfully(){
        RequestPlaceOrderDTO requestPlaceOrderDTO= RequestPlaceOrderDTO.builder().userId(123L).orderRequest(Arrays.asList(OrderRequest.builder().groceryId(1L).quantity(10L).build())).build();
        when(orderRepository.save(any())).thenReturn(OrderDetails.builder().orderId(123L).build());
        when(itemQuantityRepository.getReferenceById(1L)).thenReturn(ItemQuantity.builder().quantity(100L).build());
        ResponseEntity<String> responseEntity = orderService.placeOrder(requestPlaceOrderDTO);
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok().body("OrderDetails Placed Successfully, OrderDetails Id:123");
        Assert.assertEquals(responseEntity.getBody(),expectedResponseEntity.getBody());
    }
}
