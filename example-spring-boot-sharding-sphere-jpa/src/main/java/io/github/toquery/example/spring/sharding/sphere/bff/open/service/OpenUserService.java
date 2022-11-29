package io.github.toquery.example.spring.sharding.sphere.bff.open.service;

import io.github.toquery.example.spring.sharding.sphere.bff.open.model.response.UserInfoResponse;
import io.github.toquery.example.spring.sharding.sphere.core.modules.account.Account;
import io.github.toquery.example.spring.sharding.sphere.core.modules.address.Address;
import io.github.toquery.example.spring.sharding.sphere.core.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.core.modules.order.OrderItem;
import io.github.toquery.example.spring.sharding.sphere.core.modules.user.User;
import io.github.toquery.example.spring.sharding.sphere.modules.account.service.AccountService;
import io.github.toquery.example.spring.sharding.sphere.modules.address.service.AddressService;
import io.github.toquery.example.spring.sharding.sphere.modules.order.service.OrderItemService;
import io.github.toquery.example.spring.sharding.sphere.modules.order.service.OrderService;
import io.github.toquery.example.spring.sharding.sphere.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class OpenUserService {

    private final UserService userService;
    private final OrderService orderService;
    private final AccountService accountService;
    private final AddressService addressService;
    private final OrderItemService orderItemService;

    public UserInfoResponse userInfo(Long userId) {
        return new UserInfoResponse(userService.findById(userId), accountService.getByUserId(userId), addressService.findByUserId(userId), orderService.findByUserId(userId), orderItemService.findByUserId(userId));
    }

    public UserInfoResponse save() {
        User user = userService.save();
        Long userId = user.getId();
        Account account = accountService.save(userId);
        List<Address> address = addressService.save(userId);
        List<Order> orders = orderService.save(userId, address.get(0).getId());
        List<OrderItem> orderItems = orderItemService.save(userId, orders);
        return new UserInfoResponse(user, account, address, orders, orderItems);
    }
}
