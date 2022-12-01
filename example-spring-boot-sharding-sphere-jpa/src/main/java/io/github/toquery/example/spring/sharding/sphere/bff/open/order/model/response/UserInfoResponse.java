package io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response;

import io.github.toquery.example.spring.sharding.sphere.modules.account.Account;
import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import io.github.toquery.example.spring.sharding.sphere.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.modules.order.OrderItem;
import io.github.toquery.example.spring.sharding.sphere.modules.user.User;

import java.util.List;

/**
 *
 */
public record UserInfoResponse(User user, Account account, List<Address> addresses, List<Order> orders, List<OrderItem> orderItems) {
}
