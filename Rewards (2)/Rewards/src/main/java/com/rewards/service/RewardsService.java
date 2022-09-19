package com.rewards.service;

import com.rewards.dao.PriceDao;
import com.rewards.repository.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsService {

    @Autowired
    RewardsRepository rewardsRepository;

    public List<PriceDao> getPriceList(String customerId){
        return rewardsRepository.findByCustomer(customerId);
    }

}
