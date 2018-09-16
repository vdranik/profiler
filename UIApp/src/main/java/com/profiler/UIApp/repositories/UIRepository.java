package com.profiler.UIApp.repositories;

import com.profiler.UIApp.domain.UIData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UIRepository extends JpaRepository<UIData, Integer> {
}
