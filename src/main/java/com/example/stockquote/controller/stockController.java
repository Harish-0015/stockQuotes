package com.example.stockquote.controller;

import com.example.stockquote.services.stockServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class stockController {
    private final stockServices stockServices;

    @GetMapping("/getStock")
    public ResponseEntity<?> callEndPoint(){
        return ResponseEntity.ok(stockServices.getStockData());

    }

    @GetMapping("/getSingleStock")
    public String getStockData(@RequestParam String symbol) {
        return stockServices.getStockDataBySymbol(symbol);
    }

}
