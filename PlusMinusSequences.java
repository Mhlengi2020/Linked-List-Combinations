import java.util.ArrayList;
import java.util.List;
public class PlusMinusSequences {

    //Method to count the number of ways to reach the target sum
    public static int countWays(int[]numbers,int target,int index,int currentSum){
        if(index == numbers.length){
            //Checks if the current sum equals the target
            return currentSum==target?1:0;
        }
        //Count ways by adding the current number
        int addCurrent=countWays(numbers,target,index+1,currentSum+numbers[index]);
        //count ways by subtracting the current number
        int subtractCurrent=countWays(numbers,target,index+1,currentSum-numbers[index]);

        return subtractCurrent+addCurrent;//Now returns the total count of ways
    }
    //Method to find and display all combinations that sum to the target
    public static void findCombos(int[] numbers,int target,int index,int currentSum,String text){
        if(index == numbers.length){
            //Check if the current sum equals the target
            if(currentSum==target){
                //Print the wanted expression
                System.out.println(text);
            }
            return; //Leaves the method
        }
        //Recursive case : inlcude the current number with + annd -
        //Call the method with the current added
        findCombos(numbers,target,index+1,currentSum+numbers[index],text+"+"+numbers[index]);
        //Call the method with the current number subracted
        findCombos(numbers,target,index+1,currentSum-numbers[index],text+"-"+numbers[index]);
    }

}
