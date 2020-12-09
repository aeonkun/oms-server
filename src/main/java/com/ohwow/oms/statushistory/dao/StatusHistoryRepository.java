package com.ohwow.oms.statushistory.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.statushistory.domain.StatusHistory;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {

	@Query("select s from StatusHistory s where s.orderId.id = :id")
	Optional<List<StatusHistory>> findByOrderIdOrderByIdDesc(long id);
}
