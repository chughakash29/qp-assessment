package com.assesment.grocery.controller;

import com.assesment.grocery.dto.GroceryItemsDTO;
import com.assesment.grocery.dto.RequestQuantityUpdateDTO;
import com.assesment.grocery.entity.Grocery;
import com.assesment.grocery.service.GroceryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/grocery")
@EnableMethodSecurity
public class GroceryController {

    private final GroceryService groceryService;

    public GroceryController(GroceryService groceryService){
        this.groceryService = groceryService;
    }

    @GetMapping(value = "/getAvailableItems")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public List<Grocery> getGroceryDetails(){
        return groceryService.getGroceryDetails();
    }

    @PostMapping(value="/addItems")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addGroceryItem(@RequestBody GroceryItemsDTO groceries){
        if(checkGroceryDto(groceries)) {
            return groceryService.addGroceryItems(groceries);
        }
        return ResponseEntity.badRequest().body("Invalid Data");


    }

    @PutMapping(value="/updateItems")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateGroceryItems(@RequestBody GroceryItemsDTO groceries){
        if(checkGroceryDto(groceries)) {
            return groceryService.updateGroceryItems(groceries);
        }
        return ResponseEntity.badRequest().body("Invalid Data");
    }

    @PutMapping(value="/updateQuantity")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateQuantity(@RequestBody RequestQuantityUpdateDTO requestQuantityUpdateDTO){
        if(checkItemQuantityDto(requestQuantityUpdateDTO)) {
            return groceryService.updateGroceryQuantity(requestQuantityUpdateDTO);
        }
        return ResponseEntity.badRequest().body("Invalid Data");
    }

    @DeleteMapping(value="/deleteItems")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteItems(@RequestBody GroceryItemsDTO groceries){
        if(checkGroceryDto(groceries)) {
            return groceryService.deleteItems(groceries);
        }
        return ResponseEntity.badRequest().body("Invalid Data");
    }

    private Boolean checkGroceryDto(GroceryItemsDTO groceryItemsDTO){
        if(Objects.isNull(groceryItemsDTO) || Objects.isNull(groceryItemsDTO.getGroceryList()) || groceryItemsDTO.getGroceryList().isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean checkItemQuantityDto(RequestQuantityUpdateDTO requestQuantityUpdateDTO){
        if(Objects.isNull(requestQuantityUpdateDTO) || Objects.isNull(requestQuantityUpdateDTO.getItemQuantityList()) || requestQuantityUpdateDTO.getItemQuantityList().isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
