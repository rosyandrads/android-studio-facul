package com.example.applicationobjectssqlite;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ContentInfoCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    EditText editTextRG;
    EditText editTextNome;
    EditText editTextEmail;
    EditText editTextRA;
    EditText editTextCurso;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextRG = findViewById(R.id.editTextRG);
        editTextNome = findViewById(R.id.editTextnome);
        editTextEmail= findViewById(R.id.editTextEmail);
        editTextRA = findViewById(R.id.editTextRA);
        editTextCurso = findViewById(R.id.editTextCurso);

        spinner = findViewById(R.id.spinner);

        spinner.setOnItemClickListener(this);

        LoadSpinnerData();


    }

    private void LoadSpinnerData() {
        DatabaseHandler databaseHandler= new DatabaseHandler(getApplicationContext());
        List<Aluno> alunos = databaseHandler.getAllAluno();

        ArrayAdapter<Aluno> dataAdapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_spinner_item, alunos);

        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        spinner.setAdapter(dataAdapter);
    }

    public void addAluno(View view){
        Aluno aluno = new Aluno(editTextRG.getText().toString(),
                                editTextNome.getText().toString(),
                                editTextEmail.getText().toString(),
                                editTextRA.getText().toString(),
                                editTextCurso.getText().toString());


        if (aluno.getRG().trim().length() >0){
            DatabaseHandler databaseHandler= new DatabaseHandler(getApplicationContext());
            databaseHandler.insertLabel(aluno);

            editTextRG.setText("");
            editTextNome.setText("");
            editTextEmail.setText("");
            editTextRA.setText("");
            editTextCurso.setText("");

            InputMethodManager inputMethodManager= (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editTextNome.getWindowToken(), 0);

            LoadSpinnerData();
        } else{
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent,View view, int position, long id) {

        Aluno aluno = (Aluno) parent.getItemAtPosition(position);


        Toast.makeText(parent.getContext(), ""+ aluno, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}