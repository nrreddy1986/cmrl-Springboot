package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.CreateOrderRequest;
import com.shellinfo.demo.model.DeliveryPartner;
import com.shellinfo.demo.model.Order;
import com.shellinfo.demo.model.dto.TrackingDto;
import com.shellinfo.demo.model.dto.UpdateOrderStatusRequest;
import com.shellinfo.demo.service.OrderService;
import com.shellinfo.demo.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest req) {
        return orderService.createOrder(req);
    }

    @PostMapping("/{orderId}/assign/{partnerId}")
    public Order assignPartner(
            @PathVariable Long orderId,
            @PathVariable Long partnerId) {

        return orderService.assignPartner(orderId, partnerId);
    }

    @PostMapping("/{orderId}/status")
    public Order updateStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest req) {

        return orderService.updateStatus(orderId, req.getStatus());
    }

    @GetMapping("/{orderId}/track")
    public TrackingDto trackOrder(@PathVariable Long orderId) {
        return orderService.trackOrder(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getCustomerOrders(@PathVariable Long customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    @GetMapping("/partner/{partnerId}")
    public List<Order> getPartnerOrders(@PathVariable Long partnerId) {
        return orderService.getPartnerOrders(partnerId);
    }

    private final PartnerService partnerService;

    @GetMapping("/partners/available")
    public List<DeliveryPartner> getAvailablePartners() {
        return partnerService.getAvailablePartners();
    }

}