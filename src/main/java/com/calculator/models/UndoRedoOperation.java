package com.calculator.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoRedoOperation {
    private Stack<String> stackUndo= new Stack<>();
    private Stack<String> stackRedo= new Stack<>();

    public Stack<String> getStackUndo() {
        return stackUndo;
    }

    public Stack<String> getStackRedo() {
        return stackRedo;
    }



    public String undo(){
        return this.stackRedo.push(this.stackUndo.pop());

    }
    public String redo(){
        return this.stackUndo.push(this.stackRedo.pop());

    }
}
