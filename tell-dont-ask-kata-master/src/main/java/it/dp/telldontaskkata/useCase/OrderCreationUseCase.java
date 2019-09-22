package it.dp.telldontaskkata.useCase;

import it.dp.telldontaskkata.domain.model.*;
import it.dp.telldontaskkata.useCase.exceptions.UnknownProductException;
import it.dp.telldontaskkata.repository.OrderRepository;
import it.dp.telldontaskkata.repository.ProductCatalog;

import java.math.BigDecimal;
import java.util.ArrayList;


public class OrderCreationUseCase {
    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public void run(SellItemsRequest request) {
        Order order = getStubOrder();

        for (SellItemRequest itemRequest : request.getRequests()) {
            Product product = productCatalog.getByName(itemRequest.getProductName());

            if (product == null) {
                throw new UnknownProductException();
            }
            else {
                addProduct(order, itemRequest, product);
            }
        }

        orderRepository.save(order);
    }

    private Order getStubOrder() {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setItems(new ArrayList<>());
        order.setCurrency("EUR");
        order.setTotal(new BigDecimal("0.00"));
        order.setTax(new BigDecimal("0.00"));
        return order;
    }

    private void addProduct(Order order, SellItemRequest itemRequest, Product product) {
        TaxCalculator taxCalculator = new TaxCalculator(itemRequest, product).invoke();
        BigDecimal taxedAmount = taxCalculator.getTaxedAmount();
        BigDecimal taxAmount = taxCalculator.getTaxAmount();

        order.getItems().add(getOrderItem(itemRequest, product, taxedAmount, taxAmount));
        order.setTotal(order.getTotal().add(taxedAmount));
        order.setTax(order.getTax().add(taxAmount));
    }

    private OrderItem getOrderItem(SellItemRequest itemRequest, Product product, BigDecimal taxedAmount, BigDecimal taxAmount) {
        final OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(itemRequest.getQuantity());
        orderItem.setTax(taxAmount);
        orderItem.setTaxedAmount(taxedAmount);
        return orderItem;
    }

}
