package org.example;

import java.util.*;

public class Serializer {
    public static String serialize(Set<Integer> numbers) {
        // Преобразуем множество в отсортированный массив
        Integer[] sortedNumbers = numbers.toArray(new Integer[0]);
        Arrays.sort(sortedNumbers);

        StringBuilder builder = new StringBuilder();
        // Сохраняем первое число
        builder.append((char)(sortedNumbers[0] - 1));
        // Сохраняем разницы между числами
        for (int i = 1; i < sortedNumbers.length; i++) {
            int diff = sortedNumbers[i] - sortedNumbers[i - 1];
            builder.append((char)(diff - 1));
        }
        return builder.toString();
    }

    public static Set<Integer> deserialize(String serializedNumbers) {
        Set<Integer> numbers = new HashSet<>();
        int currentNumber = 0;
        // Читаем первый символ, который содержит первое число
        currentNumber = serializedNumbers.charAt(0) + 1;
        numbers.add(currentNumber);
        // Читаем остальные символы, которые содержат разницы между числами
        for (int i = 1; i < serializedNumbers.length(); i++) {
            currentNumber += (serializedNumbers.charAt(i) + 1);
            numbers.add(currentNumber);
        }
        return numbers;
    }
}
