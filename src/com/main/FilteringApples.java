package com.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {


    private static List<Apple> apples = Arrays.asList(new Apple(120, Color.RED),
            new Apple(110, Color.GREEN),
            new Apple(90, Color.GREEN),
            new Apple(30, Color.RED),
            new Apple(170, Color.GREEN)
    );


    public static List<Apple> filterGreenApples() {
        List<Apple> apples2 = new ArrayList<>();
        for (Apple apple : apples) {

            if (apple.getColor() == Color.GREEN)
                apples2.add(apple);

        }
        return apples2;
    }

    public static List<Apple> filterApplesByWeight(int weight) {
        List<Apple> apples2 = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() >= weight)
                apples2.add(apple);
        }
        return apples2;
    }

    public static List<Apple> filterApplesByColors(Color color) {
        List<Apple> apples2 = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor() == color)
                apples2.add(apple);
        }
        return apples2;
    }


    public static List<Apple> filter(ApplePredicate applePredicate) {
        List<Apple> apples2 = new ArrayList<>();
        for (Apple apple : apples
        ) {
            if (applePredicate.test(apple))
                apples2.add(apple);

        }
        return apples2;

    }


    public static void main(String[] args) {
/*
        Comparator<Apple> comparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight()-a2.getWeight();
            }
        };

        apples.sort(comparator);
 */

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight() - a2.getWeight();
            }
        });


        for (Apple apple : apples) {
            System.out.println(apple);

        }


        System.out.println("------------------------------------------------");
        System.out.println("List de green Aplles");
        for (Apple apple : FilteringApples.filterGreenApples()) {
            System.out.println(apple);
        }

        System.out.println("------------------------------------------------");
        System.out.println("List de green By Weight");
        for (Apple apple : FilteringApples.filterApplesByWeight(100)) {
            System.out.println(apple);
        }


        System.out.println("------------------------------------------------");
        System.out.println("List de green By Color");
        for (Apple apple : FilteringApples.filterApplesByColors(Color.RED)) {
            System.out.println(apple);
        }

        System.out.println("------------------------------------------------");
        System.out.println("List de green By Color by Predicate interface");
        for (Apple apple : filter(new AppleColorPredicate())) {
            System.out.println(apple);
        }

        System.out.println("------------------------------------------------");
        System.out.println("List de Heavy by Predicate interface");
        for (Apple apple : filter(new AppleHeavyPredicate())) {
            System.out.println(apple);
        }


        System.out.println("------------------------------------------------");
        System.out.println("List de Heavy && Red by Predicate interface");
        for (Apple apple : filter(new AppleRedAndHeavyPredicate())) {
            System.out.println(apple);
        }


        System.out.println("--------------------- Class Anonyme -------------------------------------");

        System.out.println("       List Green apples");
        for (Apple apple : filter(new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == Color.GREEN;
            }
        })) {
            System.out.println(apple);
        }


        System.out.println("       List Heavy apples");
        for (Apple apple : filter(new AppleRedAndHeavyPredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() >= 150;
            }
        })) {
            System.out.println(apple);
        }


        System.out.println("       List Heavy && Red apples");
        for (Apple apple : filter(new AppleRedAndHeavyPredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() >= 150 && apple.getColor() == Color.RED;
            }
        })) {
            System.out.println(apple);
        }


    }

    public enum Color {
        RED, GREEN;
    }

    public static class Apple {
        private int weight;
        private Color color;


        public Apple(int weight, Color color) {
            super();
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }


        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color=" + color +
                    '}';
        }


    }


    public interface ApplePredicate extends Predicate<Apple> {

    }


    public static class AppleHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() >= 150;
        }
    }

    public static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
    }

    public static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.RED && apple.getWeight() >= 150;
        }
    }


}
