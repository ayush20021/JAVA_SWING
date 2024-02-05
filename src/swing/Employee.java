package swing;

public class Employee {

    private String Name;
    private  String Sal;
    private String  Address;

    public Employee(String name, String sal, String address) {
        Name = name;
        Sal = sal;
        Address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name='" + Name + '\'' +
                ", Sal='" + Sal + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSal() {
        return Sal;
    }

    public void setSal(String sal) {
        Sal = sal;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
