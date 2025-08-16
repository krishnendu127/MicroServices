package com.KB.Product.DTO;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price) {
}
