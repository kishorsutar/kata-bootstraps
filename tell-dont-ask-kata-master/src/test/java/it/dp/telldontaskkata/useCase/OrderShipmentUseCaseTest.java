package it.dp.telldontaskkata.useCase;

import it.dp.telldontaskkata.domain.model.Order;
import it.dp.telldontaskkata.domain.model.OrderShipmentRequest;
import it.dp.telldontaskkata.domain.model.OrderStatus;
import it.dp.telldontaskkata.doubles.TestOrderRepository;
import it.dp.telldontaskkata.doubles.TestShipmentService;
import it.dp.telldontaskkata.useCase.exceptions.OrderCannotBeShippedException;
import it.dp.telldontaskkata.useCase.exceptions.OrderCannotBeShippedTwiceException;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class OrderShipmentUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final TestShipmentService shipmentService = new TestShipmentService();
    private final OrderShipmentUseCase useCase = new OrderShipmentUseCase(orderRepository, shipmentService);

    @Test
    public void shipApprovedOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatus(OrderStatus.APPROVED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder().getStatus(), is(OrderStatus.SHIPPED));
        assertThat(shipmentService.getShippedOrder(), is(initialOrder));
    }

    @Test(expected = OrderCannotBeShippedException.class)
    public void createdOrdersCannotBeShipped() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatus(OrderStatus.CREATED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
        assertThat(shipmentService.getShippedOrder(), is(nullValue()));
    }

    @Test(expected = OrderCannotBeShippedException.class)
    public void rejectedOrdersCannotBeShipped() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatus(OrderStatus.REJECTED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
        assertThat(shipmentService.getShippedOrder(), is(nullValue()));
    }

    @Test(expected = OrderCannotBeShippedTwiceException.class)
    public void shippedOrdersCannotBeShippedAgain() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatus(OrderStatus.SHIPPED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
        assertThat(shipmentService.getShippedOrder(), is(nullValue()));
    }
}
