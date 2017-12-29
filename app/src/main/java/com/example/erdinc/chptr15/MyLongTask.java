package com.example.erdinc.chptr15;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.AnimationUtils;

public class MyLongTask extends AsyncTask<String,Integer,Integer> {

    IReportBack iReportBack;
    Context context;
    public String tag=null;
    ProgressDialog progressDialog=null;

    public MyLongTask(IReportBack iReportBack,Context context,String tag){
        this.context=context;
        this.iReportBack=iReportBack;
        this.tag=tag;
    }

    @Override
    protected void onPreExecute() {
        Utils.logThreadSignature(this.tag);

        progressDialog=ProgressDialog.show(context,"title","In Progress..",true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Utils.logThreadSignature(this.tag);
        this.reportThreadSignature();

        Integer i=values[0];
        iReportBack.reportBack(tag,"Progress:"+i.toString());
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Utils.logThreadSignature(this.tag);
        for(String  s: strings){
            Log.d(tag,"Processing:"+s);
        }
        for(int i=0;i<3;i++){
            Utils.sleepForInSecs(2);
            publishProgress(i);
        }
        return 1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Utils.logThreadSignature(this.tag);
        iReportBack.reportBack(tag,"onPostExecure result:"+integer);
        progressDialog.cancel();
    }

    protected void reportThreadSignature(){
        String s=Utils.getThreadSignature();
        iReportBack.reportBack(tag,s);
    }
}
