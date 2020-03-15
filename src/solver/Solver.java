package solver;

import exception.MathException;

public class Solver {

    private String equation;
    private String answer;
    private MathException exception = null;

    public Solver(String equation){
        this.equation = equation;
        solve();
    }

    private void solve(){
        // проверяет ввод
        if(equation.equals("")){
            answer = "";
            return;
        }
        try {
            calculate();
        } catch (MathException e) {
            exception = e;
        }
    }



    private void calculate() throws MathException{
        // считает
        ArithmeticSolver solver = new ArithmeticSolver(equation);
        answer = solver.getAnswer();
    }

    public String getAnswer(){
        // проверяет ответ
        if(exception == null){
            return answer;
        }
        return exception.getWarning();
    }

}
