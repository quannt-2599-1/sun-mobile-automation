package utils;

import java.util.Arrays;

public class ConvertStringToArray {
    public static int[] parseArray(String arrayStr) {
        String[] parts = arrayStr.trim().split("\\s+");

        return Arrays.stream(parts)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
