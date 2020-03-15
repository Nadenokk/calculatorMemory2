package solver;

import exception.DivideByZeroException;
import exception.MathException;
import exception.SyntaxErrorException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticSolver {
    /*
     * Парсим числа и операторы
     */
    private String equation;

    private BigDecimal answer;

    private ArrayList<BigDecimal> numbers = new ArrayList<>();// для цифр
    private ArrayList<Operator> operators = new ArrayList<>();// для операторов

    public ArithmeticSolver(String equation) throws MathException {
        this.equation = equation;
        solve();
    }

    private void solve() throws MathException{
        System.out.println("at the beginning "+ equation);// отладка

        extractEquationToNumbersAndOperators();
        System.out.println("numbers: " + numbers.toString());// отладка
        System.out.println("operator: " + operators.toString());// отладка
        calculate();
    }

    private void extractEquationToNumbersAndOperators() throws SyntaxErrorException {
        System.out.println("execute "+ equation);

        boolean isNegative = false;// отрицательность числа
        Integer numStartIndex = null;// начало цифры

        /*
         * Пробегаем числа и считываем операции
         */
        for (int i = 0; i < equation.length(); i++) {
            char oneChar = equation.charAt(i);

            /*
             * Если число начинается с "."
             */
            if(Character.isDigit(oneChar) || oneChar == '.'){
                if(numStartIndex == null){
                    numStartIndex = i;
                }
            }

            /*
             * Если число начинается с минуса,
             * то ставим булевую переменную isNegative в true
             * Если начинается с плюса, то продолжаем
             */
            else if(Operator.isCalculationOperator(oneChar)){
                if (i == 0) {
                    if (oneChar == '-') {
                        isNegative = true;
                        continue;
                    } else if (oneChar == '+') {
                        continue;
                    } else if(Operator.isTimesOrDivide(oneChar)) {
                        throw new SyntaxErrorException();
                    }
                }

                /*
                 * Если число не начинается с нуля,
                 * то обращаемся к предыдущему char
                 * и рассматриваем его аналогично
                 */
                if(i != 0) {
                    char previousChar = equation.charAt(i - 1);
                    if (Operator.isCalculationOperator(previousChar)) {
                        if (oneChar == '-') {
                            isNegative = true;
                            continue;
                        } else if (oneChar == '+') {
                            continue;
                        } else {
                            throw new SyntaxErrorException();
                        }
                    }
                }
                assert numStartIndex != null;// проверка на наличие числа после всех операций

                /*
                 * Считываем число и проверяем на ошибку
                 */
                BigDecimal number;
                try{
                    number = new BigDecimal(equation.substring(numStartIndex, i));
                } catch (NumberFormatException e){
                    throw new SyntaxErrorException();
                }
                numbers.add((isNegative)? (number.multiply(new BigDecimal(-1))): number);// проверяем на отрицательность
                isNegative = false;
                numStartIndex = null;// отработали число и снова зануляем переменную начала числа
                operators.add(Operator.getOperator(oneChar));
            }
            else{
                throw new IllegalArgumentException();
            }
        }

        /*
         * Ошибка, что нет второго числа
         */
        if(numStartIndex == null){
            throw new SyntaxErrorException();
        }
        BigDecimal number;// второе число
        try{
            number = new BigDecimal(equation.substring(numStartIndex));
        } catch (NumberFormatException e){
            throw new SyntaxErrorException();
        }
        numbers.add((isNegative)? (number.multiply(new BigDecimal(-1))): number);// на отрицательность проверяем
    }

    /*
     * Считываем оператор и выполняем соотвествующее действие
     */
    private void calculation(List<Operator> allowedOperator) throws DivideByZeroException {
        for (int i = 0; i < operators.size(); i++) {
            Operator operator = operators.get(i);
            if(allowedOperator.contains(operator)){
                numbers.set(i, operator.calculate(numbers.get(i), numbers.get(i + 1)));
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    /*
     * Если по каким-то причинам после выполнения всех действий не получилась 1 при уменьшении i
     */
    private void storeAns(){
        if(numbers.size() != 1){
            throw new IllegalStateException("program have bug(s)...");
        }
        answer = numbers.get(0);
    }

    /*
     * Вызываем операторы
     */
    private void calculate() throws DivideByZeroException {
        calculation(Arrays.asList(Operator.times,Operator.divide,Operator.add, Operator.minus));
        storeAns();
    }

    public String getAnswer(){
        return answer.toString();
    }
}
