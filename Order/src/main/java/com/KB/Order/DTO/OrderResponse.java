package com.KB.Order.DTO;

import java.math.BigDecimal;

public record OrderResponse(Long id, String skuCode, String orderNumber, BigDecimal quantity) {
}
