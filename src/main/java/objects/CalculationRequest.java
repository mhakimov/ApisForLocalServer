package objects;

public class CalculationRequest {
    public double number1;
    public double number2;
    public String operation;

    public CalculationRequest(double number1, double number2, String action){
        this.number1 = number1;
        this.number2 = number2;
        operation = action;
    }

    public CalculationRequest(){}
}
