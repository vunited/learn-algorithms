import java.util.*;

/**
 * 数组中出现次数超过一半的数字
 * 
 * 摩尔投票算法
 * 
 * https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163
 */
public class Solution {

    // hashMap O(n)
    // public int MoreThanHalfNum_Solution(int[] array) {
    //     int res = 0;
    //     Map<Integer, Integer> count = new HashMap<>();
    //     for (int i = 0; i < array.length; i++) {
    //         if (count.containsKey(array[i])) {
    //             int tmp = count.get(array[i]) + 1;
    //             count.put(array[i], tmp);
    //         } else {
    //             count.put(array[i], 1);
    //         }
    //         if (count.get(array[i]) > array.length / 2) {
    //             res = array[i];
    //             break;
    //         }
    //     }
    //     return res;
    // }
    
    // 利用数组的性质，O(n)
    // 摩尔投票算法
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0)
            return 0;
        int count = 1;
        int p = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[p] == array[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                p = i;
                count = 1;
            }
        }
        int t = array[p];
        count = 0;
        for (int var : array) {
            if (t == var) {
                count++;
            }
        }
        return count > array.length / 2 ? t : 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Input>>>");
            List<String> in = Arrays.asList(sc.nextLine().split(","));
            int[] test = in.stream().mapToInt(item -> Integer.valueOf(item)).toArray();
            int res = s.MoreThanHalfNum_Solution(test);
            System.out.println(res);
        }
    }
}

// 1,2,3
// 1,2,2,3
// 1,2,2,2
// ,
// 1,2,3,2,4,2,5,2,3
// 1,2,3,2,4,2,5,2,2
// 1