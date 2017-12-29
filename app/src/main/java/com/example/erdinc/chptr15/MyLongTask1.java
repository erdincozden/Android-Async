package com.example.erdinc.chptr15;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;


public class MyLongTask1 extends AsyncTask<String,Integer,Integer> implements DialogInterface.OnCancelListener {

    IReportBack iReportBack;
    Context context;
    public String tag=null;
    ProgressDialog progressDialog=null;

    MyLongTask1(IReportBack iReportBack,Context context,String tag){
        this.iReportBack=iReportBack;
        this.context=context;
        this.tag=tag;
    }

    @Override
    protected void onPreExecute() {
        Utils.logThreadSignature(this.tag);
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("title");
        progressDialog.setMessage("In Progress");
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(5);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Utils.logThreadSignature(this.tag);
        this.reportThreadSignature();

        Integer i=values[0];
        iReportBack.reportBack(tag,"Progress:"+i.toString());
        progressDialog.setProgress(i);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Utils.logThreadSignature(this.tag);
        iReportBack.reportBack(tag,"onPostExecute:"+integer);
        progressDialog.cancel();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        iReportBack.reportBack(tag,"Cancelled..");
        this.cancel(true);
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Utils.logThreadSignature(this.tag);

        for(String s :strings)
        {
            Log.d(tag, "Processing:" + s);
            //r.reportTransient(tag, "Processing:" + s);
        }
        for (int i=0;i<5;i++)
        {
            Utils.sleepForInSecs(2);
            publishProgress(i);
        }
        return 1;
    }

    protected void reportThreadSignature()
    {
        String s = Utils.getThreadSignature();
        iReportBack.reportBack(tag,s);
    }
}
