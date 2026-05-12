package com.qm.userservice.repository;

import com.qm.userservice.entity.ConversionHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository
        extends JpaRepository<
        ConversionHistory,
        Long
        > {
}