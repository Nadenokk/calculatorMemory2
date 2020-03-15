package Main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.ls.LSOutput;
import solver.Solver;

public class Controller {
/**
* Описываю переменные ввода и вывода текста
* булевые переменные на нажатие "=" и наличие на дисплее записи
 */
    @FXML
    private TextField outputField;

    @FXML
    private TextField inputField;

    @FXML
    private TextField z;

    @FXML
    private TextField toBinary;




    private boolean isDisplayingAns = false;
    private boolean isEqualLabel = true;
    private boolean isError = false;
    private boolean point = false;

    TextField getOutputField() {
        return outputField;
    }

    /*
    *Выполнение математческих операций
    * Нажатие на кнопки цифр
    * Операции с 3 перменными из памяти
     */
    @FXML
    void pressing1Btn() { presKey('1'); }

    @FXML
    void pressing2Btn() { presKey('2'); }

    @FXML
    void pressing3Btn() { presKey('3');}

    @FXML
    void pressing4Btn() { presKey('4');}

    @FXML
    void pressing5Btn() { presKey('5');}

    @FXML
    void pressing6Btn() { presKey('6');}

    @FXML
    void pressing7Btn() { presKey('7');}

    @FXML
    void pressing8Btn() { presKey('8');}

    @FXML
    void pressing9Btn() { presKey('9');}

    @FXML
    void pressing0Btn() {
        presKey('0');
    }

    @FXML
    void dot() {
        reset();
        if (!point) {
            outputField.setText(outputField.getText() + ".");
            point = true;
        }
    }

    @FXML
    void plusminus() {
        //reset();
        if (outputField.getText(0,1).contains("-")){
            outputField.deleteText(0,1);
        } else {
            outputField.setText("-" + outputField.getText());
        }
    }

    @FXML
    void add() {
        equal();
        inputField.setText(outputField.getText());
        Binarycard();
        inputField.setText(outputField.getText() + "+");
        outputField.setText("0");
        point = false;
    }

    @FXML
    void minus() {
        equal();
        inputField.setText(outputField.getText());
        Binarycard();
        inputField.setText(outputField.getText() + "-");
        outputField.setText("0");
        point = false;
    }

    @FXML
    void divide() {
        equal();
        inputField.setText(outputField.getText());
        Binarycard();
        inputField.setText(outputField.getText() + "÷");
        outputField.setText("0");
        point = false;
    }

    @FXML
    void times() {
        equal();
        inputField.setText(outputField.getText());
        Binarycard();
        inputField.setText(outputField.getText() + "×");
        outputField.setText("0");
        point = false;
    }

    @FXML
    void equal() {
        String q = (inputField.getText() +outputField.getText());
        Solver solver = new Solver(q);
        String s = solver.getAnswer();
        outputField.setText(s);
        BinarycardAfterSolve();
        point = true;
        inputField.setText("0");


        if (!isEqualLabel) {
            isDisplayingAns = false;
        } else {
            isDisplayingAns = true;
        }
        isEqualLabel=true;
        if (s.contains("Error")){
            isError = true;
        }
    }

    @FXML
    void clear() {
        outputField.setText("0");
        inputField.setText("0");
        Binarycard();
        point = false;
    }




  /*  void Binarycard () {
        String s = inputField.getText();
        int d = Integer.parseInt(s);
        toBinary.setText(Integer.toBinaryString(d));
        toBinary.getText();
        point = true;
        isDisplayingAns = true;

    }*/

  void Binarycard () {
      double a = Double.parseDouble(inputField.getText());
      double b = Math.abs(a);
      int left = (int) b;
      if (b == left)
      {
          toBinary.setText(Integer.toBinaryString(left));
      }
      else {
          String bin = "";
          bin = Integer.toBinaryString(left) + bin;
          bin += ".";
          double right = (float) b - (int) b;
          for (int i = 0; i < 5; i++) {
              right = right * 2 - (int) right * 2;
              bin = bin + (int) right;
              if (right == 1.0) {
                  break;
              }
          }
          toBinary.setText(bin);
      }
      if (a >= 0){
          toBinary.getText();
      }
      else {
          toBinary.setText("-" + toBinary.getText());
      }
      toBinary.getText();
  }


   /* void BinarycardAfterSolve () {
        String s = outputField.getText();
        int d = Integer.parseInt(s);
        toBinary.setText(Integer.toBinaryString(d));
        toBinary.getText();
        point = true;
        isDisplayingAns = true;

    }*/
   void BinarycardAfterSolve () {
       double a = Double.parseDouble(outputField.getText());
       double b = Math.abs(a);
       int left = (int) b;
       if (b == left)
       {
           toBinary.setText(Integer.toBinaryString(left));
       }
       else {
           String bin = "";
           bin = Integer.toBinaryString(left) + bin;
           bin += ".";
           double right = (float) b - (int) b;
           for (int i = 0; i < 5; i++) {
               right = right * 2 - (int) right * 2;
               bin = bin + (int) right;
               if (right == 1.0) {
                   break;
               }
           }
           toBinary.setText(bin);
       }
       if (a >= 0){
           toBinary.getText();
       }
       else {
           toBinary.setText("-" + toBinary.getText());
       }
       toBinary.getText();
        }


    private void reset(){
        if(isDisplayingAns){
            outputField.setText("0");
            point = false;
            isDisplayingAns = false;
        }
        if (isError){
            inputField.setText("0");
            isError=false;
        }
    }

    private void presKey (final char c){
        reset();
        String s = outputField.getText();
        if (s.equals("0")) outputField.setText("" + c);
        else if (s.equals("-0")) outputField.setText("-" + c);
        else outputField.setText( s + c);
    }

}

