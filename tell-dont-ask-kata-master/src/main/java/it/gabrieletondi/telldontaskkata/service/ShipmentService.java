package it.gabrieletondi.telldontaskkata.service;

import it.gabrieletondi.telldontaskkata.domain.model.Order;

public interface ShipmentService {
    void ship(Order order);
}
