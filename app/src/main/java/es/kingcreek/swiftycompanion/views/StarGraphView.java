package es.kingcreek.swiftycompanion.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class StarGraphView extends View {

    private int numPoints = 5; // Número de puntos en la estrella
    private float[] dataPoints = {0.5f, 0.8f, 0.6f, 0.4f, 0.7f}; // Datos para cada punto

    private Paint linePaint;
    private Paint dataPointPaint;

    public StarGraphView(Context context) {
        super(context);
        init();
    }

    public StarGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(android.R.color.black));
        linePaint.setStrokeWidth(2f);

        dataPointPaint = new Paint();
        dataPointPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        dataPointPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        float radius = Math.min(centerX, centerY) * 0.8f;

        float angle = (float) (2 * Math.PI / numPoints);

        // Dibujar líneas conectando los puntos alrededor del círculo
        for (int i = 0; i < numPoints; i++) {
            float x1 = centerX + (float) (radius * Math.cos(i * angle));
            float y1 = centerY + (float) (radius * Math.sin(i * angle));

            float x2 = centerX + (float) (radius * Math.cos((i + 1) * angle));
            float y2 = centerY + (float) (radius * Math.sin((i + 1) * angle));

            canvas.drawLine(x1, y1, x2, y2, linePaint);
        }

        // Dibujar puntos en los extremos de las líneas
        for (int i = 0; i < numPoints; i++) {
            float x = centerX + (float) (radius * dataPoints[i] * Math.cos(i * angle));
            float y = centerY + (float) (radius * dataPoints[i] * Math.sin(i * angle));

            canvas.drawCircle(x, y, 10, dataPointPaint);
        }
    }
}