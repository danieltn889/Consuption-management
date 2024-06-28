package com.example.a21rp03122;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ExpensesAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 0;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
class expensesHolder {
    String ex_id,ex_typ,ex_qty,ex_price,ex,date;
}
