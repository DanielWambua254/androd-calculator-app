package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private TextView resultTv;
    private Button equals;
    private ImageButton backSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.entryTv);
        display.setShowSoftInputOnFocus(false);
        resultTv = findViewById(R.id.resultsTv);
        equals = findViewById(R.id.equals);
        backSpace = findViewById(R.id.backSpace);

        display.setOnClickListener(view -> {
            if (getString(R.string.display).equals(display.getText().toString())) {
                display.setText("");
            }
        });

        backSpace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                display.setText("");
                resultTv.setText("");
                return false;
            }
        });

    }

    private void updateText(String stringToAdd) {
        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);

        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(stringToAdd);
        }
        else {
            display.setText(String.format("%s%s%s",leftString, stringToAdd, rightString));
        }
        display.setSelection(cursorPosition + 1);
    }


    public void zeroBtn(View view) {
        updateText("0");
        equals.performClick();
    }
    public void btnOne(View view) {
        updateText("1");
        equals.performClick();
    }
    public void btnTwo(View view) {
        updateText("2");
        equals.performClick();
    }
    public void btnThree(View view) {
        updateText("3");
        equals.performClick();
    }
    public void btnFour(View view) {
        updateText("4");
        equals.performClick();
    }
    public void btnFive(View view) {
        updateText("5");
        equals.performClick();
    }
    public void btnSix(View view) {
        updateText("6");
        equals.performClick();
    }
    public void btnSeven(View view) {
        updateText("7");
        equals.performClick();
    }
    public void btnEight(View view) {
        updateText("8");
        equals.performClick();
    }
    public void btnNine(View view) {
        updateText("9");
        equals.performClick();
    }
    public void clear(View view) {
        display.setText("");
        resultTv.setText("");
    }
    public void multiply(View view) {
        updateText("×");
    }
    public void divide(View view) {
        updateText("÷");
    }
    public void add(View view) {
        updateText("+");
    }
    public void subtract(View view) {
        updateText("-");
    }
    public void point(View view) {
        updateText(".");
    }

    public void backspace(View view) {
        int cursorPosition = display.getSelectionStart();
        int textLength = display.getText().length();

        if (cursorPosition != 0 && textLength != 0) {
            SpannableStringBuilder selection  = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition -1, cursorPosition,"");
            display.setText(selection);
            display.setSelection(cursorPosition - 1);
        }
    }
    public void other(View view) {
        updateText("sin(");
    }
    public void parentheses(View view) {
        int cursorPosition = display.getSelectionStart();
        int openParentheses = 0;
        int closeParentheses = 0;
        int textLength = display.getText().length();

        for (int i = 0; i < cursorPosition; i++) {
            if(display.getText().toString().charAt(i) == '(') {
                openParentheses += 1;
            }
            if(display.getText().toString().charAt(i) == ')') {
                closeParentheses += 1;
            }
        }

        if (openParentheses == closeParentheses ||
                display.getText().toString().charAt(textLength - 1) == '(') {
            updateText("(");
        }
        else if (closeParentheses < openParentheses &&
                display.getText().toString().charAt(textLength - 1) != '(') {
            updateText(")");
            equals.performClick();

        }
        display.setSelection(cursorPosition + 1);
    }
    public void exponential(View view) {
        updateText("^");
    }

    public void equals(View view)  {
        String userExpression = display.getText().toString();
        userExpression = userExpression.replaceAll("÷", "/");
        userExpression = userExpression.replaceAll("×", "*");

        Expression expression = new Expression(userExpression);
        String result = String.valueOf(expression.calculate());
        if (result.endsWith(".0")) {
            result = result.replace(".0","");
        }
        resultTv.setText(result);
        display.setSelection(userExpression.length());
    }
}