package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;


public class ControllerImpl implements ClassicMvcController{
    private Model model;
    public ControllerImpl(Model model) {
        this.model=model;
    }

    public void clickNextPuzzle(){
        int index=this.model.getActivePuzzleIndex();
        this.model.setActivePuzzleIndex(index+1);
    }

    public void clickPrevPuzzle(){
        int index=this.model.getActivePuzzleIndex();
        this.model.setActivePuzzleIndex(index-1);
    }

    public void clickRandPuzzle(){
        int size=this.model.getPuzzleLibrarySize();
        int random_index = (int)Math.floor(Math.random()*size);
        this.model.setActivePuzzleIndex(random_index);
    }

    public void clickResetPuzzle(){
        this.model.resetPuzzle();
    }

    public void clickCell(int r, int c){
        if (this.model.isLamp(r, c)){
            model.removeLamp(r,c);
        } else {
            model.addLamp(r,c);
        }
    }
}
