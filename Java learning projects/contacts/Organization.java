package contacts;


public class Organization extends Contact {
    private String name;
    private String address;

    public Organization(){super();}

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

    @Override
    public void getInfo() {
        System.out.println("Organization name: " + this.getName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Number: " + this.getPhoneNumber());
        System.out.println("Time created: " + this.getCreationTime());
        System.out.println("Time last edit: " + this.getLastEditTime());
    }

    @Override
    public String getFields() {
        return "name, address, number";
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name" :
                setName(value);
                break;
            case "address" :
                setAddress(value);
                break;
            case "number" :
                setPhoneNumber(value);
                break;
            default:
                System.out.println("There is no such field!");
                break;
        }
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + address + " " + this.getPhoneNumber();
    }
}