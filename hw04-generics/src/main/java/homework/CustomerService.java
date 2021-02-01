package homework;


import java.util.*;

public class CustomerService {
    private Map<Customer, String> map = new HashMap<>();

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны


    public Map<Customer, String> getMap() {
        return map;
    }

    public Map.Entry<Customer, String> getSmallest() {
        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> smallest = map.entrySet().stream()
                .min(Comparator.comparingLong(o -> (o.getKey().getScores())))
                .orElse(null);
        System.out.println(map);
        Customer customerCopy = new Customer(smallest.getKey().getId(), smallest.getKey().getName(), smallest.getKey().getScores());
        return new SimpleMapEntry(customerCopy, smallest.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return map.entrySet().stream()
                .filter(o -> o.getKey().getScores() > customer.getScores())
                .findFirst()
                .orElse(null);
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}

class SimpleMapEntry implements Map.Entry<Customer, String> {
    private Customer customer;
    private String value;

    public SimpleMapEntry(Customer customer, String value) {
        this.customer = customer;
        this.value = value;
    }

    @Override
    public Customer getKey() {
        return customer;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        this.value = value;
        return value;
    }

}
