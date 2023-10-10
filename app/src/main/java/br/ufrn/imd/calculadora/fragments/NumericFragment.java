package br.ufrn.imd.calculadora.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.ufrn.imd.calculadora.R;

public class NumericFragment extends Fragment {

    private String operation;

    private Float numberOne;

    private Float numberTwo;

    private boolean afterOperation = false;

    public NumericFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_numeric, container, false);

        TextView tvResult = view.findViewById(R.id.tvResult);

        ConstraintLayout constraintLayout = view.findViewById(R.id.idConstraintLayoutNumeric);

        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            View view1 = constraintLayout.getChildAt(i);
            if (view1 instanceof Button) {
                Button button = (Button) view1;

                if(isOperation(button.getText().toString())){
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            afterOperation = true;
                            if(isOperationDel(button.getText().toString())){
                                tvResult.setText("");
                                numberOne = null;
                                numberTwo = null;
                                operation = null;
                            }
                            else if(numberOne == null){
                                numberOne = Float.valueOf(tvResult.getText().toString());
                                operation = button.getText().toString();
                            }
                            else {
                                numberTwo = Float.valueOf(tvResult.getText().toString());
                                Float result = operate(numberOne, numberTwo, operation);
                                tvResult.setText(String.valueOf(result));
                                numberTwo = null;

                                if(isOperationEquals(button.getText().toString())){
                                    numberOne = null;
                                }
                                else {
                                    numberOne = result;
                                    operation = button.getText().toString();
                                }

                            }
                        }
                    });
                } else {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(afterOperation)
                                tvResult.setText(button.getText());
                            else
                                tvResult.setText(tvResult.getText().toString() + button.getText());

                            afterOperation = false;
                        }
                    });
                }
            }
        }

        return view;
    }

    private Float operate(Float numberOne, Float numberTwo, String operation) {
        float result = -1;
        if(operation.equals("+"))
            result = numberOne + numberTwo;
        if(operation.equals("-"))
            result = numberOne - numberTwo;
        if(operation.equals("*"))
            result = numberOne * numberTwo;
        if(operation.equals("/"))
            result = numberOne/numberTwo;

        return result;
    }

    public boolean isOperation(String entry){
        return entry.equals("+") || entry.equals("-") ||
               entry.equals("/") || entry.equals("*") ||
               entry.equals("=") || entry.equals("DEL");
    }

    public boolean isOperationDel(String entry){
        return entry.equals("DEL");
    }

    public boolean isOperationEquals(String entry){
        return entry.equals("=");
    }
}