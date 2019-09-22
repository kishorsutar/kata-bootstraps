package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.model.Order;
import it.gabrieletondi.telldontaskkata.domain.model.OrderShipmentRequest;
import it.gabrieletondi.telldontaskkata.domain.model.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.exceptions.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.useCase.exceptions.OrderCannotBeShippedTwiceException;

import static it.gabrieletondi.telldontaskkata.domain.model.OrderStatus.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.model.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.model.OrderStatus.SHIPPED;

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
