package io.github.toquery.example.spring.sharding.sphere.bff.open.order.service;

import io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse;
import io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.UserInfoResponse;
import io.github.toquery.example.spring.sharding.sphere.modules.account.Account;
import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import io.github.toquery.example.spring.sharding.sphere.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.modules.order.OrderItem;
import io.github.toquery.example.spring.sharding.sphere.modules.statistics.StatisticsOrder;
import io.github.toquery.example.spring.sharding.sphere.modules.statistics.service.StatisticsOrderService;
import io.github.toquery.example.spring.sharding.sphere.modules.user.User;
import io.github.toquery.example.spring.sharding.sphere.modules.account.service.AccountService;
import io.github.toquery.example.spring.sharding.sphere.modules.address.service.AddressService;
import io.github.toquery.example.spring.sharding.sphere.modules.order.service.OrderItemService;
import io.github.toquery.example.spring.sharding.sphere.modules.order.service.OrderService;
import io.github.toquery.example.spring.sharding.sphere.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class OpenOrderService {

    private final UserService userService;
    private final OrderService orderService;
    private final AccountService accountService;
    private final AddressService addressService;
    private final OrderItemService orderItemService;

    private final StatisticsOrderService statisticsOrderService;

    public UserInfoResponse userInfo(Long userId) {
        return new UserInfoResponse(userService.findById(userId), accountService.getByUserId(userId), addressService.findByUserId(userId), orderService.findByUserId(userId), orderItemService.findByUserId(userId));
    }

    public List<Order> list() {
        return orderService.list();
    }


    public List<OrderUserResponse> listWithUser() {
        return orderService.listWithUser();
    }

    public Page<Order> page(Integer page, Integer size) {
        return orderService.page(page,size);
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

    public List<StatisticsOrder> statisticsStore(Long storeId) {
        return statisticsOrderService.findByStoreId(storeId);
    }

    public List<StatisticsOrder> statisticsUser(Long userId) {

        return statisticsOrderService.findByUserId(userId);
    }


}
