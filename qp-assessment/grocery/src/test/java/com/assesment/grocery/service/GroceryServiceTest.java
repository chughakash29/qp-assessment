package com.assesment.grocery.service;

import com.assesment.grocery.dto.GroceryItemsDTO;
import com.assesment.grocery.dto.RequestQuantityUpdateDTO;
import com.assesment.grocery.entity.Grocery;
import com.assesment.grocery.entity.ItemQuantity;
import com.assesment.grocery.repository.GroceryRepository;
import com.assesment.grocery.repository.ItemQuantityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroceryServiceTest {
    @Mock
    private GroceryRepository groceryRepository;
    @Mock
    private ItemQuantityRepository itemQuantityRepository;
    @InjectMocks
    private GroceryService groceryService;

    @Test
    public void getGroceryDetailsSuccessFully(){
        when(groceryRepository.getGroceryDetails()).thenReturn(Arrays.asList(Grocery.builder().groceryName("item1").price(100.0).groceryId(1L).build()));
        List<Grocery> groceryList = groceryService.getGroceryDetails();
        List<Grocery> groceries = Arrays.asList(Grocery.builder().groceryName("item1").price(100.0).groceryId(1L).build());
        Assert.assertEquals(groceryList.size(),groceries.size());
    }

    @Test
    public void addGroceryItemsSuccessFully(){
        when(groceryRepository.saveAll(any())).thenReturn(Arrays.asList(Grocery.builder().groceryName("item1").price(100.0).groceryId(1L).build()));
        ResponseEntity<String> responseEntity = groceryService.addGroceryItems(GroceryItemsDTO.builder().groceryList(Arrays.asList(Grocery.builder().groceryName("item1").price(100.0).build())).build());
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok().body("1 grocery item added");
        Assert.assertEquals(responseEntity.getBody(),expectedResponseEntity.getBody());
    }

    @Test
    public void updateGroceryItemsSuccessFully(){
        when(groceryRepository.save(any())).thenReturn(Grocery.builder().groceryName("item1").price(100.0).groceryId(1L).build());
        when(groceryRepository.findById(123L)).thenReturn(Optional.of(Grocery.builder().groceryName("item1").price(100.0).groceryId(1L).build()));
        ResponseEntity<String> responseEntity = groceryService.updateGroceryItems(GroceryItemsDTO.builder().groceryList(Arrays.asList(Grocery.builder().groceryId(123L).groceryName("item1").price(100.0).build())).build());
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok().body("1 grocery item updated");
        Assert.assertEquals(responseEntity.getBody(),expectedResponseEntity.getBody());
    }

    @Test
    public void deleteGroceryItemsSuccessFully(){
        when(groceryRepository.findById(123L)).thenReturn(Optional.of(Grocery.builder().groceryName("item1").price(100.0).groceryId(1L).build()));
        ResponseEntity<String> responseEntity = groceryService.deleteItems(GroceryItemsDTO.builder().groceryList(Arrays.asList(Grocery.builder().groceryId(123L).groceryName("item1").price(100.0).build())).build());
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok().body("1 grocery item deleted");
        Assert.assertEquals(responseEntity.getBody(),expectedResponseEntity.getBody());
    }

    @Test
    public void updateGroceryItemsQuantitySuccessFully(){
        when(itemQuantityRepository.save(any())).thenReturn(ItemQuantity.builder().id(1L).quantity(100L).build());
        when(itemQuantityRepository.findById(1L)).thenReturn(Optional.of(ItemQuantity.builder().id(1L).quantity(10L).build()));
        ResponseEntity<String> responseEntity = groceryService.updateGroceryQuantity(RequestQuantityUpdateDTO.builder().itemQuantityList(Arrays.asList(ItemQuantity.builder().quantity(100L).id(1L).grocery(Grocery.builder().groceryId(1L).build()).build())).build());
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok().body("1 grocery item quantity updated");
        Assert.assertEquals(responseEntity.getBody(),expectedResponseEntity.getBody());
    }
}
