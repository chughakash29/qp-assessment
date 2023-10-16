package com.assesment.grocery.service;

import com.assesment.grocery.dto.OrderRequest;
import com.assesment.grocery.dto.RequestPlaceOrderDTO;
import com.assesment.grocery.entity.ItemQuantity;
import com.assesment.grocery.entity.OrderDetails;
import com.assesment.grocery.entity.OrderItemDetails;
import com.assesment.grocery.repository.ItemQuantityRepository;
import com.assesment.grocery.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final ItemQuantityRepository itemQuantityRepository;

    public OrderService(OrderRepository orderRepository, ItemQuantityRepository itemQuantityRepository){
        this.orderRepository = orderRepository;
        this.itemQuantityRepository = itemQuantityRepository;
    }

    @Transactional
    public ResponseEntity<String> placeOrder(RequestPlaceOrderDTO requestPlaceOrderDTO){
        List<OrderRequest> orderRequestList = requestPlaceOrderDTO.getOrderRequest();
        List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();
        orderRequestList.forEach(orderRequest -> placeOrder(orderRequest,orderItemDetailsList));
        if(orderItemDetailsList.isEmpty()) {
            return ResponseEntity.ok().body("Items are out of stock" + requestPlaceOrderDTO.getOrderRequest());
        }
        OrderDetails orderDetails = OrderDetails.builder().orderItemDetailsList(orderItemDetailsList).orderPlaced(new Date()).userId(requestPlaceOrderDTO.getUserId()).build();
        OrderDetails orderDetailsPlaced = orderRepository.save(orderDetails);
        if(orderItemDetailsList.size()!=requestPlaceOrderDTO.getOrderRequest().size()) {
            return ResponseEntity.ok().body("OrderDetails Placed Successfully, OrderDetails Id:"+ orderDetailsPlaced.getOrderId()+" but few items are out of stock");
        }
        return ResponseEntity.ok().body("OrderDetails Placed Successfully, OrderDetails Id:"+ orderDetailsPlaced.getOrderId());
    }

    private List<OrderItemDetails> placeOrder(OrderRequest orderRequest,List<OrderItemDetails> orderItemDetails){
        if(checkWhetherQuantityAvailable(orderRequest.getGroceryId(),orderRequest.getQuantity())){
            orderItemDetails.add(OrderItemDetails.builder().groceryId(orderRequest.getGroceryId()).quantity(orderRequest.getQuantity()).build());
        }
        return orderItemDetails;
    }

    private Boolean checkWhetherQuantityAvailable(Long groceryId, Long quantity){
        ItemQuantity itemQuantity = itemQuantityRepository.getReferenceById(groceryId);
        if(itemQuantity.getQuantity() - quantity >=0){
            itemQuantityRepository.save(itemQuantity.toBuilder().quantity(itemQuantity.getQuantity() - quantity).build());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
