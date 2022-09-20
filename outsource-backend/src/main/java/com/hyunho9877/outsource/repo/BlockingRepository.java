package com.hyunho9877.outsource.repo;

import com.hyunho9877.outsource.domain.BlockKey;
import com.hyunho9877.outsource.domain.Blocking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BlockingRepository extends JpaRepository<Blocking, BlockKey> {
    Set<Blocking> findByRequester(String requester);
    List<Blocking> findByTarget(String target);
}