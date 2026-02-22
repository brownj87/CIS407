//Jordan Brown, CIS 407, Stack Calculator
package stackCalculator;
import java.util.Stack;
public class StackCalculator {
    private Stack<Double> calculator;
    public StackCalculator() {
        calculator = new Stack<>();
    }
    
    public void push(double value) {
        calculator.push(value);
    }
    
    public double pop() {
        return calculator.pop();
    }
    
    public int size() {
        return calculator.size();
    }
    
    public void clearStack() {
        calculator.clear();
    }
    
    public void listStack() {
        if (calculator.isEmpty()) {
            System.out.println("empty");
        } else {
            for (Double value : calculator) {
                System.out.println(value);
            }
        }
    }
}
