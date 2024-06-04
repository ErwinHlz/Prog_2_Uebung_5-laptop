public class ToplevelFaculty {
    public int calculateFaculty(int x) {
        int result = 1;
        for (int i=0;i < x ;i++){
            result *= i+1;
        }
        return result;
    }
}
