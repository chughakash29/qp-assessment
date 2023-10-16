package com.assesment.grocery.controller;

import com.assesment.grocery.dto.GroceryItemsDTO;
import com.assesment.grocery.dto.RequestQuantityUpdateDTO;
import com.assesment.grocery.entity.Grocery;
import com.assesment.grocery.entity.ItemQuantity;
import com.assesment.grocery.service.GroceryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class GroceryControllerTest {
    @Mock
    private GroceryService groceryService;

    @InjectMocks
    private GroceryController groceryController;

    @Test
    public void shouldGetGroceryDetails(){
        Mockito.when(groceryService.getGroceryDetails()).thenReturn(Arrays.asList(Grocery.builder().groceryId(123L).price(100.0).groceryName("item1").build()));
        List<Grocery> groceries = groceryController.getGroceryDetails();
        List<Grocery> expectedGroceries = Arrays.asList(Grocery.builder().groceryId(123L).price(100.0).groceryName("item1").build());
        Assert.assertEquals(groceries.size(),expectedGroceries.size());
    }

    @Test
    public void shouldAddItemsSuccessFully(){
        Mockito.when(groceryService.addGroceryItems(any(GroceryItemsDTO.class))).thenReturn(ResponseEntity.ok().body("Item added successfully"));
        ResponseEntity<String> actualResponse = groceryController.addGroceryItem(GroceryItemsDTO.builder().groceryList(Arrays.asList(Grocery.builder().groceryId(123L).price(100.0).groceryName("item1").build())).build());
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Item added successfully");
        Assert.assertEquals(actualResponse.getBody(),expectedResponse.getBody());
    }

    @Test
    public void shouldUpdateItemsSuccessfully(){
        Mockito.when(groceryService.updateGroceryItems(any(GroceryItemsDTO.class))).thenReturn(ResponseEntity.ok().body("Item updated successfully"));
        ResponseEntity<String> actualResponse = groceryController.updateGroceryItems(GroceryItemsDTO.builder().groceryList(Arrays.asList(Grocery.builder().groceryId(123L).price(100.0).groceryName("item1").build())).build());
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Item updated successfully");
        Assert.assertEquals(actualResponse.getBody(),expectedResponse.getBody());
    }

    @Test
    public void shouldDeleteItemsSuccessfully(){
        Mockito.when(groceryService.deleteItems(any(GroceryItemsDTO.class))).thenReturn(ResponseEntity.ok().body("Item deleted successfully"));
        ResponseEntity<String> actualResponse = groceryController.deleteItems(GroceryItemsDTO.builder().groceryList(Arrays.asList(Grocery.builder().groceryId(123L).price(100.0).groceryName("item1").build())).build());
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Item deleted successfully");
        Assert.assertEquals(actualResponse.getBody(),expectedResponse.getBody());
    }

    @Test
    public void shouldUpdateQuantitySuccessfully(){
        Mockito.when(groceryService.updateGroceryQuantity(any(RequestQuantityUpdateDTO.class))).thenReturn(ResponseEntity.ok().body("Item quantity updated successfully"));
        ResponseEntity<String> actualResponse = groceryController.updateQuantity(RequestQuantityUpdateDTO.builder().itemQuantityList(Arrays.asList(ItemQuantity.builder().quantity(100L).build())).build());
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Item quantity updated successfully");
        Assert.assertEquals(actualResponse.getBody(),expectedResponse.getBody());
    }
}
