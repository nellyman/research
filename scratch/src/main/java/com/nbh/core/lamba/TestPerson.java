/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.lamba;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nbh.core.lamba.Person.Sex;



/**
 * @author nhardwic
 *
 */
public class TestPerson {

    List<Person> persons;

    @Before
    public void setup() {
        this.persons = new ArrayList<Person>();

        final LocalDateTime bday = LocalDateTime.of(1980, 5, 15, 11, 00);
        final Person john=new Person().setName("John").setGender(Sex.MALE).setEmailAddress("a@bc.com").setBirthday(bday);
        this.persons.add(john);

        final Person sarah=new Person().setName("Sarah").setGender(Sex.FEMALE).setEmailAddress("b@bc.com").setBirthday(bday);
        this.persons.add(sarah);
    }

    @Test
    public void testAllMalePersons() {
        Person.processPersons(
                this.persons,
                p -> p.getGender() == Person.Sex.MALE,
                personMatch -> System.out.println(personMatch)
                );
    }

    @Test
    public void testAllEmailPersons() {
        Person.processPersons(
                this.persons,
                p -> p.getEmailAddress().startsWith("b"),
                personMatch -> System.out.println(personMatch)
                );
    }


}
