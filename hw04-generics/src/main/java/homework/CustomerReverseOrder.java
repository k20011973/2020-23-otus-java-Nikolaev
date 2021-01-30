package homework;


import java.util.LinkedList;
import java.util.Queue;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    LinkedList<Customer> linkedList = new LinkedList<>();

    public void add(Customer customer) {
        linkedList.offer(customer);
    }

    public Customer take() {
        return linkedList.removeLast();
    }
}
