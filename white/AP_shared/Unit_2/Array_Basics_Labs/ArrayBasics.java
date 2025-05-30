public class ArrayBasics{

  /**
  * find and return the largest value in the array
  * 
  * @param ns : an array of ints
  * @return : an integer equal to the largest value in ns,
  */

  public static int max(int[] ns){
  

    return 0;
  }

  /**
  * find and return the smallest value in the array
  * 
  * @param ns : an array of ints
  * @return : an integer equal to the smallest value in ns,
  */

  public static int min(int[] ns){
    
    return 0;
  }

  /**
  * find and return the sum of all values in the array
  * 
  * @param ns : an array of ints
  * @return : an integer equal to sum of all values in the array 
  */

  public static int sum(int[] ns){

    return 0;
  }

  /**
  * find and return the average of all values in the array
  * 
  * @param ns : an array of ints with positive length
  * @return : a double equal to average of all values in the array 
  */

  public static double average(int[] ns){
    
    return 0.0;
  }

  /**
  * check if any array value is equal to a given value
  * 
  * @param ss : an array of Strings with positive length
  * @param target : a string
  *
  * @return : an integer representing any index k such that 
  * ss[k] is equal to target, or -1 if there is no such index
  */

  public static int search(String[] ss, String target){
	  
    return 0;
  }

  /**
  * check if any String in the array starts with an upper case
  * letter
  * 
  * @param ss : an array of Strings with positive length
  * @return : true if any String in ss begins with an upper case
  * letter, and false otherwise
  */

  public static boolean anyStartCaps(String[] ss){
	  
    return false;
  }

  /**
  * check if all Strings in the array start with an upper case
  * letter
  * 
  * @param ss : an array of Strings with positive length
  * @return : true if all Strings in ss begins with an upper case
  * false otherwise
  */

  public static boolean allStartCaps(String[] ss){
	  
    return false;
  }
    public static void main(String[] args){
    int[] ns = {15, 22, 13, 40, 5, 76, 7, 8, 9, 10};
    String[] ss = {"hello", "world", "Java", "python", "Javascript"};  

    System.out.println("max: " + max(ns));
    System.out.println("min: " + min(ns));
    System.out.println("sum: " + sum(ns));
    System.out.println("avg: " + average(ns));
    System.out.println("search: " + search(ss, "Javascript"));
    System.out.println("any start cap? "+ anyStartCaps(ss));
    System.out.println("all start cap? "+ allStartCaps(ss));
 
    
  }

}