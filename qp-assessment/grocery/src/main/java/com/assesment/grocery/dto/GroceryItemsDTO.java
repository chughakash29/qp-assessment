package com.assesment.grocery.dto;

import com.assesment.grocery.entity.Grocery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemsDTO {
    List<Grocery> groceryList;
}
