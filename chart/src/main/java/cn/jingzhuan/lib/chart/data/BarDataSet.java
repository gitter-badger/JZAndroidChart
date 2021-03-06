package cn.jingzhuan.lib.chart.data;

import cn.jingzhuan.lib.chart.Viewport;
import java.util.ArrayList;
import java.util.List;

import cn.jingzhuan.lib.chart.component.AxisY;
import cn.jingzhuan.lib.chart.component.AxisY.AxisDependency;


/**
 * Created by Donglua on 17/8/1.
 */

public class BarDataSet extends AbstractDataSet<BarValue> {

    private List<BarValue> mBarValues;
    private float mBarWidth = 20;
    private boolean mAutoBarWidth = false;
    private int mForceValueCount = -1;
    private float strokeThickness = 2;

    public BarDataSet(List<BarValue> barValues) {
        this(barValues, AxisY.DEPENDENCY_BOTH);
    }

    public BarDataSet(List<BarValue> mBarValues, @AxisDependency int axisDependency) {
        this.mBarValues = mBarValues;
        setAxisDependency(axisDependency);
    }

    @Override
    public int getEntryCount() {
        if (mForceValueCount > 0) return mForceValueCount;

        if (mBarValues != null) {
            return mBarValues.size();
        }
        return 0;
    }

    @Override
    public void calcMinMax(Viewport viewport) {

        if (mBarValues == null || mBarValues.isEmpty())
            return;

        mViewportYMax = -Float.MAX_VALUE;
        mViewportYMin = Float.MAX_VALUE;

        for (BarValue e : getVisiblePoints(viewport)) {
            calcMinMaxY(e);
        }

    }

    public void calcMinMaxY(BarValue e) {

        if (e == null) return;

        for (float v : e.getValues()) {
            mViewportYMin = Math.min(mViewportYMin, v);
            mViewportYMax = Math.max(mViewportYMax, v);
        }
    }

    @Override
    public void setValues(List<BarValue> values) {
        this.mBarValues = values;

        //setMinMax();
    }

    @Override
    public List<BarValue> getValues() {
        return mBarValues;
    }

    @Override
    public boolean addEntry(BarValue e) {
        if (e == null)
            return false;

        if (mBarValues == null) {
            mBarValues = new ArrayList<>();
        }

        calcMinMaxY(e);

        return mBarValues.add(e);
    }

    @Override
    public boolean removeEntry(BarValue e) {

        if (e == null) return false;

        calcMinMaxY(e);

        return mBarValues.remove(e);
    }

    @Override
    public int getEntryIndex(BarValue e) {
        return mBarValues.indexOf(e);
    }

    @Override
    public BarValue getEntryForIndex(int index) {
        return mBarValues.get(index);
    }

    public float getBarWidth() {
        return mBarWidth;
    }

    public void setBarWidth(float mBarWidth) {
        this.mBarWidth = mBarWidth;
    }

    public void setAutoBarWidth(boolean mAutoBarWidth) {
        this.mAutoBarWidth = mAutoBarWidth;
    }

    public boolean isAutoBarWidth() {
        return mAutoBarWidth;
    }

    public void setForceValueCount(int forceValueCount) {
        this.mForceValueCount = forceValueCount;
    }

    public int getForceValueCount() {
        return mForceValueCount;
    }

    public float getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(float strokeThickness) {
        this.strokeThickness = strokeThickness;
    }
}
