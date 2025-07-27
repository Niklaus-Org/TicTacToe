package com.lld.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public Board(int dimension) {
         this.dimension = dimension;
         this.board = new ArrayList<>();

         for(int i=0; i<dimension; i++) {
             board.add(new ArrayList<>());

             for(int j=0; j<dimension; j++) {
                 board.get(i).add(new Cell(i, j, CellState.EMPTY));
             }
         }
    }

    @Override
    public String toString() {
        return "Board{" +
                "dimension=" + dimension +
                ", board=" + board +
                '}';
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }
}
