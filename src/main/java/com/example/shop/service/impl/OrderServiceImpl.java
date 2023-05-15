package com.example.shop.service.impl;

import com.example.shop.exception.NullEntityReferenceException;
import com.example.shop.exception.UnacceptableParameterValueException;
import com.example.shop.model.order.Order;
import com.example.shop.model.order.OrderProduct;
import com.example.shop.model.order.OrderProductId;
import com.example.shop.model.order.OrderStatus;
import com.example.shop.model.product.Product;
import com.example.shop.repository.OrderProductRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.service.OrderService;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String NOT_FOUND_ENTITY_MESSAGE = "Order (id=UUID: %s) was not found";
    private static final String NULL_ENTITY_MESSAGE = "Order cannot be 'null'";
    private static final String ENTITY_DELETED_MESSAGE = "Order (id=UUID: %s) was deleted";

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private ProductService productService;

    @Override
    public Order save(Order order) {
        checkIfOrderIsNull(order);
        return orderRepository.save(order);
    }

    private static void checkIfOrderIsNull(Order order) {
        if (order == null) {
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    @Override
    public void delete(UUID id) {
        orderRepository.findById(id).ifPresentOrElse(user -> {
            orderRepository.delete(user);
            log.info(ENTITY_DELETED_MESSAGE.formatted(id));
        }, () -> {
            log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    @Override
    public List<Order> findAllOrdersByUserId(UUID userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<Order> findAllByOrderStatus(String status) {
        checkIfStatusIsBlank(status);
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        return orderRepository.findAllByStatus(orderStatus);
    }

    @Override
    public Order changeOrderStatus(UUID orderId, OrderStatus status) {
        Order order = findById(orderId);
        switch (order.getStatus()) {
            case DRAFT -> changeStatusToPAYED(status, order);
            case PAYED -> changeStatusToDELIVERINGorREFUNDED(status, order);
            case DELIVERING -> changeStatusToCOMPLETEDorRETURNING(status, order);
            case RETURNING -> changeStatusToREFUNDED(status, order);
        }
        return save(order);
    }

    private void changeStatusToREFUNDED(OrderStatus status, Order order) {
        if (status == OrderStatus.REFUNDED) {
            order.setStatus(OrderStatus.REFUNDED);
        }
    }

    private void changeStatusToCOMPLETEDorRETURNING(OrderStatus status, Order order) {
        if (status == OrderStatus.COMPLETED) {
            order.setStatus(OrderStatus.COMPLETED);
        } else if (status == OrderStatus.RETURNING) {
            order.setStatus(OrderStatus.RETURNING);
        }
    }

    private void changeStatusToDELIVERINGorREFUNDED(OrderStatus status, Order order) {
        if (status == OrderStatus.REFUNDED) {
            order.setStatus(OrderStatus.REFUNDED);
        } else if (status == OrderStatus.DELIVERING) {
            order.setStatus(OrderStatus.DELIVERING);
        }
    }

    private void changeStatusToPAYED(OrderStatus status, Order order) {
        if (status == OrderStatus.PAYED) {
            order.setStatus(OrderStatus.PAYED);
        }
    }

    @Override
    public void setQuantityOfProductsInOrder(UUID orderId, UUID productId, int quantity) {
        Order order = findById(orderId);
        Product product = productService.findById(productId);
        OrderProductId id = new OrderProductId();
        id.setOrderId(orderId);
        id.setProductId(productId);

        if (quantity == 0) {
            orderProductRepository.deleteById(id);
        }

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(id);
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);

        orderProductRepository.save(orderProduct);
    }

    private static void checkIfStatusIsBlank(String status) throws UnacceptableParameterValueException {
        if (status == null || status.isEmpty()) {
            throw new UnacceptableParameterValueException("Status is null or empty");
        }
    }
}
