package com.qm.userservice.service;

import com.qm.userservice.dto.HistoryRequest;
import com.qm.userservice.entity.ConversionHistory;
import com.qm.userservice.repository.HistoryRepository;

import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private final
    HistoryRepository repository;

    public HistoryService(
            HistoryRepository repository
    ) {

        this.repository = repository;
    }

    public String saveHistory(
            HistoryRequest request
    ) {

        ConversionHistory history =
                new ConversionHistory();

        history.setUserId(
                request.getUserId()
        );

        history.setType(
                request.getType()
        );

        history.setFromUnit(
                request.getFromUnit()
        );

        history.setToUnit(
                request.getToUnit()
        );

        history.setInputValue(
                request.getInputValue()
        );

        history.setOutputValue(
                request.getOutputValue()
        );

        repository.save(history);

        return "History saved successfully";
    }
}