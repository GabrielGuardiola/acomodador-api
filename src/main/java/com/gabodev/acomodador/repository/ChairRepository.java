package com.gabodev.acomodador.repository;

import com.gabodev.acomodador.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<Chair, Long> {
}