package exception;
/*
 * Математическая ошибка
 */
public class DivideByZeroException extends MathException{
    @Override
    public String getWarning() {
        return "Math Error";
    }
}
