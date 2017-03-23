package a00914315.comp3717.bcit.ca.treetinder;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by panda on 2017-03-23.
 */

public class Step_Counter extends Fragment implements SensorEventListener{

    private TextView textView;
    private View view;
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.step_counter_layout, container, false);
        mSensorManager = (SensorManager)
                getActivity().getSystemService(Context.SENSOR_SERVICE);  ;
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        textView = (TextView) view.findViewById(R.id.stepCount);
        return view;
    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            textView.setText("Step Counter Detected : " + value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
    }
}
