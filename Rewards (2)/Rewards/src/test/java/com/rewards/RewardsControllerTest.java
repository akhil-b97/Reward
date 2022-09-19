package com.rewards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewards.dao.PriceDao;
import com.rewards.dao.RewardsRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RewardsControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getRewards() throws Exception {
        String uri = "/getRewards";
        RewardsRequest rewardsRequest = new RewardsRequest();
        rewardsRequest.setCustomerId("test0");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(rewardsRequest))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(415, status);
        //String content = mvcResult.getResponse().getContentAsString();

    }

    @Test
    public void getCustomerRewards(){
        RewardsRequest rewardsRequest = new RewardsRequest();
        rewardsRequest.setCustomerId("test0");

        List<PriceDao> priceDaoList = rewardsService.getPriceList(rewardsRequest.getCustomerId());
        assertNotNull(priceDaoList);
    }

    @Test
    public void getNoNExistCustomerRewards(){
        RewardsRequest rewardsRequest = new RewardsRequest();
        rewardsRequest.setCustomerId("test4");

        List<PriceDao> priceDaoList = rewardsService.getPriceList(rewardsRequest.getCustomerId());
        assertNull(priceDaoList);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
