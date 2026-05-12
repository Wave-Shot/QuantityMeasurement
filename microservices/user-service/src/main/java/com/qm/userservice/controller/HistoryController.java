package com.qm.userservice.controller;

import com.qm.userservice.dto.HistoryRequest;
import com.qm.userservice.service.HistoryService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class HistoryController {

    private final
    HistoryService historyService;

    public HistoryController(
            HistoryService historyService
    ) {

        this.historyService =
                historyService;
    }

    @PostMapping("/history")
    public String saveHistory(
            @RequestBody
            HistoryRequest request
    ) {

        return historyService.saveHistory(
                request
        );
    }

    @GetMapping("/test")
    public String test() {

        return "user-service working";
    }
}