package com.monetastore.monetastore.services;

import com.monetastore.monetastore.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// in other to read and write from the data base
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
