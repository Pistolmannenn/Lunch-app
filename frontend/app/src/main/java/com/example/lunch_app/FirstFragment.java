package com.example.lunch_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunch_app.databinding.FragmentFirstBinding;

import java.util.LinkedList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    Integer []data = {R.drawable.stamp};
    int counter = 0;
    SharedPreferences preferences;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Integer> items = new LinkedList<>();
        TextView textview = view.findViewById(R.id.textView_id);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        counter = preferences.getInt("counter", 0);

        Intent fetchData = getActivity().getIntent();
        String string = fetchData.getStringExtra("String");
        textview.setText(string);

        if (string != null && string.equals("Token +1")){
                counter++;
                preferences.edit().putInt("counter", counter).apply();
        }

        textview.setText(String.valueOf(counter));

        items.clear();
        for (int i = 0; i < counter; i++){
            items.add(data[i % data.length]);
        }
        adapter.notifyDataSetChanged();

        adapter.notifyItemInserted(items.size()-1);
        if (items.size() > 10){
            counter = 1;
            preferences.edit().putInt("counter", counter).apply();
            for (int i = 9; i >= 0; i--) {
                items.remove(i);
                adapter.notifyItemRemoved(i);
            }
            adapter.notifyDataSetChanged();
            textview.setText(String.valueOf(counter));
        }

        binding.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getin = new Intent(getActivity(), CameraActivity.class);
                getActivity().startActivity(getin);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}