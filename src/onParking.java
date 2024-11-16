public class onParking implements State {
    @Override
    public void onChangeState(Car car) {
        car.setCarState();
    }

    @Override
    public void onParking(Car car) {}

    @Override
    public void onRoute(Car car) {}
}
