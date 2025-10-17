package com.example.mahasiswa_aulyafani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;  // ✅ Import wajib

import android.os.Bundle;

import com.example.mahasiswa_aulyafani.adapter.MahasiswaAdapter; // ✅ sesuaikan package adapter
import com.example.mahasiswa_aulyafani.db.DbHelper;              // ✅ sesuaikan package db
import com.example.mahasiswa_aulyafani.model.Mahasiswa;          // ✅ sesuaikan package model

import java.util.ArrayList;

public class ListMahasiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> studentsArrayList;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        recyclerView = findViewById(R.id.rview);

        // inisialisasi adapter & dbHelper
        adapter = new MahasiswaAdapter(this);
        dbHelper = new DbHelper(this);

        // ambil data mahasiswa dari database
        studentsArrayList = dbHelper.getAllUsers();
        adapter.setListStudents(studentsArrayList);

        // setup RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsArrayList = dbHelper.getAllUsers();
        adapter.setListStudents(studentsArrayList);
        adapter.notifyDataSetChanged();
    }
}
