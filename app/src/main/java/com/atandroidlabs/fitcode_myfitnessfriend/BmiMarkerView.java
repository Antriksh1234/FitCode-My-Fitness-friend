package com.atandroidlabs.fitcode_myfitnessfriend;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class BmiMarkerView extends MarkerView {

    private TextView monthTextView, bmiTextView;
    ArrayList<String> timePeriod;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */

    public BmiMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        monthTextView = findViewById(R.id.month);
        bmiTextView = findViewById(R.id.bmi);
    }

    public void setTimePeriod(ArrayList<String> timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String monthText = "Time " + (int) e.getX();
        monthTextView.setText(monthText);

        monthTextView.setText(timePeriod.get((int)e.getX() - 1));

        bmiTextView.setText("BMI: " + e.getY());
        // this will perform necessary layout stuff
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {
        if(mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2), getHeight());
            mOffset = new MPPointF(-(getWidth() / 2), 1f);
        }
        return mOffset;
    }
}
