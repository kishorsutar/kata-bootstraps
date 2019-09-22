package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.model.Product;
import it.gabrieletondi.telldontaskkata.domain.model.SellItemRequest;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

 class TaxCalculator {

    private SellItemRequest itemRequest;
    private Product product;
    private BigDecimal taxedAmount;
    private BigDecimal taxAmount;

    TaxCalculator(SellItemRequest itemRequest, Product product) {
        this.itemRequest = itemRequest;
        this.product = product;
    }

    BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    BigDecimal getTaxAmount() {
        return taxAmount;
    }

     TaxCalculator invoke() {
        final BigDecimal unitaryTax = product.getPrice().divide(valueOf(100)).multiply(product.getCategory().getTaxPercentage()).setScale(2, HALF_UP);
        final BigDecimal unitaryTaxedAmount = product.getPrice().add(unitaryTax).setScale(2, HALF_UP);
        taxedAmount = unitaryTaxedAmount.multiply(BigDecimal.valueOf(itemRequest.getQuantity())).setScale(2, HALF_UP);
        taxAmount = unitaryTax.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
        return this;
    }
}
