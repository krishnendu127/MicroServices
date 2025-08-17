package com.KB.Order.Service;

import com.KB.Order.Client.InventoryClient;
import com.KB.Order.DTO.OrderRequest;
import com.KB.Order.DTO.OrderResponse;
import com.KB.Order.Model.Order;
import com.KB.Order.OrderApplication;
import com.KB.Order.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public void placeOrder(OrderRequest orderRequest){
        boolean isInStock=inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if(isInStock) {
            Order order = mapToOrder(orderRequest);
            orderRepository.save(order);
        }
        else{
            throw new RuntimeException("Product with SkuCode"+orderRequest.skuCode()+"is out of stock");
        }
    }
    public List<OrderResponse> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResponse(order.getId(),order.getSkuCode(),order.getOrderNumber(),order.getPrice()))
                .toList();
    }
    public Order mapToOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        return order;
    }
}
