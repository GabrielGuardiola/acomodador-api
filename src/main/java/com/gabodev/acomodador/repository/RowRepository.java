package com.gabodev.acomodador.repository;

import com.gabodev.acomodador.entity.Row;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowRepository extends JpaRepository<Row, Long> {
}