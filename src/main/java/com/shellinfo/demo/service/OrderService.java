package com.shellinfo.demo.service;

import com.shellinfo.demo.model.CreateOrderRequest;
import com.shellinfo.demo.model.DeliveryPartner;
import com.shellinfo.demo.model.Order;
import com.shellinfo.demo.model.PartnerLocation;
import com.shellinfo.demo.model.dto.TrackingDto;
import com.shellinfo.demo.repository.DeliveryPartnerRepository;
import com.shellinfo.demo.repository.OrderRepository;
import com.shellinfo.demo.repository.PartnerLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PartnerLocationRepository locationRepository;

    public Order createOrder(CreateOrderRequest req) {

        Order order = new Order();

        order.setCustomerId(req.getCustomerId());
        order.setPickupLat(req.getPickupLat());
        order.setPickupLng(req.getPickupLng());
        order.setDropLat(req.getDropLat());
        order.setDropLng(req.getDropLng());
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public TrackingDto trackOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow();

        PartnerLocation location =
                locationRepository.findTopByPartnerIdOrderByUpdatedAtDesc(order.getPartnerId());

        return new TrackingDto(
                order.getStatus(),
                location.getLatitude(),
                location.getLongitude()
        );
    }

    public Order updateStatus(Long orderId, String status){

        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setStatus(status);

        if(status.equals("DELIVERED")){
            DeliveryPartner partner =
                    partnerRepository.findById(order.getPartnerId()).orElseThrow();

            partner.setStatus("AVAILABLE");
            partnerRepository.save(partner);
        }

        return orderRepository.save(order);
    }

    public List<Order> getCustomerOrders(Long customerId) {

        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getPartnerOrders(Long partnerId) {

        return orderRepository.findByPartnerId(partnerId);
    }

    private final DeliveryPartnerRepository partnerRepository;

    public Order assignPartner(Long orderId, Long partnerId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        DeliveryPartner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        order.setPartnerId(partner.getId());
        order.setStatus("ASSIGNED");

        partner.setStatus("BUSY");

        partnerRepository.save(partner);
        return orderRepository.save(order);
    }
}

