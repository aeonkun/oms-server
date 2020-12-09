package com.ohwow.oms.producthistory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.producthistory.domain.ProductHistory;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

	List<ProductHistory> findByProductIdOrderByDateTimeModifiedDesc(long id);
}
