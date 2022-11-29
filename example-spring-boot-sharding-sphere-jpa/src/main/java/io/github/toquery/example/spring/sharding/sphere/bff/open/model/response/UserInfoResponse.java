package io.github.toquery.example.spring.sharding.sphere.bff.open.model.response;

import io.github.toquery.example.spring.sharding.sphere.core.modules.account.Account;
import io.github.toquery.example.spring.sharding.sphere.core.modules.address.Address;
import io.github.toquery.example.spring.sharding.sphere.core.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.core.modules.order.OrderItem;
import io.github.toquery.example.spring.sharding.sphere.core.modules.user.User;

import java.util.List;

/**
 *
 */
public record UserInfoResponse(User user, Account account, List<Address> addresses, List<Order> orders, List<OrderItem> orderItems) {
}
