package com.parsakav.langclass.repository;

import com.parsakav.langclass.model.Lession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessionRepository extends JpaRepository<Lession,Long> {
}
