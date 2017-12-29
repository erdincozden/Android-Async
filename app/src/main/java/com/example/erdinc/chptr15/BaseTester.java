package com.example.erdinc.chptr15;

import android.content.Context;

/**
 * Created by erdinc on 12/27/17.
 */

public class BaseTester {

    protected IReportBack mReportTo;
    protected Context mContext;

    public BaseTester(Context ctx, IReportBack target) {
        mReportTo = target;
        mContext = ctx;
    }
}
