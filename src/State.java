public interface State {
    void onChangeState(Car car);
    void onParking(Car car);
    void onRoute(Car car);
}
