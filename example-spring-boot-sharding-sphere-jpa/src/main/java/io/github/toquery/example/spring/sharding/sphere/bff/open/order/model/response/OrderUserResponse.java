package io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 */
//@SqlResultSetMapping(
//        name = "OrderUser",
//        classes = {
//                @ConstructorResult(
//                        targetClass = OrderUserResponse.class,
//                        columns = {
//                                @ColumnResult(name = "id", type = String.class),
//                                @ColumnResult(name = "nt", type = String.class)
//                        }
//                )
//        })
@Data
public class OrderUserResponse {

    private Long orderId;

    private Long userId;

    private String username;

    private String pwd;

    private Long addressId;

    private String status;

    private LocalDateTime createDateTime;

    public OrderUserResponse(Long orderId) {
        this.orderId = orderId;
    }
}