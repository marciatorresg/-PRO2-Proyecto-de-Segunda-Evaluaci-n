package com.grupopoop.tres_en_raya.ui.controllers;

import android.content.Context;
import android.widget.ImageView;

import com.grupopoop.tres_en_raya.MainActivity;
import com.grupopoop.tres_en_raya.R;
import com.grupopoop.tres_en_raya.ui.sampledata.WinDialog;

import java.util.*;
import java.util.List;

public class GameControl {
    private final List<int[]> combinationList;
    private int[] boxPositions;
    private int totalSelectedBoxes ;
    private int playerTurn ;
    private Context context;
    public GameControl() {
        combinationList= new ArrayList<>();
        this.boxPositions= new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        totalSelectedBoxes=1;
        playerTurn=1;
        initCombination();


    }

    public GameControl(Context context) {
        this();
        this.context = context;

    }

    private void initCombination() {
        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});
    }

    public List<int[]> getCombinationList() {
        return combinationList;
    }
    public boolean isBoxSelectable(int boxPosition){
        boolean response =false;

        if(boxPositions[boxPosition]==0){
            response=true;

        }
        return response;

    }


    public boolean checkPlayerWin(){
        boolean response=false;
        for(int i=0; i<combinationList.size();i++){
            final int[] combination= combinationList.get(i);
            if(boxPositions[combination[0]]==playerTurn&&boxPositions[combination[1]]==playerTurn&&boxPositions[combination[2]]==playerTurn){
                response =true;
            }

        }
        return response;
    }


    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int[] getBoxPositions() {
        return boxPositions;
    }

    public void setBoxPositions(int[] boxPositions) {
        this.boxPositions = boxPositions;
    }

    public int getTotalSelectedBoxes() {
        return totalSelectedBoxes;
    }

    public void setTotalSelectedBoxes(int totalSelectedBoxes) {
        this.totalSelectedBoxes = totalSelectedBoxes;
    }
}
