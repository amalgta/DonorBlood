package com.styx.gta.donorblood.base;

        import android.content.Context;

/**
 * Created by amalg on 30-11-2016.
 */

public interface BaseAdapter<T> {
    void addItem(T item);
    void setContext(Context context);
}
