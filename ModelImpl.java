package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private PuzzleLibrary library;
    private int activePuzzle;
    private int[][] lamp;
    private List<ModelObserver> observerList;

    public ModelImpl(PuzzleLibrary library) {
        if(library.size() == 0) {
            throw new IllegalArgumentException();
        }
        this.library = library;
        this.activePuzzle = 0;
        Puzzle puzzle=this.library.getPuzzle(this.activePuzzle);
        this.lamp=new int[puzzle.getHeight()][puzzle.getWidth()];
        observerList= new ArrayList<ModelObserver>();
    }

    public void addLamp(int r, int c){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        if (r >= puzzle.getHeight() || r<0){
            throw new IndexOutOfBoundsException();
        }
        if (c >= puzzle.getWidth() || c<0){
            throw new IndexOutOfBoundsException();
        }
        if (puzzle.getCellType(r, c)!=CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        this.lamp[r][c]=1;

    }

    public void removeLamp(int r, int c){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        if (r >= puzzle.getHeight() || r<0){
            throw new IndexOutOfBoundsException();
        }
        if (c >= puzzle.getWidth() || c<0){
            throw new IndexOutOfBoundsException();
        }
        if (puzzle.getCellType(r, c)!=CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        this.lamp[r][c]=0;

    }

    public boolean isLit(int r, int c){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        if (r >= puzzle.getHeight() || r<0){
            throw new IndexOutOfBoundsException();
        }
        if (c >= puzzle.getWidth() || c<0){
            throw new IndexOutOfBoundsException();
        }
        if (puzzle.getCellType(r, c)!=CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        if (lamp[r][c]==1){
            notifyObservers();
            return true;
        }
        if (isLitHelper(puzzle, r, c, 1, 1, 1, 1, 0 ,0 ,0, 0)){
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean isLitHelper(Puzzle puzzle, int r, int c, int up, int down, int left, int right, int upStatus, int downStatus, int leftStatus, int rightStatus){
        if (upStatus==2 && downStatus==2 && leftStatus==2 && rightStatus==2){
            return false;
        }
        if (upStatus==0){
            if (r-up>=0){
                if (puzzle.getCellType(r-up, c)==CellType.CLUE || puzzle.getCellType(r-up, c)==CellType.WALL) {
                    upStatus = 2;
                }
                if (lamp[r-up][c]==1){
                    return true;
                }
            } else {
                upStatus=2;
            }
        }
        if (downStatus==0){
            if (r+down<puzzle.getHeight()){
                if (puzzle.getCellType(r+down, c)==CellType.CLUE || puzzle.getCellType(r+down, c)==CellType.WALL) {
                    downStatus = 2;
                }
                if (lamp[r+down][c]==1){
                    return true;
                }
            } else {
                downStatus=2;
            }
        }
        if (leftStatus==0){
            if (c-left>=0){
                if (puzzle.getCellType(r, c-left)==CellType.CLUE || puzzle.getCellType(r, c-left)==CellType.WALL) {
                    leftStatus = 2;
                }
                if (lamp[r][c-left]==1){
                    return true;
                }
            } else {
                leftStatus=2;
            }
        }
        if (rightStatus==0){
            if (c+right<puzzle.getWidth()){
                if (puzzle.getCellType(r, c+right)==CellType.CLUE || puzzle.getCellType(r, c+right)==CellType.WALL) {
                    rightStatus = 2;
                }
                if (lamp[r][c+right]==1){
                    return true;
                }
            } else {
                rightStatus=2;
            }
        }
        return isLitHelper(puzzle, r, c, up+1, down+1, left+1, right+1, upStatus, downStatus, leftStatus, rightStatus);
    }

    public boolean isLamp(int r, int c){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        if (r >= puzzle.getHeight() || r<0){
            throw new IndexOutOfBoundsException();
        }
        if (c >= puzzle.getWidth() || c<0){
            throw new IndexOutOfBoundsException();
        }
        if (puzzle.getCellType(r, c)!=CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        return lamp[r][c]==1;
    }

    public boolean isLampIllegal(int r, int c){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        if (lamp[r][c]==0){
            throw new IllegalArgumentException();
        }
        if (r >= puzzle.getHeight() || r<0){
            throw new IndexOutOfBoundsException();
        }
        if (c >= puzzle.getWidth() || c<0){
            throw new IndexOutOfBoundsException();
        }
        if (isLitHelper(puzzle, r, c, 1, 1, 1, 1, 0, 0, 0, 0)==true){
            return true;
        }
        return false;
    }

    public Puzzle getActivePuzzle(){
        return this.library.getPuzzle(activePuzzle);
    }

    public int getActivePuzzleIndex(){
        return this.activePuzzle;
    }

    public void setActivePuzzleIndex(int index){
        if (index<0 || index>=this.library.size()){
            throw new IndexOutOfBoundsException();
        }
        this.activePuzzle=index;
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        this.lamp=new int[puzzle.getHeight()][puzzle.getWidth()];
        notifyObservers();
    }

    public int getPuzzleLibrarySize(){
        return this.library.size();
    }

    public void resetPuzzle(){
        for (int i=0; i<lamp.length; i++){
            for (int j=0; j<lamp[0].length; j++){
                this.lamp[i][j]=0;
            }
        }
    }

    public boolean isSolved(){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        for (int i=0; i<lamp.length; i++){
            for (int j=0; j<lamp[0].length; j++){
                if (puzzle.getCellType(i, j)==CellType.CORRIDOR && isLit(i, j)==false){
                    return false;
                }
                if (lamp[i][j]==1 && isLampIllegal(i, j)==true){
                    return false;
                }
                if (puzzle.getCellType(i, j)==CellType.CLUE){
                    int clue=puzzle.getClue(i, j);
                    int count=0;
                    if (i-1>=0 && lamp[i-1][j]==1){
                        count++;
                    }
                    if (i+1<lamp.length && lamp[i+1][j]==1){
                        count++;
                    }
                    if (j-1>=0 && lamp[i][j-1]==1){
                        count++;
                    }
                    if (j+1<lamp[0].length && lamp[i][j+1]==1){
                        count++;
                    }
                    if (count!=clue){
                        return false;
                    }
                }
            }
        }
        notifyObservers();
        return true;
    }

    public boolean isClueSatisfied(int r, int c){
        Puzzle puzzle=this.library.getPuzzle(activePuzzle);
        int clue=puzzle.getClue(r, c);
        int count=0;
        if (r-1>=0 && lamp[r-1][c]==1){
            count++;
        }
        if (r+1<lamp.length && lamp[r+1][c]==1){
            count++;
        }
        if (c-1>=0 && lamp[r][c-1]==1){
            count++;
        }
        if (c+1<lamp[0].length && lamp[r][c+1]==1){
            count++;
        }
        if (count!=clue){
            return false;
        }
        return true;
    }

    public void addObserver(ModelObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(ModelObserver observer){
        observerList.remove(observer);
    }

    public void notifyObservers(){
        for (ModelObserver observer : this.observerList){
            observer.update(this);
        }
    }
}
