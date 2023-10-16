package com.assesment.grocery.service;

import com.assesment.grocery.dto.GroceryItemsDTO;
import com.assesment.grocery.dto.RequestQuantityUpdateDTO;
import com.assesment.grocery.entity.Grocery;
import com.assesment.grocery.entity.ItemQuantity;
import com.assesment.grocery.repository.GroceryRepository;
import com.assesment.grocery.repository.ItemQuantityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryService {

    private final GroceryRepository groceryRepository;
    private final ItemQuantityRepository itemQuantityRepository;

    public GroceryService(GroceryRepository groceryRepository, ItemQuantityRepository itemQuantityRepository){
        this.groceryRepository = groceryRepository;
        this.itemQuantityRepository = itemQuantityRepository;
    }

    public List<Grocery> getGroceryDetails(){
        return groceryRepository.getGroceryDetails();
    }

    @Transactional
    public ResponseEntity<String> addGroceryItems(GroceryItemsDTO groceryItemsDTO){
        List<Grocery> groceries = new ArrayList<>();
        groceries.addAll(groceryItemsDTO.getGroceryList());
        List<Grocery> groceryToBeAdded = checkGroceryAlreadyPresent(groceries);
        if(!groceryToBeAdded.isEmpty()) {
            List<Grocery> savedGrocery = groceryRepository.saveAll(groceryToBeAdded);
            List<ItemQuantity> itemQuantities = new ArrayList<>();
            savedGrocery.forEach(grocery -> {
                itemQuantities.add(ItemQuantity.builder().grocery(grocery).quantity(0L).build());
            });
            itemQuantityRepository.saveAll(itemQuantities);
        }
        if(groceryToBeAdded.size()!=groceryItemsDTO.getGroceryList().size()){
            return ResponseEntity.ok().body(groceryToBeAdded.size()+ " grocery item added and rest are already present");
        }
        return ResponseEntity.ok().body(groceryToBeAdded.size()+ " grocery item added");
    }

    @Transactional
    public ResponseEntity<String> updateGroceryItems(GroceryItemsDTO groceryItemsDTO){
        List<Long> itemNotPresent = new ArrayList<>();
        groceryItemsDTO.getGroceryList().forEach(grocery -> {
            if(groceryRepository.findById(grocery.getGroceryId()).isPresent()) {
                Grocery prevData = groceryRepository.findById(grocery.getGroceryId()).get();
                Grocery newData = prevData.toBuilder().groceryName(grocery.getGroceryName()).price(grocery.getPrice()).build();
                groceryRepository.save(newData);
            }else{
                itemNotPresent.add(grocery.getGroceryId());
            }
        });
        if(itemNotPresent.isEmpty()) {
            return ResponseEntity.ok().body(groceryItemsDTO.getGroceryList().size() + " grocery item updated");
        }
        return ResponseEntity.ok().body(groceryItemsDTO.getGroceryList().size() - itemNotPresent.size() + " grocery item updated,items not present"+ itemNotPresent);

    }

    @Transactional
    public ResponseEntity<String> updateGroceryQuantity(RequestQuantityUpdateDTO requestQuantityUpdateDTO){
        List<Long> itemNotPresent = new ArrayList<>();
        requestQuantityUpdateDTO.getItemQuantityList().forEach(itemQuantity -> {
            if(itemQuantityRepository.findById(itemQuantity.getId()).isPresent()) {
                ItemQuantity oldItemQuantity = itemQuantityRepository.findById(itemQuantity.getId()).get();
                ItemQuantity newItemQuantity = oldItemQuantity.toBuilder().quantity(oldItemQuantity.getQuantity() + itemQuantity.getQuantity()).build();
                itemQuantityRepository.save(newItemQuantity);
            }else{
                itemNotPresent.add(itemQuantity.getId());
            }
        });
        if(itemNotPresent.isEmpty()) {
            return ResponseEntity.ok().body(requestQuantityUpdateDTO.getItemQuantityList().size() + " grocery item quantity updated");
        }
        return ResponseEntity.ok().body(requestQuantityUpdateDTO.getItemQuantityList().size() - itemNotPresent.size() + " grocery item quantity updated, items not present"+ itemNotPresent);
    }

    @Transactional
    public ResponseEntity<String> deleteItems(GroceryItemsDTO groceryItemsDTO){
        groceryItemsDTO.getGroceryList().forEach(grocery -> {
            groceryRepository.deleteById(grocery.getGroceryId());
        });
        return ResponseEntity.ok().body(groceryItemsDTO.getGroceryList().size()+ " grocery item deleted");
    }

    private List<Grocery> checkGroceryAlreadyPresent(List<Grocery> groceries){
        List<String> groceriesName = groceries.stream().map(Grocery::getGroceryName).collect(Collectors.toList());
        List<Grocery> groceryList = groceryRepository.getGroceryByName(groceriesName);
        groceries.removeAll(groceryList);
        return groceries;
    }

}
