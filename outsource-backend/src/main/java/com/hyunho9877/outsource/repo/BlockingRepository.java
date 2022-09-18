package com.hyunho9877.outsource.repo;

import com.hyunho9877.outsource.domain.BlockKey;
import com.hyunho9877.outsource.domain.Blocking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockingRepository extends JpaRepository<Blocking, BlockKey> {
    List<Blocking> findByRequester(String requester);
    List<Blocking> findByTarget(String target);
}