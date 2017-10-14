package fcmdemo.peacock.example.com.dummy;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources system = Resources.getSystem();
        int hour_numberpicker_id = system.getIdentifier("hour", "id", "android");
        int minute_numberpicker_id = system.getIdentifier("minute", "id", "android");
        int ampm_numberpicker_id = system.getIdentifier("amPm", "id", "android");
        TimePicker time_picker=(TimePicker) findViewById(R.id.simpleTimePicker);
        NumberPicker hour_numberpicker = (NumberPicker) time_picker.findViewById(hour_numberpicker_id);
        NumberPicker minute_numberpicker = (NumberPicker) time_picker.findViewById(minute_numberpicker_id);
        NumberPicker ampm_numberpicker = (NumberPicker) time_picker.findViewById(ampm_numberpicker_id);





        fcmdemo.peacock.example.com.dummy.NumberPicker test=(fcmdemo.peacock.example.com.dummy.NumberPicker)findViewById(R.id.test);

        try {
            Field mScroller;
            mScroller = hour_numberpicker.getClass().getDeclaredField("mFlingScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this,null,true);

            // scroller.setFixedDuration(5000);
            // scrollBy(0,1500);


            mScroller.set(this, scroller);

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        test.setMinValue(0);
        test.setMaxValue(100);

        set_numberpicker_text_colour(hour_numberpicker);
        set_numberpicker_text_colour(minute_numberpicker);
        set_numberpicker_text_colour(ampm_numberpicker);
    }

    private void set_numberpicker_text_colour(NumberPicker number_picker){
        final int count = number_picker.getChildCount();
        final int color = Color.parseColor("#ff0000");

        for(int i = 0; i < count; i++){
            View child = number_picker.getChildAt(i);

            try{
                Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelpaint_field.setAccessible(true);

                ((Paint)wheelpaint_field.get(number_picker)).setColor(color);
                ((EditText)child).setTextColor(color);
                ((EditText)child).setTextSize(20);
                number_picker.invalidate();
            }
            catch(NoSuchFieldException e){
                e.printStackTrace();
            }
            catch(IllegalAccessException e){
                e.printStackTrace();
            }
            catch(IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }
}
