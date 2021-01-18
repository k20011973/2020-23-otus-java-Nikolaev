package homework;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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
        Customer customerCopy = new Customer(smallest.getKey().getId(), smallest.getKey().getName(), smallest.getKey().getScores());
        String valCopy = smallest.getValue();
        return new Map.Entry<Customer, String>() {
            @Override
            public Customer getKey() {
                return customerCopy;
            }

            @Override
            public String getValue() {
                return valCopy;
            }

            @Override
            public String setValue(String value) {
                return null;
            }
        };
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
