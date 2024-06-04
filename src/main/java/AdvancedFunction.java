import java.util.function.Predicate;

public interface AdvancedFunction extends MyFunction{

    //default Implementierung damit man es nicht mehr implementieren kann/muss
    // conditionateInput macht mit predicate<Integer> test, ob typ vom input int ist
    // conditionateOutput macht mit predicate<Integer> test, ob typ vom output int ist
    default AdvancedFunction conditionateInput(Predicate<Integer> predicate){
        return n -> {
            if (predicate.test(n)){
                return apply(n);
            } else {
                return -1;
            }
        };

    }

    default AdvancedFunction conditionateOutput(Predicate<Integer> predicate){

        return n -> {
            if (predicate.test(apply(n))){
                return apply(n);
            } else {
                return -1;
            }
        };
    }

}
