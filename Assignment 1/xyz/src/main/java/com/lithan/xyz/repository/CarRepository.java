package com.lithan.xyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lithan.xyz.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	@Query(value="SELECT * FROM Car WHERE "
			+ "make like %:keyword% OR "
			+ "model like %:keyword% OR "
			+ "registration like %:keyword% AND "
			+ "price BETWEEN :minPrice AND :maxPrice",nativeQuery = true)
    List<Car> search(String keyword, double minPrice, double maxPrice);
}