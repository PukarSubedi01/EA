package jms;

public class Calculator {
    private Integer number;
    private Character operator;

    public Calculator(Integer number, Character operator) {
        this.number = number;
        this.operator = operator;
    }

    public Calculator(){}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }
    public int calculate(Integer previousValue) {
        switch (operator) {
            case '+':
                previousValue = previousValue + number;
                break;
            case '-':
                previousValue = previousValue - number;
                break;

            case '*':
                previousValue = previousValue * number;

        }
        return  previousValue;
    }

}
