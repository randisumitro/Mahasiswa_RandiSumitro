package com.example.mahasiswa_aulyafani;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mahasiswa_aulyafani.db.DbHelper;
import com.example.mahasiswa_aulyafani.model.Mahasiswa;

public class UpdateActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private EditText etName, etNim;
    private Button btnSave;
    private Mahasiswa student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Init DB
        dbHelper = new DbHelper(this);

        // Init View
        etName = findViewById(R.id.edt_name);
        etNim = findViewById(R.id.edt_nim);
        btnSave = findViewById(R.id.btn_submit);

        // Get data dari intent
        Intent intent = getIntent();
        student = (Mahasiswa) intent.getSerializableExtra("user");

        if (student != null) {
            etName.setText(student.getName());
            etNim.setText(student.getNim());
        }

        // Aksi tombol update
        btnSave.setOnClickListener((View v) -> {
            if (student != null) {
                dbHelper.updateUser(
                        student.getId(),
                        etNim.getText().toString(),
                        etName.getText().toString()
                );
                Toast.makeText(UpdateActivity.this, "Update berhasil!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(UpdateActivity.this, "Data tidak ditemukan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
