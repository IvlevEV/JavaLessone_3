package Lessone01;

public class Apple extends Fruit {

    private static final Float Apple_weight = 1.0F;

    Apple() {
        super(Apple_weight);
    }

    @Override
    public String toString(){
        return "Яблоко эдэма";
    }
}