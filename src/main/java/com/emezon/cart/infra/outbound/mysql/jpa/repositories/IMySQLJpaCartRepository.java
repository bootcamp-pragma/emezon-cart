package com.emezon.cart.infra.outbound.mysql.jpa.repositories;

import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMySQLJpaCartRepository extends JpaRepository<CartEntity, String> {

    Optional<CartEntity> findByClientId(String clientId);

}
