package edu.ucsb.cs.cs185.group.a185;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.attr.fragment;


/**
 * A fragment representing a list of Items.
 */
public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        PostAdapter postAdapter = new PostAdapter(getActivity());
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter(postAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(),PostActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        return view;
    }
}
