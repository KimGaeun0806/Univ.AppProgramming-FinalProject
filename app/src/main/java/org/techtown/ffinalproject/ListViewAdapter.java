package org.techtown.ffinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewAdapterData> list = new ArrayList<ListViewAdapterData>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview,viewGroup,false);
        }

        TextView tvNum = (TextView)view.findViewById(R.id.item_tv_num);
        TextView tvDate = (TextView)view.findViewById(R.id.item_tv_date);
        TextView tvHappy = (TextView)view.findViewById(R.id.item_tv_happy);
        TextView tvSad = (TextView)view.findViewById(R.id.item_tv_sad);
        TextView tvEmotion = (TextView)view.findViewById(R.id.item_tv_emotion);
        TextView tvColor = (TextView)view.findViewById(R.id.item_tv_color);


        ListViewAdapterData listdata = list.get(i);

        tvNum.setText(Integer.toString(listdata.getNum())); //원래 int형이라 String으로 형 변환
        tvDate.setText(listdata.getDate());
        tvHappy.setText(listdata.getHappy());
        tvSad.setText(listdata.getSad());
        tvEmotion.setText(listdata.getEmotion());
        tvColor.setText("●");
        tvColor.setTextColor(listdata.getColor());

        return view;
    }

    public void addItemToList(int num, String date, String happy, String sad, String emotion, int color ){
        ListViewAdapterData listdata = new ListViewAdapterData();

        listdata.setNum(num);
        listdata.setDate(date);
        listdata.setHappy(happy);
        listdata.setSad(sad);
        listdata.setEmotion(emotion);
        listdata.setColor(color);

        list.add(listdata);

    }
}