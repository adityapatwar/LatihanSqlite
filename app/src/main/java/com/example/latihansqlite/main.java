package com.example.latihansqlite;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class main extends Fragment  implements View.OnClickListener,RecyclerviewAdapter.OnUserClickListener{
    RecyclerView recyclerView;
    EditText edtName,edtAge;
    Button btnSubmit;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Orang> listPersonInfo;
    boolean test = true;


    public main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate (R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        context = getActivity ();
        recyclerView = view.findViewById (R.id.recyclerview);
        layoutManager = new LinearLayoutManager (context);
        recyclerView.setLayoutManager (layoutManager);

        edtName = view.findViewById (R.id.edtname);
        edtAge = view.findViewById (R.id.edtage);
        btnSubmit = view.findViewById (R.id.btnsubmit);

        btnSubmit.setOnClickListener(this);

        setupRecyclerView();
    }


    private void setupRecyclerView() {
        DatabaseHelper db=new DatabaseHelper(context);
        listPersonInfo=db.selectUserData();

        RecyclerviewAdapter adapter=new  RecyclerviewAdapter(context,listPersonInfo,this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsubmit){
            DatabaseHelper db = new DatabaseHelper(context);
            Orang currentPerson = new Orang();
            String btnStatus=btnSubmit.getText().toString();

            if(btnStatus.equals("Submit")) {
                currentPerson.setName(edtName.getText().toString());
                currentPerson.setAge(Integer.parseInt(edtAge.getText().toString()));
                db.insert(currentPerson);
            }

            if(btnStatus.equals("Update")){
                currentPerson.setName(edtName.getText().toString());
                currentPerson.setAge(Integer.parseInt(edtAge.getText().toString()));
                db.update(currentPerson);
            }

            setupRecyclerView();
            edtName.setText("");
            edtAge.setText("");
            edtName.setFocusable (true);
            edtName.setFocusableInTouchMode (true);
            btnSubmit.setText("Submit");
        }
    }
    @Override
    public void onUserClick(Orang currentPerson, String action) {

        if(action.equals("Edit")){
            edtName.setText(currentPerson.getName());
            edtName.setFocusable(false);

            edtAge.setText(currentPerson.getAge()+"");
            btnSubmit.setText("Update");
        }
        if(action.equals("Delete")){
            DatabaseHelper db=new DatabaseHelper(context);
            db.delete(currentPerson.getName());
            setupRecyclerView();
        }
    }



}
