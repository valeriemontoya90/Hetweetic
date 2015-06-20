package net.hetic.hetweetic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.hetic.hetweetic.Models.Tweet;
import net.hetic.hetweetic.R;

import java.util.ArrayList;

/**
 * Created by valerie on 20/06/15.
 */
public class ListTimelineAdapter extends BaseAdapter {

    private Context mContext;
    int layoutResourceId;
    ArrayList<Tweet> data;

    public ListTimelineAdapter(Context c, int layoutResourceId, ArrayList<Tweet> data) {
        this.mContext = c;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.item_timeline, parent, false);
        TextView text = (TextView) view.findViewById(R.id.tv_item_timeline);
        text.setText(data.get(position).getText());

        return view;
    }
}
