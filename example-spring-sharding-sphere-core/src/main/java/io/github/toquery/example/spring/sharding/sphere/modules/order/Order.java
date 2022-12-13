package io.github.toquery.example.spring.sharding.sphere.modules.order;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 *
 */
@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Basic(optional = false)
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

}
