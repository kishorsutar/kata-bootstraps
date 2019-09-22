package it.dp.telldontaskkata.useCase;

import it.dp.telldontaskkata.domain.model.OrderShipmentRequest;
import it.dp.telldontaskkata.domain.model.Order;
import it.dp.telldontaskkata.domain.model.OrderStatus;
import it.dp.telldontaskkata.repository.OrderRepository;
import it.dp.telldontaskkata.service.ShipmentService;
import it.dp.telldontaskkata.useCase.exceptions.OrderCannotBeShippedException;
import it.dp.telldontaskkata.useCase.exceptions.OrderCannotBeShippedTwiceException;

import static it.dp.telldontaskkata.domain.model.OrderStatus.CREATED;
import static it.dp.telldontaskkata.domain.model.OrderStatus.REJECTED;
import static it.dp.telldontaskkata.domain.model.OrderStatus.SHIPPED;

public class OrderShipmentUseCase {
    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;

    public OrderShipmentUseCase(OrderRepository orderRepository, ShipmentService shipmentService) {
        this.orderRepository = orderRepository;
        this.shipmentService = shipmentService;
    }

    public void run(OrderShipmentRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(CREATED) || order.getStatus().equals(REJECTED)) {
            throw new OrderCannotBeShippedException();
        }

        if (order.getStatus().equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwiceException();
        }

        shipmentService.ship(order);

        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }
}
