package es.kingcreek.swiftycompanion.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomProgressBar extends View {

    private int progress = 0;
    private int maxProgress = 100;
    private int barColor = Color.BLUE;
    private String centerText = "";
    private Paint paint;

    public CustomProgressBar(Context context) {
        super(context);
        init();
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setProgress(int progress) {
        if (progress >= 0 && progress <= maxProgress) {
            this.progress = progress;
            invalidate();
        }
    }

    public void setMaxProgress(int maxProgress) {
        if (maxProgress > 0) {
            this.maxProgress = maxProgress;
            invalidate();
        }
    }

    public void setBarColor(int color) {
        this.barColor = color;
        invalidate();
    }

    public void setCenterText(String text) {
        this.centerText = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Draw background
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0, 0, width, height, paint);

        // Draw progress bar
        paint.setColor(barColor);
        float progressBarWidth = (float) progress / maxProgress * width;
        canvas.drawRect(0, 0, progressBarWidth, height, paint);

        // Draw text in the center
        paint.setColor(Color.BLACK);
        paint.setTextSize(40f);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(centerText, width / 2f, height / 2f - ((paint.descent() + paint.ascent()) / 2), paint);
    }
}
