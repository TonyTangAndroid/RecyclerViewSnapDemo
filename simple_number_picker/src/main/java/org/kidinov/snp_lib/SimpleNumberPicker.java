package org.kidinov.snp_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleNumberPicker extends LinearLayout {
    private OnNewValueSelectedListener onNewValueSelected;
    private LinearLayoutManager llm;

    public SimpleNumberPicker(Context context) {
        this(context, null);
    }

    public SimpleNumberPicker(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.SNPickerStyle);
    }

    public SimpleNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        RecyclerView rv = new RecyclerView(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SNPicker, defStyleAttr, 0);
        try {
            final Params params = readParams(typedArray);

            llm = new LinearLayoutManager(context,
                    typedArray.getBoolean(R.styleable.SNPicker_snp_vertical, false) ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(llm);

            SimplePickerAdapter adapter = new SimplePickerAdapter(context, params);
            rv.setAdapter(adapter);

            rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (onNewValueSelected != null) {
                        onNewValueSelected.newValueSelected(params.getMin() + getValue());
                    }
                }
            });

        } finally {
            typedArray.recycle();
        }
        addView(rv);
    }

    private Params readParams(TypedArray ta) {
        Params.Builder builder = new Params.Builder();
        builder.setSmallTextColor(ta.getColor(R.styleable.SNPicker_snp_smallTextColor, Color.parseColor("#727272")));
        builder.setBigTextColor(ta.getColor(R.styleable.SNPicker_snp_bigTextColor, Color.parseColor("#212121")));

        builder.setBigTextSize(ta.getDimension(R.styleable.SNPicker_snp_bigTextSize, EnvHelper.pxFromDp(getContext(), 10)));
        builder.setSmallTextSize(ta.getDimension(R.styleable.SNPicker_snp_smallTextSize, EnvHelper.pxFromDp(getContext(), 3)));

        builder.setNotchSize(ta.getDimension(R.styleable.SNPicker_snp_notchSize, EnvHelper.pxFromDp(getContext(), 10)));
        builder.setNotchColor(ta.getColor(R.styleable.SNPicker_snp_notchColor, Color.parseColor("#B6B6B6")));

        builder.setDelimNumber(ta.getInt(R.styleable.SNPicker_snp_delimNumber, 10));

        builder.setMin(ta.getInt(R.styleable.SNPicker_snp_min, 0));
        builder.setMax(ta.getInt(R.styleable.SNPicker_snp_max, 5000));

        builder.setVertical(ta.getBoolean(R.styleable.SNPicker_snp_vertical, false));
        return builder.build();
    }

    private int getValue() {
        return llm.findFirstVisibleItemPosition() + (llm.findLastVisibleItemPosition() - llm.findFirstVisibleItemPosition()) / 2;
    }

    public void setOnNewValueSelectedListener(OnNewValueSelectedListener onNewValueSelected) {
        this.onNewValueSelected = onNewValueSelected;
    }
}
