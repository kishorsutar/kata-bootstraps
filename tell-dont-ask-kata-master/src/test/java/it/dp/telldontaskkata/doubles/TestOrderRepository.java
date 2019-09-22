package it.dp.telldontaskkata.doubles;

import it.dp.telldontaskkata.domain.model.Order;
import it.dp.telldontaskkata.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class TestOrderRepository implements OrderRepository {
    private Order insertedOrder;
    private List<Order> orders = new ArrayList<>();

    public Order getSavedOrder() {
        return insertedOrder;
    }

    public void save(Order order) {
        this.insertedOrder = order;
    }

    @Override
    public Order getById(int orderId) {
        return orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
