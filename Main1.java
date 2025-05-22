public class Main {
    public static void main(String[] args) {

        //Given list of numbers
        int[]numbers={83,46,13,37,22,72,91,55,51,69};
        int target=155;
        int count=PlusMinusSequences.countWays(numbers,target,0,0);
        System.out.printf(" Number of ways to reach : %d is  : %d times",target,count);
        System.out.println();
        //Display all the comibinations that sum to the target
        System.out.printf("Combos  that sum to  %d",target);
        System.out.println();
        PlusMinusSequences.findCombos(numbers,target,0,0,"");
        PlusMinusSequences.countWays(numbers,target,0,0);
        //initialise  withe empty text
    }
}