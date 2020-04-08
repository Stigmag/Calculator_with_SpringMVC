package com.calculator.controller;

import com.calculator.models.Calculator;
import com.calculator.models.UndoRedoOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalculatorController {
    private int i = 0;
    private String text;
    private UndoRedoOperation undoRedoOperation = new UndoRedoOperation();

    @GetMapping("/")
    public String index(Model model) {
        String mathProblem = "";
        model.addAttribute("mathProblem", mathProblem);
        model.addAttribute("view", "views/calculatorForm");


        return "base-layout";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "calculate")
    public String index(HttpServletRequest request, @RequestParam String mathProblem, Model model) {

        if (mathProblem == "") {
            model.addAttribute("result", "Please, enter math problem");
            model.addAttribute("view", "views/calculatorForm");
            return "base-layout";
        }
        Calculator calculator = new Calculator(mathProblem);


        calculator.calculate();


        undoRedoOperation.getStackUndo().push(mathProblem);

        model.addAttribute("result", calculator.getResult());

        model.addAttribute("view", "views/calculatorForm");
        return "base-layout";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "undo")
    public String indexUndo(HttpServletRequest request,


                            Model model
    ) {


        text = undoRedoOperation.undo();

        model.addAttribute("mathProblem", text);

        model.addAttribute("view", "views/calculatorForm");
        return "base-layout";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "redo")
    public String indexRedo(HttpServletRequest request,


                            Model model
    ) {


        text = undoRedoOperation.redo();

        model.addAttribute("mathProblem", text);

        model.addAttribute("view", "views/calculatorForm");
        return "base-layout";
    }
}
