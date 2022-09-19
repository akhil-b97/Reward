package com.rewards.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RewardsDao {
    String month;
    int points;
    List<Integer> priceList;
}
