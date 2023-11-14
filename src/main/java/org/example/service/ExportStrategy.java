package org.example.service;

import org.example.model.ExportStrategyName;
import org.example.model.Product;

import java.io.File;
import java.util.List;

public interface ExportStrategy {

    ExportStrategyName getStrategyName();

    File toFile(List<Product> productList);
}
