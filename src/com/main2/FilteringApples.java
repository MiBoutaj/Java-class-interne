package com.main2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import static java.util.Comparator.comparing;

public class FilteringApples {


    private static List<Apple> apples = Arrays.asList(new Apple(120, Color.RED),
            new Apple(110, Color.GREEN),
            new Apple(90, Color.GREEN),
            new Apple(30, Color.RED),
            new Apple(170, Color.GREEN)
    );

    public static void main(String[] args) {




        /* apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });

        for (Apple a: apples) {
            System.out.println(a);
        }

         */

        apples.sort((Apple o1, Apple o2)->o1.getWeight()-o2.getWeight());

        System.out.println("*****************CREEN Apples ********************************");
        apples.forEach(apple ->{
            if(apple.getColor()==Color.GREEN)
            System.out.println(apple);});

        System.out.println("*****************CREEN Apples filtre ********************************");

        Predicate<Apple> predicate=new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor()==Color.GREEN;
            }
        };
        List<Apple> greenAplles= filter(predicate);



        List<Apple> greenAplles2= filter(new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor()==Color.GREEN;
            }
        });


        greenAplles.forEach(System.out::println);
        greenAplles2.forEach(System.out::println);

        System.out.println("***** heavy apple********");


        Predicate<Apple> predicate1 = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight()>=150;
            }
        };

        List<Apple> applesHeavy=filter(predicate1);


        List<Apple> applesHeavy1=filter((Apple apple)->apple.getWeight()>=150);


        applesHeavy.forEach(System.out::println);
        System.out.println("--------");
        applesHeavy1.forEach(System.out::println);



        apples.sort(comparing(Apple::getWeight));




        System.out.println("***********Red Apples ************************");

        Predicate<Apple> applePredicate = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor()==Color.RED;
            }
        };
        List<Apple> redApples = filter(applePredicate);


        List<Apple> redApples1 = filter((Apple apple)->apple.getColor()==Color.RED);


        redApples.forEach(apple -> System.out.println(apple));
        redApples1.forEach(apple -> System.out.println(apple));

        System.out.println("****** Color green et Heavy apple");


        Predicate<Apple> applePredicate1 = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor()==Color.GREEN && apple.getWeight()>=150;
            }
        };
        List<Apple> greenHeavy = filter(applePredicate1);


        List<Apple> greenHeavy1 = filter((Apple apple)->apple.getColor()==Color.RED && apple.getWeight()>=150);


        greenHeavy.forEach(apple -> System.out.println(apple));
        greenHeavy1.forEach(apple -> System.out.println(apple));








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


    // par quoi en filtre ??

    public static List<Apple> filter(Predicate<Apple> predicate){

        List<Apple> newapples = new ArrayList<Apple>();

        apples.forEach(apple -> {
            if (predicate.test(apple))
               newapples.add(apple);
        });
        return newapples;
    }






}
