package edu.ucsb.cs.cs185.group.a185;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by wells on 3/18/17.
 */

public class FilteredListFragment extends Fragment{
    User user = User.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        FilteredPostAdapter filteredPostAdapter = new FilteredPostAdapter(getActivity(), user.getUsername(), 2);
        listView.setAdapter(filteredPostAdapter);
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
