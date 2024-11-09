package com.emezon.cart.infra.outbound.mysql.jpa.repositories;

import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMySQLJpaCartItemRepository extends JpaRepository<CartItemEntity, String> {

    Page<CartItemEntity> findByCartId(String cartId, Pageable pageable);

}
