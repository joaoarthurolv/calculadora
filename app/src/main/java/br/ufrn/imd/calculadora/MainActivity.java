package br.ufrn.imd.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import br.ufrn.imd.calculadora.fragments.MediaFragment;
import br.ufrn.imd.calculadora.fragments.NumericFragment;

public class MainActivity extends AppCompatActivity {

    private NumericFragment numericFragment;

    private MediaFragment mediaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numericFragment = new NumericFragment();
        mediaFragment = new MediaFragment();
    }

    public void goToNumericFragment(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.header, numericFragment);
        transaction.commit();
    }

    public void goToMediaFragment(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.header, mediaFragment);
        transaction.commit();
    }
}