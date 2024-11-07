package com.emezon.cart.infra.outbound.mysql.jpa.entities;

import com.emezon.cart.infra.outbound.mysql.jpa.constants.CartEntityConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity(name = CartEntityConstants.ENTITY_NAME)
@Table(name = CartEntityConstants.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private int status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
