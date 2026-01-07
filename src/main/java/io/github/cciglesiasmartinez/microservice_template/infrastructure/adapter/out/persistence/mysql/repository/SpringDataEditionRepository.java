package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataEditionRepository extends JpaRepository<EditionEntity, String> {

    boolean existsByBarCode(String barCode);
    Optional<EditionEntity> findByBarCode(String barCode);

}
