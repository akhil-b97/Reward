package com.rewards.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PriceDao {

    String month;
    List<Integer> prices;
}
