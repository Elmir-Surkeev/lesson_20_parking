public interface State {
    public void onChangeState(Car car);
    public void onParking(Car car);
    public void onRoute(Car car);
}
