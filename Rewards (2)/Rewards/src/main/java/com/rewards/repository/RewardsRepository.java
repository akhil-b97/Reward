package com.rewards.repository;

import com.rewards.dao.PriceDao;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
public class RewardsRepository {

    private HashMap<String, List<PriceDao>>  pirceMap= new HashMap<>();


    RewardsRepository() {
        PriceDao.builder().month("Jan").prices(Arrays.asList(134,145,7,89)).build();
        pirceMap.put("test0",
                Arrays.asList(PriceDao.builder().month("Jan").prices(Arrays.asList(134,145,7,89)).build(),
                        PriceDao.builder().month("Feb").prices(Arrays.asList(134,145,7,89,1016)).build()));
        pirceMap.put("test1",
                Arrays.asList(PriceDao.builder().month("Jan").prices(Arrays.asList(134,145,7,89)).build(),
                        PriceDao.builder().month("Mar").prices(Arrays.asList(134,145,7,89)).build()));
        pirceMap.put("test2",
                Arrays.asList(PriceDao.builder().month("Apr").prices(Arrays.asList(134,145,7,89)).build(),
                        PriceDao.builder().month("Mar").prices(Arrays.asList(134,145,7,89)).build()));


    }

    public List<PriceDao> findByCustomer(String id){
        return pirceMap.get(id);
    }


}
