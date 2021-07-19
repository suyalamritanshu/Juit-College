package com.example.juitcollege.Fragments.About;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.juitcollege.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel>list;




    public AboutFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_baseline_computer_24, "Computer Science", "To become a Center of Excellence in the Computer Science & Engineering and Information Technology (CSE&IT) discipline with state of art research and teaching environment."));
        list.add(new BranchModel(R.drawable.ic_baseline_computer_24, "Civil Engineering", "To strive for excellence, knowledge creation and research contribution to the field of Civil Engineering, and to serve the society and the nation with missionary zeal, thus to be recognized internationally as one of the best centres of research and education in all the areas of Civil Engineering."));

        adapter = new BranchAdapter(getContext(), list);

        viewPager = view.findViewById(R.id.viewPager);
        
        viewPager.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.collegeimage);
        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/juit-admin.appspot.com/o/juit01.jpg?alt=media&token=68fa9a0d-69ef-453d-af7c-35e06c5b1155")
                .into(imageView);
        
        return view;
    }
}