package com.malkoc.orderservice.entity;

import com.malkoc.orderservice.enums.PaymentMethod;
import com.malkoc.orderservice.order_line.OrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "costumer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String costumerId;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    @Column(insertable = false , nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false , nullable = false)
    private LocalDateTime lastModifiedDate;

 }
