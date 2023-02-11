package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

@Repository
public class OrderRepository {

    HashMap<String, List<String>> db1 = new HashMap<>(); // store the pair
    HashMap<String, String> db2 = new HashMap<>(); // assigned order(OrderId, PartnerId)
    HashMap<String, Order> db3 = new HashMap<>();// store the order
    HashMap<String, DeliveryPartner> db4 = new HashMap<>(); // store the delivery partner



    public String addOrder(Order order) {
        db3.put(order.getId(), order);
        return "Added Successfully";
    }

    public String addPartner(String partnerId) {
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        db4.put(partnerId, partner);
        return "Added Successfully";
    }

    public String addOrderPartnerPair(String orderId, String partnerId) {
        List<String> l = db1.getOrDefault(partnerId, new ArrayList<>());
        l.add(orderId);
        db1.put(partnerId, l);
        db2.put(orderId, partnerId);
        DeliveryPartner partner = db4.get(partnerId);
        partner.setNumberOfOrders(l.size());
        return "Added Successfully";

    }

    public Order getOrderById(String orderId) {
        for (String str : db3.keySet()) {
            if (str.equals(orderId)) {
                return db3.get(str);
            }
        }
        return null;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        if (db4.containsKey(partnerId)) {
            return db4.get(partnerId);
        }
        return null;

    }

    public int getOrderCountByPartnerId(String partnerId) {
        int odrs = db1.getOrDefault(partnerId, new ArrayList<>()).size();
        return odrs;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> odrs = db1.getOrDefault(partnerId, new ArrayList<>());
        return odrs;
    }

    public List<String> getAllOrders() {
        List<String> odrs = new ArrayList<>();
        for (String str : db3.keySet()) {
            odrs.add(str);
        }
        return odrs;

    }

    public int getCountOfUnassignedOrders() {
        // Count of orders assign by DeliveryPartner
        int countOdrs = db3.size() - db2.size();
        return countOdrs;
    }

    public String deletePartnerById(String partnerId) {
        // Delete the partnerId
        db4.remove(partnerId);

        List<String> l = db1.getOrDefault(partnerId, new ArrayList<>());
        ListIterator<String> itr = l.listIterator();
        while (itr.hasNext()) {
            String str = itr.next();
            db2.remove(str);
        }
        db1.remove(partnerId);
        return "Deleted from db Successfully";
    }

    public String deleteOrderById(String orderId) {

        // Delete an order and also remove partnerId from the assigned order database(db2)
        db3.remove(orderId);
        String partnerId = db2.get(orderId);
        db2.remove(orderId);
        List<String> l = db1.get(partnerId);

        ListIterator<String> itr = l.listIterator();
        while (itr.hasNext()) {
            String str = itr.next();
            if (str.equals(orderId)) {
                itr.remove();
            }
        }
        db1.put(partnerId, l);

        return "Deleted from db Successfully";
    }
}
