package com.k3kc.fragmentloginformexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;
    private  List<LoginModel> listOfLogins;
    private ILogin mILoginInterface;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String logins = getArguments().getString("logins");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<LoginModel>>(){}.getType();
        listOfLogins = (List<LoginModel>)gson.fromJson(logins, listType);
        Log.d("AAA", "SIZE OF LOGINS LIST " + listOfLogins.size());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        mLayoutManager = new LinearLayoutManager(view.getContext());

        mRecyclerView = view.findViewById(R.id.contacts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerAdapter = new RecyclerAdapter(listOfLogins);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }
}
