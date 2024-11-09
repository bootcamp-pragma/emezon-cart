package com.emezon.cart.infra.outbound.mysql.jpa.adapters;

import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.spi.ICartItemRepositoryOutPort;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;
import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartItemEntity;
import com.emezon.cart.infra.outbound.mysql.jpa.mappers.CartItemEntityMapper;
import com.emezon.cart.infra.outbound.mysql.jpa.repositories.IMySQLJpaCartItemRepository;
import com.emezon.cart.infra.outbound.mysql.jpa.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RequiredArgsConstructor
public class MySQLJpaCartItemAdapter implements ICartItemRepositoryOutPort {

    private final IMySQLJpaCartItemRepository repository;

    @Override
    public CartItem save(CartItem cartItem) {
        CartItemEntity entity = CartItemEntityMapper.toEntity(cartItem, true);
        CartItemEntity savedEntity = repository.save(entity);
        return CartItemEntityMapper.toModel(savedEntity, true);
    }

    @Override
    public Optional<CartItem> findById(String id) {
        Optional<CartItemEntity> entity = repository.findById(id);
        return entity.map(e -> CartItemEntityMapper.toModel(e, true));
    }

    @Override
    public Optional<CartItem> findByCartIdAndArticleId(String cartId, String articleId) {
        return repository.findByCartIdAndArticleId(cartId, articleId).map(e -> CartItemEntityMapper.toModel(e, true));
    }

    @Override
    public Optional<CartItem> findByUserIdAndArticleId(String userId, String articleId) {
        return Optional.empty();
    }

    @Override
    public PaginatedResponse<CartItem> findAll(PaginatedResponseParams params) {
        Pageable pageable = PageableUtils.getFromPaginatedResponseParams(params);
        Page<CartItemEntity> page = repository.findAll(pageable);
        PaginatedResponse<CartItem> res = new PaginatedResponse<>();
        res.setItems(page.getContent().stream().map(entity -> CartItemEntityMapper.toModel(entity, true)).toList());
        res.setTotalItems(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        res.setPage(page.getNumber());
        res.setSize(page.getSize());
        return res;
    }

    @Override
    public PaginatedResponse<CartItem> findAllByCartId(String cartId, PaginatedResponseParams params) {
        Pageable pageable = PageableUtils.getFromPaginatedResponseParams(params);
        Page<CartItemEntity> page = repository.findAllByCartId(cartId, pageable);
        PaginatedResponse<CartItem> res = new PaginatedResponse<>();
        res.setItems(page.getContent().stream().map(entity -> CartItemEntityMapper.toModel(entity, true)).toList());
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
