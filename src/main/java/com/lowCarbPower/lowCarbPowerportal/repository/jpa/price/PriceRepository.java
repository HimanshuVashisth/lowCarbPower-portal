package com.lowCarbPower.lowCarbPowerportal.repository.jpa.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lowCarbPower.lowCarbPowerportal.entity.PriceEntity;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

	PriceEntity findFirstByOrderByIdDesc();
}
