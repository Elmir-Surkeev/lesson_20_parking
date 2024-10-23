public class Car {
    private int id;
    private String name;
    private String state;
    private String number;
    private transient State carState;


    public void changeState(String state) {
        try {
            this.state = state;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCarState(){
        switch (this.state){
            case "onRoute" -> this.carState = new onParking();
            case "onParking" -> this.carState = new onRoute();
        }
    }

    public State getCarState() {
        return carState;
    }

    public void setCarState(State carState) {
        this.carState = carState;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return " Car " +
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
