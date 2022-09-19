package com.rewards.controller;

import com.rewards.dao.PriceDao;
import com.rewards.dao.RewardsDao;
import com.rewards.dao.RewardsRequest;
import com.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @PostMapping(path="/getRewards", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RewardsDao> getRewards(@RequestBody RewardsRequest rewardsRequest)
    {
        List<PriceDao> priceList =rewardsService.getPriceList(rewardsRequest.getCustomerId());

//        priceList.stream().forEach(i ->rewardsPoints.addAndGet(calculateRewardPoints(i)));
        List<RewardsDao> rewardsDaoList = new ArrayList<>();
        for(PriceDao priceDao : priceList){
            AtomicInteger rewardsPoints= new AtomicInteger();
            priceDao.getPrices().stream().forEach(i ->rewardsPoints.addAndGet(calculateRewardPoints(i)));
            rewardsDaoList.add(RewardsDao.builder().month(priceDao.getMonth()).priceList(priceDao.getPrices()).points(rewardsPoints.get()).build());
        }

        return rewardsDaoList;
    }

    private int calculateRewardPoints(int price){
        int points=0;
        if (price >=50 && price < 100) {
            points= price-50;
        } else if (price >100){
            points = (2*(price-100) + 50);
        }
        return points;
    }
}
