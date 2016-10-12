package com.gaoyang.test.equals_hashcode.bean;

public class Person2 {
    private String name;
    private String address;
    private String number;

    public Person2(String name, String address, String number) {
        this.address = address;
        this.name = name;
        this.number = number;
    }
      
    public String getName() {  
        return name;
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getAddress() {  
        return address;  
    }  
    public void setAddress(String address) {  
        this.address = address;  
    }  
    public String getNumber() {  
        return number;  
    }  
    public void setNumber(String number) {  
        this.number = number;  
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person2 person2 = (Person2) o;

        if (name != null ? !name.equals(person2.name) : person2.name != null) return false;
        if (address != null ? !address.equals(person2.address) : person2.address != null) return false;
        return number != null ? number.equals(person2.number) : person2.number == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}