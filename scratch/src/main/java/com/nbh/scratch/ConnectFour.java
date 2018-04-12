/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.scratch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/3fva66/20150805_challenge_226_intermediate_connect_four/
 * @author nhardwic
 *
 */
public class ConnectFour {

    // Sets the Rule up...
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    String[][] board = new String[7][6];

    String[] moves = {"D,d", "D,c", "C,c", "C,c", "G,f", "F,d", "F,f","D,f", "A,a","E,b", "E,e", "B,g","G,g","B,a"};

    // O won at move 11 (with c1 d2 e3 f4)

    @Test
    public void expectMovesToBeRecordedOk() {
        this.addMoveToBoard("a", "X");
        this.addMoveToBoard("g", "X");
        this.addMoveToBoard("A", "X");
        this.addMoveToBoard("G", "X");
        this.addMoveToBoard("G", "X");
    }

    @Test
    public void expectFullColumnToFail() {
        this.thrown.expect(RuntimeException.class);
        this.thrown.expectMessage("Cannot make that move");

        this.addMoveToBoard("a", "X");
        this.addMoveToBoard("A", "O");
        this.addMoveToBoard("a", "X");
        this.addMoveToBoard("A", "O");
        this.addMoveToBoard("a", "X");
        this.addMoveToBoard("a", "O");
        this.addMoveToBoard("a", "O");
    }


    private void addMoveToBoard(final String columnLetter, final String team) {

        final int column = Character.getNumericValue(columnLetter.charAt(0))-10;
        System.out.println(column);
        int row=5;
        String cell = this.board[column][row];
        System.out.println("Cells value: "+cell);
        while (cell!=null) {
            row=row-1;
            if (row<0) {
                throw new RuntimeException("Cannot make that move");
            }
            cell = this.board[column][row];
            System.out.println("Cells value: "+cell);
        }
        this.board[column][row]=team;
    }

    private String[] isWinningMove() {

        return null;
    }

}
