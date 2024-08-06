package com.example.stockquote.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class stockServices {

    private static final String apiurl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=FJGPSD0HSULRYRR7";
    private static final String key = "FJGPSD0HSULRYRR7";
    @Autowired
    private RestTemplate restTemplate;
    public Object getStockData(){
        String url="";
        try{
            ResponseEntity<String> response = restTemplate.exchange(apiurl,HttpMethod.GET,null,String.class);
            log.info("Output from API: ",response.getBody());
            return response.getBody();

        }
        catch (Exception e){
            log.error("Something went wrong");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling endpoint of RapidAPI for corona",
                    e
            );
        }

    }

    public String getStockDataBySymbol(String symbol) {
        String url = String.format("%s?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s", apiurl, symbol, key);
        return restTemplate.getForObject(url, String.class);
    }
}

