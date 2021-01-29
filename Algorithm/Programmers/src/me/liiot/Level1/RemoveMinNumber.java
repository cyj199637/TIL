package me.liiot.Level1;

/*
제일 작은 수 제거하기
 */
public class RemoveMinNumber {

    public static void main(String[] args) {

        int[] i1 = {4,3,2,1};
        int[] i2 = {10};

        System.out.println(solution(i1).toString());
        System.out.println(solution(i2).toString());
    }

    private static int[] solution(int[] arr) {

        if (arr.length == 1)
            return new int[]{-1};

        int min = arr[0];
        for (int i=1; i<arr.length; i++) {
            if (min > arr[i])
                min = arr[i];
        }

        int[] result = new int[arr.length-1];
        int index = 0;
        for (int i=0; i<arr.length; i++) {
            if (min == arr[i])
                continue;
            result[index] = arr[i];
            index++;
        }

        return result;
    }
}
