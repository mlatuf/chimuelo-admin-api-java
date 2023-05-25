package org.example.repository;


import org.example.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("select u from ClientEntity u where lower(u.name) like lower(concat('%', :value,'%'))" +
            "or lower(u.lastname) like lower(concat('%', :value,'%'))")
    List<ClientEntity> findByString(@Param("value") String value);

}