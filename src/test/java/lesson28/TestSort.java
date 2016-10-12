package lesson28;

/**
 * Created by gaoyang on 2016/8/5.
 */
public class TestSort {

    //冒泡排序
    public static int[] bubbleSort1(int[] a) {
        //长度减一代表最后一位下一层来取做比较 最多做n-1趟排序
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println("第" + i + "次循环");
            //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
            for (int j = 0; j < a.length - i - 1; j++) {
                System.out.println("i=" + i + ";j=" + j);
                //把大的值交换到后面
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            for (int x = 0; x < a.length; x++) {
                System.out.print(a[x] + " ");
            }
            System.out.println();
        }
        return a;
    }

    //冒泡排序
    public static int[] bubbleSort2(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println("第" + i + "次循环");
            for (int j = i + 1; j < a.length; j++) {
                System.out.println("i=" + i + ";j=" + j);
                if (a[i] > a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
            for (int x = 0; x < a.length; x++) {
                System.out.print(a[x] + " ");
            }
            System.out.println();
        }
        return a;
    }

    public static int[] exchangeSort(int[] mp) {

        for (int i = 0; i < mp.length; i++) {
            System.out.println("第" + i + "次循环");
            for (int j = 0; j < mp.length; j++) {
                System.out.println("i=" + i + ";j=" + j);
                if (mp[i] < mp[j]) {
                    int temp = mp[j];
                    mp[j] = mp[i];
                    mp[i] = temp;
                }
            }
            for (int x = 0; x < mp.length; x++) {
                System.out.print(mp[x] + " ");
            }
            System.out.println();
        }
        return mp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 5, 1, 3};
//        int[] result = TestSort.bubbleSort1(a);
//        int[] result = TestSort.bubbleSort2(a);
        int[] result = TestSort.exchangeSort(a);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }

    }

}
