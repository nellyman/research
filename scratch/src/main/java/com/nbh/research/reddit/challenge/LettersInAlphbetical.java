/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.reddit.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * A handful of words have their letters in alphabetical order, that is nowhere in the word do you change direction in the word if
 * you were to scan along the English alphabet. An example is the word "almost", which has its letters in alphabetical order.
Your challenge today is to write a program that can determine if the letters in a word are in alphabetical order.
As a bonus, see if you can find words spelled in reverse alphebatical order.

Challenge results-

billowy IN ORDER
biopsy IN ORDER
chinos IN ORDER
defaced NOT IN ORDER
chintz IN ORDER
sponged REVERSE ORDER
bijoux IN ORDER
abhors IN ORDER
fiddle NOT IN ORDER
begins IN ORDER
chimps IN ORDER
wronged REVERSE ORDER

 *
 * @author nhardwic
 *
 */
public class LettersInAlphbetical {

    final Logger logger =LogManager.getRootLogger();

    public enum Output  {IN_ORDER, NOT_IN_ORDER, REVERSE_ORDER};

    class InputEntry{
        public String input;
        public Output expectedOutput;
        public InputEntry(final String input, final Output output) {
            this.input=input;
            this.expectedOutput=output;
        }
    }

    private List<InputEntry> inputs;

    @Before
    public void setup() {
        this.inputs = new ArrayList<LettersInAlphbetical.InputEntry>();
        this.inputs.add(new InputEntry("billowy",Output.IN_ORDER));
        this.inputs.add(new InputEntry("biopsy",Output.IN_ORDER));
        this.inputs.add(new InputEntry("chinos",Output.IN_ORDER));
        this.inputs.add(new InputEntry("defaced",Output.NOT_IN_ORDER));
        this.inputs.add(new InputEntry("chintz",Output.IN_ORDER));
        this.inputs.add(new InputEntry("sponged",Output.REVERSE_ORDER));
        this.inputs.add(new InputEntry("bijoux",Output.IN_ORDER));
        this.inputs.add(new InputEntry("abhors",Output.IN_ORDER));
        this.inputs.add(new InputEntry("fiddle",Output.NOT_IN_ORDER));
        this.inputs.add(new InputEntry("begins",Output.IN_ORDER));
        this.inputs.add(new InputEntry("chimps",Output.IN_ORDER));
        this.inputs.add(new InputEntry("wronged",Output.REVERSE_ORDER));

    }

    @Test
    public void expectInputsToHaveExpectedOutputs() {
        for (final InputEntry entry : this.inputs) {
            final String input= entry.input;
            final Output output = this.performCompare(input);
            Assert.assertEquals(entry.expectedOutput, output);
            this.logger.info(input+" - "+output);
        }
    }

    /**
     * Not working...
     * @param input
     * @return
     */
    private Output performCompare2(final String input) {
        String[] chars = input.split("( /^a*b*c*d*e*f*g*h*i*j*k*l*m*n*o*p*q*r*s*t*u*v*w*x*y*z*$)");
        Arrays.stream(chars)
        .forEach(System.out::println);

        if (chars.length>0) {
            return Output.IN_ORDER;
        }
        chars = input.split("( /^z*y*x*w*v*u*t*s*r*q*p*o*n*m*l*k*j*i*h*g*f*e*d*c*b*a*$)");
        if (chars.length>0) {
            return Output.REVERSE_ORDER;
        }
        return Output.NOT_IN_ORDER;
    }

    private Output performCompare(final String input) {
        this.logger.info("checking "+input);

        final String[] chars = input.split("");
        this.logger.info(chars.length);
        String lastCh=chars[0];

        Output output = null;
        Output lastOutput=null;

        for (int index=1; index<chars.length; index++) {
            final String ch = chars[index];
            this.logger.info("checking "+lastCh+" with "+ch);

            final int score = ch.compareTo(lastCh);

            if (score>0) {
                output = Output.IN_ORDER;
            }
            if (score<0) {
                output=Output.REVERSE_ORDER;
            }
            this.logger.info("deemed as "+output);

            if (lastOutput!=null && lastOutput!=output) {
                return Output.NOT_IN_ORDER;
            }
            this.logger.info("In Order check ok...");

            lastCh=ch;
            lastOutput = output;
        }
        return output;
    }
}
