package solver;

import exception.DivideByZeroException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Operator {
    add, minus, times, divide;

    public static boolean isCalculationOperator(char c){
        return (c == '+' || c == '-' || c == '×' || c == '÷');
    }
    public static boolean isTimesOrDivide(char c){
        return ( c == '×' || c == '÷' );
    }

    public static Operator getOperator(char c){
        /*
        * Читаем знак
         */
        switch (c){
            case '+': return add;
            case '-': return minus;
            case '×': return times;
            case '÷': return divide;
            default: throw new IllegalArgumentException();
        }
    }

    public BigDecimal calculate(BigDecimal a, BigDecimal b) throws DivideByZeroException {
        /*
        * Подбор знака и операции
         */
        switch (this){
            case add: return a.add(b);
            case minus: return  a.subtract(b);
            case times: return a.multiply(b);
            case divide: return division(a, b);
            default: throw new IllegalStateException();
        }
    }

    private BigDecimal division(BigDecimal a, BigDecimal b) throws DivideByZeroException {
        /*
        * Проверка деления на 0
         */
        if(b.equals(new BigDecimal(0))){
            throw new DivideByZeroException();
        }

        try{
            return a.divide(b);
        }
        catch (ArithmeticException e){
            /*
            * Минимум 8 знаков после запятой
             */
            return a.divide(b,8, RoundingMode.HALF_UP);
        }
    }
}
