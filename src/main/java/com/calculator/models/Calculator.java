package com.calculator.models;

import java.util.*;

public class Calculator {
    private List<Double> listOfNumbers = new ArrayList<Double>();

    private List<String> listOfOperators = new ArrayList<String>();

    private Map<String, Integer> priorityOfOperators = new HashMap<String, Integer>();
private String mathProblem="";
private double result=0.0;

    public double getResult() {
        return result;
    }

    public Calculator(String mathProblem) {
        this.mathProblem=mathProblem;
        this.priorityOfOperators.put("+", 1);
        this.priorityOfOperators.put("-", 1);
        this.priorityOfOperators.put("/", 2);
        this.priorityOfOperators.put("*", 2);

    }

    private void fillLists(String mathProblem) {
        String[] text = mathProblem.split(" ", -1);
        for (String c : text) {
            if (isDigit(c)) {
                this.listOfNumbers.add(Double.parseDouble(c));
            } else if (isOperator(c)) {
                if (c.equals("(")|| this.listOfOperators.isEmpty() || this.listOfOperators.get(this.listOfOperators.size() - 1) .equals( "(")) {
                    this.listOfOperators.add(c);
                } else {
                    while (isHigherPriority(c)) {

                        this.listOfNumbers.add(doOperation());
                        this.listOfOperators.remove(this.listOfOperators.size() - 1);

                    }
                    this.listOfOperators.add(c);
                }
            }
            if (c .equals( ")")) {
                doOperationInBracket();


            }


        }


    }

   private boolean isHigherPriority(String operator) {
        if (this.listOfOperators.isEmpty() || this.listOfOperators.get(this.listOfOperators.size() - 1) .equals( "("))
            return false;
        else if
        (priorityOfOperators.get(operator) <= priorityOfOperators.get(this.listOfOperators.get(this.listOfOperators.size() - 1)))
            return true;
        else return false;
    }

   private void doOperationInBracket() {
 if(this.listOfOperators.size()!=0)
 {
        do {
            this.listOfNumbers.add(doOperation());
            this.listOfOperators.remove(this.listOfOperators.size() - 1);
        }
        while (!(this.listOfOperators.get(this.listOfOperators.size() - 1)).equals( "("));
        this.listOfOperators.remove(this.listOfOperators.size() - 1);

    }

    }

   private double doOperation() {
        double result = 0.0;



           switch (this.listOfOperators.get(this.listOfOperators.size() - 1)) {
               case "+":
                   result = this.listOfNumbers.get(this.listOfNumbers.size() - 2) + this.listOfNumbers.get(this.listOfNumbers.size() - 1);
                   break;
               case "-":
                   result = this.listOfNumbers.get(this.listOfNumbers.size() - 2) - this.listOfNumbers.get(this.listOfNumbers.size() - 1);
                   break;
               case "/":
                   result = this.listOfNumbers.get(this.listOfNumbers.size() - 2) / this.listOfNumbers.get(this.listOfNumbers.size() - 1);
                   break;
               case "*":
                   result = this.listOfNumbers.get(this.listOfNumbers.size() - 2) * this.listOfNumbers.get(this.listOfNumbers.size() - 1);
                   break;


               default:
                   throw new IllegalStateException("Unexpected value: " + this.listOfOperators.get(this.listOfOperators.size() - 1));
           }
           this.listOfNumbers.remove(this.listOfNumbers.size() - 1);
           this.listOfNumbers.remove(this.listOfNumbers.size() - 1);



       return result;
    }

 private boolean isDigit(String character) {
        try {
            Double.parseDouble(character);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }



    private boolean isOperator(String character) {
        return character .equals("+") || character .equals("-")   || character .equals("/")|| character .equals("*") || character .equals("(") ;
    }

  public String calculate() {

        fillLists(this.mathProblem);
        if(this.listOfOperators.size()!=0 && this.listOfNumbers.size()!=0 ) {

            while (this.listOfNumbers.size() != 1) {
                this.listOfNumbers.add(doOperation());
                this.listOfOperators.remove(this.listOfOperators.size() - 1);
            }
            this.result = this.listOfNumbers.get(this.listOfNumbers.size() - 1);
            return String.valueOf(this.result);
        }
      if(this.listOfNumbers.size()==1){this.result=this.listOfNumbers.get(this.listOfNumbers.size() - 1); return String.valueOf(this.result);}
return "Wrong format";
    }
}
