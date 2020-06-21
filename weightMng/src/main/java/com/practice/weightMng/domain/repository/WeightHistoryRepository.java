package com.practice.weightMng.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.weightMng.domain.model.WeightHistory;

@Repository
public interface WeightHistoryRepository extends JpaRepository<WeightHistory, Integer> {
	
	// session.userのidを条件に抽出したWeightHistoryのレコード全件をjava.util.Listで返す
	@Query("SELECT wh FROM WeightHistory wh WHERE :userId = wh.id ORDER BY wh.measuredDay")
	public List<WeightHistory> findByUserId(@Param("userId") Integer userId);
}
    