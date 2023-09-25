package com.victormartingil.prices.infrastructure.persistence.repositories;

import com.victormartingil.prices.infrastructure.persistence.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceH2JpaRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(Integer brandId, Integer productId, LocalDateTime startDate, LocalDateTime endDate);

}
