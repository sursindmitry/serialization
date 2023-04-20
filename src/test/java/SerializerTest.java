import org.example.Serializer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SerializerTest {
    public static void main(String[] args) {
        Set<Integer> testSet = Set.of(1, 2, 3);

        String serialized = Serializer.serialize(testSet);
        System.out.println("Сериализация: " + serialized); // Expecting: "\u0001\u0001"

        Set<Integer> deserialized = Serializer.deserialize(serialized);
        System.out.println("Десериализация" + deserialized);

        Random random = new Random();
        Set<Integer> randomSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            randomSet.add(random.nextInt(300) + 1);
        }

        System.out.println("Случайные числа: " + randomSet);

        String randomSerialized = Serializer.serialize(randomSet);
        double compressionRatio = (double) randomSerialized.length() / (randomSet.size() * 2 - 1);
        System.out.println("Коэффициента сжатия: " + compressionRatio);
        Set<Integer> randomDeserialized = Serializer.deserialize(randomSerialized);
        System.out.println(randomSet.equals(randomDeserialized)); // Expecting: true
        // Дополнительные тесты
        testSerialization("1", "\u0000", 0.5);
        testSerialization("99", "\u0058\u0000", 0.5);
        testSerialization("100", "\u0059\u0000", 0.5);
        testSerialization("111", "\u005A\u0001", 0.5);
        testSerialization("222", "\u00DE\u0001", 0.5);
        testSerialization("11111111111111111111", "\u005A\u005A\u005A\u005A\u005A\u005A\u005A\u005A\u005A\u005A", 0.45);
        testSerialization("22222222222222222222", "\u00DE\u00DE\u00DE\u00DE\u00DE\u00DE\u00DE\u00DE\u00DE\u00DE", 0.45);
        testSerialization("33333333333333333333", "\u017A\u017A\u017A\u017A\u017A\u017A\u017A\u017A\u017A\u017A", 0.45);
    }

    private static void testSerialization(String input, String expectedOutput, double expectedRatio) {
        System.out.println("\nInput: " + input);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            set.add(Character.getNumericValue(input.charAt(i)));
        }

        String serialized = Serializer.serialize(set);
        System.out.println(serialized.equals(expectedOutput));
        System.out.println("Сериализация: " + serialized);

        Set<Integer> deserialized = Serializer.deserialize(serialized);
        System.out.println(set.equals(deserialized));
        System.out.println("Десериализация" + deserialized);

        double compressionRatio = (double) serialized.length() / (set.size() * input.length() - 1);
        System.out.println(compressionRatio == expectedRatio);
        System.out.println("Коэффициента сжатия: " + compressionRatio);
    }
}

