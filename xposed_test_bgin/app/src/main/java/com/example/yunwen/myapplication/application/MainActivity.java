package com.example.yunwen.myapplication.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.yunwen.myapplication.R;
import com.example.yunwen.myapplication.dao.XModuleLog;
import com.example.yunwen.myapplication.dao.XModuleLogRepo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MainActivity is the process of
 * Configurator and Executor of sub-modules
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLog;
    private RecyclerView mRecyclerView;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLog = findViewById(R.id.btn_log);
        initListView();
    }

    public void onClick(final View v){
        int _algorithm_id = 0;
        if (v == v.findViewById(R.id.btn_log)){
            XModuleLogRepo repo = new XModuleLogRepo(v.getContext());
            XModuleLog xModuleLog = new XModuleLog();
            xModuleLog = repo.getColumnById(_algorithm_id);
            ArrayList<HashMap<String, String>> algorithmList =  repo.getAlgorithmList();
            //Show Db list
            if(algorithmList.size()!=0) {
                initRecyclerView(algorithmList);
            }else{
                Toast.makeText(v.getContext(), "No Content!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRecyclerView(ArrayList<HashMap<String, String>> algorithmList){
        mRecyclerView = findViewById(R.id.recycler_db);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbAdapter = new DbAdapter(this,algorithmList);
        mRecyclerView.setAdapter(dbAdapter);
    }

    public void initListView(){
        btnLog.callOnClick();
    }
}
