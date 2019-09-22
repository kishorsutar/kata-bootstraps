package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.model.Product;

public interface ProductCatalog {
    Product getByName(String name);
}
