package org.example.repository;


import org.example.entity.CategoryEntity;
import org.example.entity.ProductEntity;
import org.example.entity.VariantEntity;
import org.example.model.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
}