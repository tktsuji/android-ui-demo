package edu.ucsb.cs.inQuery.group.a185;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    FilteredPostAdapter filteredPostAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        int filterType = getArguments().getInt("filterType");
        String filter = getArguments().getString("filter");
        Log.d(filter, "filter");
        Log.d(Integer.toString(filterType), "filterType");
        filteredPostAdapter = new FilteredPostAdapter(getActivity(), filter, filterType);
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
