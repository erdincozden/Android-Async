package com.example.erdinc.chptr15;

import android.content.Context;

/**
 * Created by erdinc on 12/29/17.
 */

public class AsyncTester extends BaseTester {

    private static String tag="AsyncTester1";
    public AsyncTester(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void test1(){
        MyLongTask myLongTask1=new MyLongTask(this.mReportTo,this.mContext,"Task1");
        myLongTask1.execute("String1,","String2","String3");
    }
    public void test2()
    {
        MyLongTask1 mlt = new MyLongTask1(this.mReportTo,this.mContext,"Task1");
        mlt.execute("String1","String2","String3");
    }
}
