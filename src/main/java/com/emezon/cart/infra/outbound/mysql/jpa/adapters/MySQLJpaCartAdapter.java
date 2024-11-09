package com.emezon.cart.infra.outbound.mysql.jpa.adapters;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;
import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartEntity;
import com.emezon.cart.infra.outbound.mysql.jpa.mappers.CartEntityMapper;
import com.emezon.cart.infra.outbound.mysql.jpa.repositories.IMySQLJpaCartRepository;
import com.emezon.cart.infra.outbound.mysql.jpa.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RequiredArgsConstructor
public class MySQLJpaCartAdapter implements ICartRepositoryOutPort {

    private final IMySQLJpaCartRepository repository;

    @Override
    public Cart save(Cart cart) {
        boolean includeItems = cart.getItems() != null;
        CartEntity entity = CartEntityMapper.toEntity(cart, includeItems);
        CartEntity savedEntity = repository.save(entity);
        return CartEntityMapper.toModel(savedEntity, includeItems);
    }

    @Override
    public Optional<Cart> findById(String id) {
        CartEntity entity = repository.findById(id).orElse(null);
        return Optional.ofNullable(CartEntityMapper.toModel(entity, true));
    }

    @Override
    public Optional<Cart> findByUserIdAndStatus(String userId, int status) {
        CartEntity entity = repository.findByClientIdAndStatus(userId, status).orElse(null);
        return Optional.ofNullable(CartEntityMapper.toModel(entity, true));
    }

    @Override
    public PaginatedResponse<Cart> findAll(PaginatedResponseParams params) {
        Pageable pageable = PageableUtils.getFromPaginatedResponseParams(params);
        Page<CartEntity> page = repository.findAll(pageable);
        PaginatedResponse<Cart> res = new PaginatedResponse<>();
        res.setItems(page.getContent().stream().map(entity -> CartEntityMapper.toModel(entity, true)).toList());
        res.setTotalItems(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        res.setPage(page.getNumber());
        res.setSize(page.getSize());
        return res;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
