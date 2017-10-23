package pe.com.push_tdp.push_tdp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.com.push_tdp.push_tdp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllPublicationsFragment extends Fragment {

    public AllPublicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.all_publications, container, false);
    }
}
