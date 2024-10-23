public class Car {
    private int id;
    private String name;
    private String state;
    private String number;
    //private transient State truckState;

    @Override
    public String toString() {
        return "Car " +
                "id=" + id +
                " name='" + name + '\'' +
                " state='" + state + '\'' +
                " number='" + number + '\'';
    }

    public Car(int id, String name, String state, String number) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.number = number;
        //this.truckState = truckState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
