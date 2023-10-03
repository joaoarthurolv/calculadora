package br.ufrn.imd.calculadora.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.calculadora.R;

public class MediaFragment extends Fragment {

    public MediaFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);

        Button btnCalculate = view.findViewById(R.id.btnCalculate);
        TextView tvStatus = view.findViewById(R.id.textViewStatus);

        EditText gradeOne = (EditText) view.findViewById(R.id.editTextGradeOne);
        EditText gradeTwo = (EditText) view.findViewById(R.id.editTextGradeTwo);
        EditText gradeThree = (EditText) view.findViewById(R.id.editTextGradeThree);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvStatus.setText(R.string.status); // Resetar valor de situação
                Float gradeOneInt, gradeTwoInt, gradeThreeInt, media, mediaPorNota;

                gradeOneInt = getValue(gradeOne);
                gradeTwoInt = getValue(gradeTwo);
                gradeThreeInt = getValue(gradeThree);

                if(missingGrades(gradeTwoInt, gradeThreeInt)){
                    if(gradeTwoInt < 0 && gradeThreeInt < 0){
                        media = calculateMedia(gradeOneInt);
                        mediaPorNota = calculateMediaPorNota(gradeOneInt);

                        tvStatus.setText(tvStatus.getText() + getTwoGradesPartialApprovalText(media, mediaPorNota));
                    } else if(gradeTwoInt >= 0 && gradeThreeInt < 0){
                        media = calculateMedia(gradeOneInt, gradeTwoInt);
                        mediaPorNota = calculateMediaPorNota(gradeOneInt, gradeTwoInt);

                        tvStatus.setText(tvStatus.getText() + getPartialApprovalText(media, mediaPorNota));
                    }
                } else {
                    media = calculateMedia(gradeOneInt, gradeTwoInt, gradeThreeInt);
                    tvStatus.setText(tvStatus.getText() + " " + getResult(media));
                }
            }
        });

        return view;
    }

    private boolean missingGrades(Float gradeTwoInt, Float gradeThreeInt) {
        return gradeTwoInt < 0 || gradeThreeInt < 0;
    }

    public Float calculateMedia(Float gradeOne, Float gradeTwo, Float gradeThree){
        return (gradeOne+gradeTwo+gradeThree)/3;
    }

    public Float calculateMedia(Float gradeOne){
        return (7 * 3 - gradeOne)/2;
    }

    public Float calculateMedia(Float gradeOne, Float gradeTwo){
        return (7 * 3 - gradeOne - gradeTwo);
    }

    public Float calculateMediaPorNota(Float gradeOne){
        Float mediaPorNota = (5 * 3 - gradeOne) / 2;
        return mediaPorNota >= 3 ? mediaPorNota : 3;
    }

    public Float calculateMediaPorNota(Float gradeOne, Float gradeTwo){
        Float mediaPorNota = (5 * 3 - gradeOne - gradeTwo);
        return mediaPorNota >= 3 ? mediaPorNota : 3;
    }

    public String getResult(Float media){
        if(media < 5)
            return "Reprovado";
        else if(media < 7)
            return "Aprovado por nota!";
        else
            return "Aprovado!";
    }

    public String getPartialResultTwoGrades(Float media, Boolean porMedia){
        if(porMedia)
            return "Aprovado por média: " + media + " na 2° e 3° unidades!";

        return "Aprovado por nota: " + media + " na 2° e 3° unidades!";
    }

    public String getPartialResult(Float media, Boolean porMedia){
        if(porMedia)
            return "Aprovado por média: " + media + " na 3° unidade!";

        return "Aprovado por nota: " + media + " na 3° unidade!";
    }

    public Float getValue(EditText editText){
        if(editText.getText().toString().equals(""))
            return -1.0f;
        return Float.valueOf(editText.getText().toString());
    }

    public String getPartialApprovalText(Float media, Float mediaPorNota){
        return "\n" + getPartialResult(media, true) + "\n" + getPartialResult(mediaPorNota, false);
    }

    public String getTwoGradesPartialApprovalText(Float media, Float mediaPorNota){
        return "\n" + getPartialResultTwoGrades(media, true) + "\n" + getPartialResultTwoGrades(mediaPorNota, false);
    }
}