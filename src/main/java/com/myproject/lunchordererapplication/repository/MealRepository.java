package com.myproject.lunchordererapplication.repository;

import com.myproject.lunchordererapplication.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
