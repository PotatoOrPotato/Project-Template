package unmanned.supermarket.pay.demo;

import java.util.Arrays;

public class day7 {

    public static void main(String[] args) {
        int[] arr ={1,2,3,4,5,6,7};
        int[] ints = Arrays.copyOf(arr, arr.length + 1);
        for (int i = 0; i <ints.length-1 ; i++) {
            int temp = ints[i+1];
            ints[i+1] = ints[0];
            ints[0] = temp;
        }
        System.err.println(Arrays.toString(ints));
    }
}
