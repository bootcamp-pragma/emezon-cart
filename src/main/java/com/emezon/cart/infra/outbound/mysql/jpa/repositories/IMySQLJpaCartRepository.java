package com.emezon.cart.infra.outbound.mysql.jpa.repositories;

import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMySQLJpaCartRepository extends JpaRepository<CartEntity, String> {
}
