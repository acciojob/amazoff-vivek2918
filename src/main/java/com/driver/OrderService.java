package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

     @Autowired
    OrderRepository orderRepository;

    public String addOrder(Order order) {
        String str = orderRepository.addOrder(order);
        return str;
    }

    public String addPartner(String partnerId) {
        String str = orderRepository.addPartner(partnerId);
        return str;
    }

    public String addOrderPartnerPair(String orderId, String partnerId) {
        String str = orderRepository.addOrderPartnerPair(orderId, partnerId);
        return str;
    }

    public Order getOrderById(String orderId) {
        Order odrs = orderRepository.getOrderById(orderId);
        return odrs;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        DeliveryPartner dp = orderRepository.getPartnerById(partnerId);
        return dp;
    }

    public int getOrderCountByPartnerId(String partnerId) {
        int res = orderRepository.getOrderCountByPartnerId(partnerId);
        return res;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> l = orderRepository.getOrdersByPartnerId(partnerId);
        return l;
    }

    public List<String> getAllOrders() {
        List<String> l = orderRepository.getAllOrders();
        return l;
    }

    public int getCountOfUnassignedOrders() {
        int countOdrs = orderRepository.getCountOfUnassignedOrders();
        return countOdrs;
    }

    public String deletePartnerById(String partnerId) {
        String result = orderRepository.deletePartnerById(partnerId);
        return result;
    }

    public String deleteOrderById(String orderId) {
        String result = orderRepository.deleteOrderById(orderId);
        return result;
    }
}
