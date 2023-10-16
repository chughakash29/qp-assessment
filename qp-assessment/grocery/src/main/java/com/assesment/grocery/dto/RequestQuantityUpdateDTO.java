package com.assesment.grocery.dto;

import com.assesment.grocery.entity.ItemQuantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RequestQuantityUpdateDTO {
    private List<ItemQuantity> itemQuantityList;
}
