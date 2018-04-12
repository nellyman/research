/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.lamba;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;



/**
 * @author nhardwic
 *
 */
public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private LocalDateTime birthday;
    private Sex gender;
    private String emailAddress;
    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    /**
     * @param name the name to set
     * @return the Person
     */
    public Person setName(final String name) {
        this.name = name;
        return this;
    }
    /**
     * @return the birthday
     */
    public LocalDateTime getBirthday() {
        return this.birthday;
    }
    /**
     * @param birthday the birthday to set
     * @return the Person
     */
    public Person setBirthday(final LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }
    /**
     * @return the gender
     */
    public Sex getGender() {
        return this.gender;
    }
    /**
     * @param gender the gender to set
     * @return the Person
     */
    public Person setGender(final Sex gender) {
        this.gender = gender;
        return this;
    }
    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }
    /**
     * @param emailAddress the emailAddress to set
     * @return the Person
     */
    public Person setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public static void processPersons(
            final List<Person> roster,
            final Predicate<Person> tester,
            final Consumer<Person> block) {

        // for each person in the roster...
        for (final Person p : roster) {
            // test them..
            if (tester.test(p)) {
                // if true then pass to the consumer..
                block.accept(p);
            }
        }

    }

    @Override
    public String toString() {
        return this.name;
    }

}

