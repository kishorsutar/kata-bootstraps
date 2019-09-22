package it.dp.telldontaskkata.useCase;

import it.dp.telldontaskkata.useCase.exceptions.RejectedOrderCannotBeApprovedException;
import it.dp.telldontaskkata.useCase.exceptions.ShippedOrdersCannotBeChangedException;
import it.dp.telldontaskkata.domain.model.Order;
import it.dp.telldontaskkata.domain.model.OrderApprovalRequest;
import it.dp.telldontaskkata.domain.model.OrderStatus;
import it.dp.telldontaskkata.repository.OrderRepository;
import it.dp.telldontaskkata.useCase.exceptions.ApprovedOrderCannotBeRejectedException;

public class OrderApprovalUseCase {
    private final OrderRepository orderRepository;

    public OrderApprovalUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(OrderApprovalRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }

        if (request.isApproved() && order.getStatus().equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }

        if (!request.isApproved() && order.getStatus().equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejectedException();
        }

        order.setStatus(request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED);
        orderRepository.save(order);
    }
}
