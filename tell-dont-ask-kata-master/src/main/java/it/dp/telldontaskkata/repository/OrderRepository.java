package it.dp.telldontaskkata.repository;

import it.dp.telldontaskkata.domain.model.Order;

public interface OrderRepository {
    void save(Order order);

    Order getById(int orderId);
}
