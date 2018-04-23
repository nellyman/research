package com.nbh.research.streams;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * From:  http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 */
public class Streams2 {

    class Transaction{
        private int id;
        private String name;

        public Transaction(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * @return id
         **/
        public int getId() {
            return id;
        }

        /**
         * @return name
         **/
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    List<Transaction> transactions = new ArrayList<>();

    @Before
    public void setup(){
        transactions.add(new Transaction(5, "fifth"));
        transactions.add(new Transaction(1, "first"));
        transactions.add(new Transaction(2, "second"));
        transactions.add(new Transaction(10, "ten"));
        transactions.add(new Transaction(3, "third"));
        transactions.add(new Transaction(9, "nine"));
        transactions.add(new Transaction(4, "forth"));
        transactions.add(new Transaction(6, "sixth"));
        transactions.add(new Transaction(7, "seventh"));
        transactions.add(new Transaction(8, "eigth"));
    }

    @Test
    public void mapObjectsToInts(){
        List<Integer> transactionIds =
                transactions.stream()
                        .map(Transaction::getId)
                        .collect(toList());

        System.out.println("transactionIds = " + transactionIds);
    }


    @Test
    public void limitExample(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                        .filter(n -> {
                            System.out.println("filtering " + n);
                            return n % 2 == 0; // determines if an even number
                        })
                        .map(n -> {
                            System.out.println("mapping " + n);
                            return n * n; // determines the square
                        })
                        .limit(2) // limits to 2 squares
                        .collect(toList());
        System.out.println("twoEvenSquares = " + twoEvenSquares);
    }

    @Test
    public void UsingPredicateOnFilter(){
        boolean largeId =
                transactions.stream()
                        .allMatch(t -> t.getId() > 3); //check that all elements in a stream of transactions

        System.out.println("largeId = " + largeId);

        boolean idOverZero = transactions.stream()
                .allMatch(t -> t.getId() > 0);
        System.out.println("idOverZero = " + idOverZero);
    }

    @Test
    public void findFirstExample(){
        Optional<Transaction> tx = transactions.stream()
                .filter(t -> t.getId() == 3)
                .findFirst();
        System.out.println("tx = " + tx);
    }

    @Test
    public void ifPresentExample(){
        transactions.stream()
                .filter(t -> t.getId() > 5)
                .findAny()
                .ifPresent(System.out::println);
    }

    @Test
    public void collectExample(){
        List<Integer> nameLength = transactions.stream()
                .map(t -> t.getName().length())
                .collect(toList());

        nameLength.stream()
                .forEach(s -> System.out.println("length: " + s));
    }

    @Test
    public void sumAllElements(){
        int totalNameLengths = transactions.stream()
                .mapToInt(t -> t.getName().length())
                .sum();
        System.out.println("totalNameLengths = " + totalNameLengths);
    }

    @Test
    public void generateOddNumbers(){
        IntStream oddNumbers =
                IntStream.rangeClosed(10, 30)
                        .filter(n -> n % 2 == 1);

        oddNumbers.forEach(s-> System.out.println(s));
    }
}
