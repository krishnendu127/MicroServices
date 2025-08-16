package com.KB.Order.DTO;

import java.math.BigDecimal;

public record OrderRequest(Long id, String skuCode, BigDecimal price,Integer quantity) {
}
