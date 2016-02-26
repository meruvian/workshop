package org.meruvian.workshop.fragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.meruvian.workshop.fragment.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meruvian on 04/02/16.
 */
public class DetailNewsFragment extends Fragment {

    private TextView title, date, content;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = (TextView) view.findViewById(R.id.text_title);
        date = (TextView) view.findViewById(R.id.text_date);
        content = (TextView) view.findViewById(R.id.text_content);

        if (getArguments() != null) {
            title.setText(getArguments().getString("title", "-"));
            date.setText(dateFormat.format(new Date(getArguments().getLong("date", 0))));
            content.setText(getArguments().getString("content", "-"));
        }
    }
}
