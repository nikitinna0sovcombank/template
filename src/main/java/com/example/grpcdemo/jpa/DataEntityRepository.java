package com.example.grpcdemo.jpa;

import com.example.grpcdemo.entity.DataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {

    Page<DataEntity> findAll(Pageable pageable);
}
