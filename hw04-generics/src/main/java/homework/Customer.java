package homework;

import java.util.Objects;

public class Customer {
    private final long id;
    private String name;
    private long scores;

    //todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    /*
        Изменил:
        - hashCode():
            закомментировал изменяемые поля (name, scores)
        - equals():
            ? нужно ли комментировать изменяемые поля (name, scores)
            1. согластно:
                https://yandex.ru/turbo/internet-technologies.ru/s/articles/sravnenie-obektov-java-s-pomoschyu-equals-i-hashcode.html
                equals() сравнивает значения атрибутов объектов -> Если два объекта имеют одинаковые значения полей, то объекты одинаковы.
            2. согластно:
                https://stepik.org/lesson/12769/step/5?after_pass_reset=true&unit=3117
                Метод equals() предназначен для сравнения объектов по содержимому к дополненю к сравнению по ссылке (==)
                Объект сам контролирует свое состояние и обеспечивает его корректность
                !!! СУЩЕСТВУЕТ ТРЕБОВАНИЕ НА СОГЛАСОВАННОСТЬ РЕАЛИЗАЦИИ equals() и hashCode() !!!:
                    Если два объекта равны по equals они должны иметь одинаковый hashCode
            Вывод:
                2. =>  закомментировал изменяемые поля (name, scores)
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
//        if (scores != customer.scores) return false;
//        return name != null ? name.equals(customer.name) : customer.name == null;
        return true;
    }

    @Override
    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (int) (scores ^ (scores >>> 32));
//        int result = (int) id;
        return Objects.hash(id);
    }

}
