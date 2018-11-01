package kr.appfactory.billiard;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class NewsListAdapter  extends BaseAdapter {

    private Context  context;
    private List<News>  newsList;

    public NewsListAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.news, null);

        TextView subjectText = (TextView) v.findViewById(R.id.subjectText);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);

        subjectText.setText(newsList.get(i).getSubject());
        nameText.setText(newsList.get(i).getName());
        dateText.setText(newsList.get(i).getDate());

        v.setTag(newsList.get(i).getSubject());

        return v;

    }

}
