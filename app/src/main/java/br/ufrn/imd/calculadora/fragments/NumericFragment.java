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
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvResult.setText(tvResult.getText().toString() + button.getText());
                    }
                });
            }
        }

        return view;
    }
}