package com.example.rabinhowlader.interdcr.model.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinhowlader.interdcr.R;
import com.example.rabinhowlader.interdcr.model.UserList;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<UserList> {

    private List<UserList> userLists = new ArrayList<>();
    public ProductAdapter(@NonNull Context context, int resource, int spinnerText, @NonNull List<String> userLists) {
        super(context, resource, spinnerText);
        this.userLists = userLists;
    }

    @Override
    public UserList getItem(int position) {
        return userLists.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }

    /**
     * Gets the state object by calling getItem and
     * Sets the state name to the drop-down TextView.
     *
     * @param position the position of the item selected
     * @return returns the updated View
     */
    private View initView(int position) {
        UserList userList = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.product_list, null);
        TextView textView =  v.findViewById(R.id.productSpinnerText);
        textView.setText(userList.getProductGroupList().toString());
        return v;

    }
}

