package it.dp.telldontaskkata.repository;

import it.dp.telldontaskkata.domain.model.Product;

public interface ProductCatalog {
    Product getByName(String name);
}
