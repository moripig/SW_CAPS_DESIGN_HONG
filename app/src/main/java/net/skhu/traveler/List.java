package net.skhu.traveler;

public class List {
    String destination;
    String address;

    public List(String destination,String address) {
        this.destination = destination;
        this.address = address;
    }

    public String getDestination() {
        return destination;
    }
    public  void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
