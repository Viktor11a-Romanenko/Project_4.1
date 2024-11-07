package Lerning;
/*
Метод для проверки, является ли число четным.
 */
public class NumberUtils {
    public boolean isEven (int number){
        return number % 2 == 0;
    }

    public static void main(String[] args) {
        NumberUtils utils = new NumberUtils();
        int numberToCheck = 35;
        boolean result = utils.isEven(numberToCheck);
        System.out.println(result + " " + numberToCheck);
    }
}
