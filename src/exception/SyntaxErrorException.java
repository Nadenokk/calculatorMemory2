package exception;
/*
 * Синтаксическая ошибка
 */
public class SyntaxErrorException extends MathException {
    @Override
    public String getWarning() {
        return "Syntax Error";
    }
}
