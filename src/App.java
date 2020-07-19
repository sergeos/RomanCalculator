import java.util.Scanner;
import java.text.DecimalFormat;

public class App {
    public static void main(String[] args) {
        
        final String operands = "+-*/";
        
        //String expSample = "IV*  x".trim();
        // String[] arrNumbers; // = expSample.split("[\\+\\-\\*\\/]");

        Scanner sc = new Scanner(System.in);
        String expSample;

        System.out.println("Enter expression for calculate:");
        expSample = sc.nextLine();
        
        sc.close();

        try {
            if (expSample.length() < 3) {
                throw new Exception("Expression is invalid!");
            }
            boolean b = false;
            for (int i = 1; i < expSample.length()-1; i++) {
    
                if (operands.indexOf(expSample.substring(i, i+1)) > -1) {
                    String[] arrNumbers = new String[3];
                    arrNumbers[0] = expSample.substring(0, i).trim();;
                    arrNumbers[1] = expSample.substring(i, i+1);
                    arrNumbers[2] = expSample.substring(i+1, expSample.length()).trim();

                    NumberSystem fOperandNS = new NumberSystem(arrNumbers[0]);
                    NumberSystem sOperandNS = new NumberSystem(arrNumbers[2]);
                    
                    // проверяем, соответствуют ли системы счисления друг другу
                    if (fOperandNS.numSystem != sOperandNS.numSystem) {
                        throw new Exception("Number Systems is not same!");
                    } else {
                        final double fValue;
                        final double sValue;
                        
                        // проверяем значение на допустимый диапазон (1 - 10)
                        // если СС римская, то перводим в арабские числа
                        if (fOperandNS.numSystem == "R") {
                            RomanNumeral N = new RomanNumeral(arrNumbers[0]);
                            fValue = N.toInt();
                            N = new RomanNumeral(arrNumbers[2]);
                            sValue = N.toInt();
                        } else {
                            fValue = Integer.parseInt(arrNumbers[0]);
                            sValue = Integer.parseInt(arrNumbers[2]);
                        }
        
                        if (!(fValue > 0 && fValue < 11) && (sValue > 0 && sValue < 11)) {
                            throw new Exception("Value not in range (1 - 10)");
                        } else {
                            double resultInt = 0;
                            if (arrNumbers[1].equals("+")) {
                                resultInt = fValue + sValue;
                            } else if (arrNumbers[1].equals("-")) {
                                resultInt = fValue - sValue;
                            } else if (arrNumbers[1].equals("*")) {
                                resultInt = fValue * sValue;
                            } else if (arrNumbers[1].equals("/")) {
                                resultInt = fValue / sValue;
                            }

                            if (fOperandNS.numSystem == "R") {
                                if (resultInt < 1) {
                                    throw new Exception("Result less than 1 can't be present in Roman numbers!");
                                } else {
                                    RomanNumeral N = new RomanNumeral((int)(resultInt));
                                    System.out.println(N.toString());   
                                }
                                
                            } else {
                                DecimalFormat decimalFormat=new DecimalFormat("#.#");
                                System.out.println(decimalFormat.format(resultInt));
                            }   
                        }
                    }

                    b = true;
                    break;
                }
            }
            
            if (!b)
                throw new Exception("Expression is invalid!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
