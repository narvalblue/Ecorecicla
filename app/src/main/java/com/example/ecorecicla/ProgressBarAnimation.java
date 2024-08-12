package com.example.ecorecicla;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;


public class ProgressBarAnimation extends Animation {
    private Context context;
    public ProgressBar progressBar;
    public double desde;
    public double hasta;

    public ProgressBarAnimation(Context context, ProgressBar progressBar, double desde, double hasta) {
        super();
        this.context = context;
        this.progressBar = progressBar;
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    protected void applyTransformation(float time, Transformation t) {
        super.applyTransformation(time, t);
        double value = desde + (hasta - desde) * time;
        progressBar.setProgress((int) value);
    }

    public void progress(double sumaPlastico, double sumaPapel, double sumaVidrio, double sumaMetal, double sumaElectronicos) {
        ProgressBar loading = ((Activity)context).findViewById(R.id.plasticBar);
        ProgressBarAnimation progressBarAnimation = new ProgressBarAnimation(context, loading, 0, sumaPlastico);
        progressBarAnimation.setDuration(3000);
        loading.startAnimation(progressBarAnimation);

        ProgressBar loading2 = ((Activity)context).findViewById(R.id.electronicBar);
        progressBarAnimation = new ProgressBarAnimation(context, loading2, 0, sumaElectronicos);
        progressBarAnimation.setDuration(3000);
        loading2.startAnimation(progressBarAnimation);

        ProgressBar loading3 = ((Activity)context).findViewById(R.id.glassBar);
        progressBarAnimation = new ProgressBarAnimation(context, loading3, 0, sumaVidrio);
        progressBarAnimation.setDuration(3000);
        loading3.startAnimation(progressBarAnimation);

        ProgressBar loading4 = ((Activity)context).findViewById(R.id.metalBar);
        progressBarAnimation = new ProgressBarAnimation(context, loading4, 0, sumaMetal);
        progressBarAnimation.setDuration(3000);
        loading4.startAnimation(progressBarAnimation);

        ProgressBar loading5 = ((Activity)context).findViewById(R.id.paperBar);
        progressBarAnimation = new ProgressBarAnimation(context, loading5, 0, sumaPapel);
        progressBarAnimation.setDuration(3000);
        loading5.startAnimation(progressBarAnimation);

    }
}