public class ArrayMain {
    public static void main(String[] args) {
        int [][] a1 = {{1,2,3,4},
                       {5,6,7,8},
                       {9,10,11,12},
                       {13,14,15,16}};
        traverse1(a1);

        int [][] a2 = {{ 1, 5,  9, 13},
                       { 2, 6, 10, 14},
                       { 3, 7, 11, 15},
                       { 4, 8, 12, 16}};
      traverse2(a2);

        int [][] a3 = {{  1,  2,  9, 10},
                       {  3,  4, 11, 12},
                       {  5,  6, 13, 14},
                       {  7,  8, 15, 16}};
      traverse3(a3);

        int [][] a4 = {{  1,  3,  5,  7},
                       {  9, 11, 13, 15},
                       {  2,  4,  6,  8},
                       { 10, 12, 14, 16}};
      traverse4(a4);

      int [][] a5 =   {{  1,  3,  6, 10},
                       {  2,  5,  9, 13},
                       {  4,  8, 12, 15},
                       {  7, 11, 14, 16}};
      traverse5(a5);

      int [][] a6 =   {{  7,  4,  2,  1},
                       { 13,  8,  5,  3},
                       { 15, 12,  9,  6},
                       { 16, 14, 11, 10}};
      traverse6(a6);

      int [][] flat = flatten(a4);
    }

      /*  COMPLETE THE METHODS BELOW  */

      public static void traverse1(int[][] matrix){

      }
      public static void traverse2(int[][] matrix){

      }
      public static void traverse3(int[][] matrix){

      }
      public static void traverse4(int[][] matrix){

      }
      public static void traverse5(int[][] matrix){

      }
      public static void traverse6(int[][] matrix){

      }

      public static int [][] flatten(int[][] inArray){
    // the line below is just a placeholder.Don't use it
        return null;
      }

}