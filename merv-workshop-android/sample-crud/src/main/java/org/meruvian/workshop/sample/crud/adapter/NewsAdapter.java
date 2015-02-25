package org.meruvian.workshop.sample.crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.meruvian.workshop.sample.crud.R;
import org.meruvian.workshop.sample.crud.entity.News;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ludviantoovandi on 25/02/15.
 */
public class NewsAdapter extends BaseAdapter {
    private List<News> newses = new ArrayList<News>();
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private Context context;

    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<News> categories) {
        this.context = context;
        this.newses = categories;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addNews(News news) {
        newses.add(news);
        notifyDataSetChanged();
    }

    public void addNews(List<News> categories) {
        this.newses.addAll(categories);
        notifyDataSetChanged();
    }

    public void clear() {
        newses.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newses.size();
    }

    @Override
    public Object getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.adapter_news, viewGroup, false);

            holder.name = (TextView) view.findViewById(R.id.text_title);
            holder.description = (TextView) view.findViewById(R.id.text_content);
            holder.date = (TextView) view.findViewById(R.id.text_date);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
        holder.name.setText(newses.get(position).getName());
        holder.description.setText(newses.get(position).getDescription());
        holder.date.setText(dateFormat.format(new Date(newses.get(position).getCreateDate())));

        return view;
    }

    private class ViewHolder {
        public TextView name;
        public TextView description;
        public TextView date;
    }
}
