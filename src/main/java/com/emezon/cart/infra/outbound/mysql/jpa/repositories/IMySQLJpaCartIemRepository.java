package com.emezon.cart.infra.outbound.mysql.jpa.repositories;

import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMySQLJpaCartIemRepository extends JpaRepository<CartItemEntity, String> {

}
