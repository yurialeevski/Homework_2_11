import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
    }
    public static void task1() {
        System.out.println("Задача 1");

        Comparator<Integer> comparator = Integer::compareTo;
        BiConsumer<Integer, Integer> integerIntegerBiConsumer = (x, y) ->
                System.out.println("min = " + x + " max = " + y);

        System.out.println("Проверка, если min != null и max !=null");
        Stream<Integer> integerStream = Stream.of(22,11,44,33,66,55,88,77,99);
        findMinMax(integerStream, comparator, integerIntegerBiConsumer);

        System.out.println("Проверка, если min == null и max ==null");
        Stream<Integer> integerStream2 = Stream.empty();
        findMinMax(integerStream2, comparator, integerIntegerBiConsumer);

        System.out.println("Проверка, если min == max");
        Stream<Integer> integerStream3 = Stream.of(11);
        findMinMax(integerStream3, comparator, integerIntegerBiConsumer);
    }
    public static void task2() {
        System.out.println("Задача 2");
        Integer[] integers = {22,11,44,33,66,55,88,77,99, 111};

        System.out.println("Подсчет числа четных чисел в списке без создания метода");
        List<Integer> evenNumbersList = new ArrayList<>(Arrays.asList(integers));
        Long evenNumberCount = evenNumbersList.stream().filter(x -> x % 2 == 0).count();
        System.out.println(evenNumberCount);

        System.out.println("Подсчет с использованием созданного метода. Список передается в параметры метода.");
        List<Integer> anotherList = Arrays.asList(111, 222, 333, 400, 555, 644);
        System.out.println(evenNumbersCounter(anotherList));
    }
    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> element = stream.sorted(order).collect(Collectors.toList());
        T min;
        T max;
        if(element.isEmpty()) {
            minMaxConsumer.accept(null, null);
        } else {
            min = element.get(0);
            max = element.get(element.size() - 1);
            minMaxConsumer.accept(min, max );
        }
    }
    public static Long evenNumbersCounter(List<Integer> integers) {
        return integers.stream().filter(x -> x % 2 == 0).count();
    }
}