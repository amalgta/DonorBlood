package com.styx.gta.donorblood.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.ui.util.TypeFaceProvider;

/**
 * Created by amal.george on 24-11-2016.
 */

public class FontTextView extends TextView {
    String fontname;

    void init() {
        if (fontname == null)
            fontname = getContext().getString(R.string.font_regular);
        getPaint().setTypeface(TypeFaceProvider.getTypeFaceWithExt(getContext(), fontname));

    }

    void processattr(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Font,
                0, 0);

        try {
            fontname = a.getString(R.styleable.Font_font);
        } finally {
            a.recycle();
        }


    }

    public FontTextView(Context context) {
        super(context);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        processattr(attrs);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        processattr(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        processattr(attrs);
        init();
    }
}
