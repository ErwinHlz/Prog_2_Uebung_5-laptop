import java.util.function.Predicate;

public class FunctionApp {

    private static class Faculty{

        private int calculateFaculty(int x){


            int result = 1;
            for (int i=0;i < x ;i++){
                result *= i+1;
            }
            return result;

        }

    }
    public static void main(String[] args) {

        // x² als Lamba Ausdruck

        System.out.printf("%n");
        System.out.println("\u001B[35mALLE FUNKTIONEN MIT LAMDA-AUSDRÜCKEN:");

        System.out.println("\u001B[36mi) Funktion: x² = \u001B[38m");
        applyAndPrint(0,90, ((x -> x * x)));

        // Fakultät als Lambda Ausdruck
        // for Schleife multipliziert vorherigen wert immer mit dem nÄchsten

        System.out.println("\u001B[36mii) Fakultät: x! = \u001B[38m");
        applyAndPrint(0,10, x -> {
            int result = 1;
            for (int i=0;i < x ;i++){
                result *= i+1;
            } return result;
        });

        // x^x+1 als Lamda Ausdruck
        // Methode Math.pow für Potenzrechnung

        System.out.println("\u001B[36miii) Funktion: x^(x+1) = \u001B[38m");
        applyAndPrint(0,10, x -> (int) Math.pow(x,x+1));

        // Fibonacci-Folge als Lamda Ausdruck
        /* Startwerte werden festgelegt und danach wird result3 aus result2 und result ausgerechnet, dann werden alle
           Werte um eine Position verschoben
        */

        System.out.println("\u001B[36miiii) Funktion: fib(x) = \u001B[38m");
        applyAndPrint(0,10, x -> {
            int result = 0;
            int result2 = 1;
            int result3 = 1;
            for (int i=1; i<x;i++){
                result3 = result2 + result;
                result = result2;
                result2 = result3;
            } if(x == 0){result3 = 0;}
            return result3;
        });
        System.out.printf("%n%n");

        //alle Funktionen als anonyme Klasse

        MyFunction squareFunction = new MyFunction() {
            @Override
            public int apply(int x) {
                return x * x;
            }
        };

        MyFunction facultyFunction = new MyFunction() {
            @Override
            public int apply(int x) {
                int result = 1;
                for (int i=0;i < x ;i++){
                    result *= i+1;
                }
                return result;
            }
        };

        MyFunction powerFunction = new MyFunction() {
            @Override
            public int apply(int x) {
                return (int) Math.pow(x,x+1);
            }
        };

        AdvancedFunction fibonacciFunction = new AdvancedFunction() {

            @Override
            public int apply(int x) {

                int result = 0;
                int result2 = 1;
                int result3 = 1;


                for (int i=1; i<x;i++){
                    result3 = result2 + result;
                    result = result2;
                    result2 = result3;

                } if(x == 0){result3 = 0;}
                return result3;
            }
        };

        // applyAndPrint aufruf mit den anonymen Klasse

        System.out.println("\u001B[35mALLE FUNKTIONEN ALS ANONYME KLASSE:");
        System.out.println("\u001B[36mFunktion: x² = \u001B[38m");
        applyAndPrint(0,10,squareFunction);

        System.out.println("\u001B[36mFunktion: x! = \u001B[38m");
        applyAndPrint(0,10,facultyFunction);

        System.out.println("\u001B[36mFunkion: x^(x+1) = \u001B[38m");
        applyAndPrint(0,10,powerFunction);

        System.out.println("\u001B[36mFunktion: fib(x) = \u001B[38m");
        applyAndPrint(0,10,fibonacciFunction);

        System.out.printf("%n%n");

        // MyFunction implementieren mit der inner class


        MyFunction innerFaculty = new MyFunction() {
            @Override
            public int apply(int x) {
                Faculty iF = new Faculty();
                return iF.calculateFaculty(x);
            }
        };

        // MyFunction implementieren mit der outer class

        MyFunction topLevelFaculty = new MyFunction() {
            @Override
            public int apply(int x) {
                ToplevelFaculty tF = new ToplevelFaculty();
                return tF.calculateFaculty(x);
            }
        };

        //Aufruf der applyAndPrint Methode mit inner und outer class


        System.out.println("\u001B[36mFunktion: x! als Innere \u001B[38m");
        applyAndPrint(0,10,innerFaculty);
        System.out.println("\u001B[36mFunktion: x! als Top-Level-Klasse: \u001B[38m");
        applyAndPrint(0,10,topLevelFaculty);


        // Implementeirung des odd Predicate, wenn ungerade gibt true


        Predicate<Integer> odd = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer%2 != 0;
            }
        };

        // Implementeriung des even Predicate, wenn gerade gibt true

        Predicate<Integer> even = integer -> integer%2 == 0;

        // squareFunction als Typ von AdvancedFunction, um die conditionateInput methode aufzurufen
        AdvancedFunction squareFunction2 = x -> x * x;


        //Aufruf der applyAndPrint Methode mit squareFunction und unseren Predicates
        System.out.println("\u001B[36mFunktion: x² aber mit geradem x:\u001B[00m");
        applyAndPrint(0,10,squareFunction2.conditionateInput(even));

        // FacultyFunction als Typ AdvancedFunction, um die conditionateInput Methode aufzurufen
        AdvancedFunction facultyFunction2 = new AdvancedFunction() {
            @Override
            public int apply(int x) {
                int result = 1;
                for (int i=0;i < x ;i++){
                    result *= i+1;
                }
                return result;
            }
        };

        // Aufruf der applyandPrint Methode aber nur ungerade results werden ausgegeben
        System.out.println("\u001B[36mFunktion: x! aber mit ungeradem result: \u001B[00m");
        applyAndPrint(0,100,facultyFunction2.conditionateOutput(odd));
        System.out.printf("%n\u001B[36mFunktion: fib(x) aber mit ungeradem result: \u001B[00m%n");
        applyAndPrint(0,10,fibonacciFunction.conditionateOutput(odd));



    }

    public static void applyAndPrint(int i, int j, MyFunction function){


        //wenn i oder j kleiner 0 wird eine exception geworfen, da unser Definitionsbereich in N liegt

        if (i<0 || j<0){
            throw new IllegalArgumentException("i and j must be natural numbers");
        }

        // ruft die apply Methode von i bis j auf und printed die Ausgabe

        for (int x=i; x<=j;x++){
            int result = function.apply(x);

            if (result == -1 ){

            } else if (x == j) {
                System.out.println(" f(" + x +") = " + "\u001B[32m" + result + "\u001B[38m");

            } else if (x%10 == 0 && x != 0) {
                System.out.printf(" f(" + x +") = " + "\u001B[32m"  + result + "\u001B[38m" + "; %n");


            } else {
                System.out.print(" f(" + x +") = " + "\u001B[32m"  + result + "\u001B[38m" + ";");
            }

        }

    }
}
