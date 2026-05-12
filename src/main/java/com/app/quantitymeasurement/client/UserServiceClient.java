package com.app.quantitymeasurement.client;

import com.app.quantitymeasurement.dto.HistoryRequest;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/api/users/history")

    String saveHistory(
            @RequestBody
            HistoryRequest request
    );
}