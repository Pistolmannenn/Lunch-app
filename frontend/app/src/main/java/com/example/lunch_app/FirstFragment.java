package com.example.lunch_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunch_app.databinding.FragmentFirstBinding;

import java.util.LinkedList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    String []data = {"Hello","Hii","Welcome"};
    int counter = 0;

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

        List<String> items = new LinkedList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        binding.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(data[counter%3]);
                counter++;
                adapter.notifyItemInserted(items.size()-1);
                if (items.size() > 10){
                    counter = 0;
                    for (int i = 10; i >= 0; i--) {
                        items.remove(i);
                        adapter.notifyItemRemoved(i);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}