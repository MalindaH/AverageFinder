package commalindah.httpsgithub.averagefinder;

import android.content.Context;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int numElementsAdded;
    private static double[] numbersToAverage;

    private EditText mNumberToAdd;
    private TextView mErrorMessageDisplay;
    private TextView mAverageDisplay;

    @Override
    /**
     * onCreate is the method that is run when we create an instance of this activity
     *
     * @param savedInstanceState is a Bundle object that allows the Activity to restore
     *                           itself to a previously saved instance
     * @return Nothing is returned
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberToAdd = (EditText) findViewById(R.id.et_number_input);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message);
        mAverageDisplay = (TextView) findViewById(R.id.tv_display_average);

        numElementsAdded = 0;
        numbersToAverage = new double[20];
    }

    /**
     * addNumToArray adds a new number to the array of numbers we will need
     *               to average
     *
     * @param vw is the View that is related to this method
     * @return Nothing is returned
     */
    public void addNumToArray( View vw )
    {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        double userInput = Double.parseDouble( mNumberToAdd.getText().toString() );

        if( numElementsAdded >= 20 )
        {
            mErrorMessageDisplay.setText("You have added the maximum amount of numbers. Please press Calculate Average.");

            //This is how I close my keyboard
            if( inputManager != null)
            {
                inputManager.hideSoftInputFromWindow(vw.getApplicationWindowToken(),0);
            }
        }
        else
        {
            numbersToAverage[ numElementsAdded ] = userInput;
            numElementsAdded++;

            mErrorMessageDisplay.setText("Number added successfully");

            mNumberToAdd.setText("");
            mNumberToAdd.requestFocus();

            //This is how I open my keyboard
            if( inputManager != null)
            {
                inputManager.showSoftInput( mNumberToAdd, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    /**
     * calcAverage calculates the average of the numbers the user entered and displays this average
     *
     * @param vw is the View that is related to this method
     * @return Nothing is returned
     */
    public void calcAverage( View vw )
    {
        double average = 0.0;
        double sum = 0.0;

        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        if( inputManager != null)
        {
            inputManager.hideSoftInputFromWindow(vw.getApplicationWindowToken(),0);
        }

        if( numElementsAdded <= 0 )
        {
            mErrorMessageDisplay.setText("You must add at least one number to calculate the average.");
        }
        else
        {
            mErrorMessageDisplay.setText("");
            mNumberToAdd.setText("");

            for( int i = 0; i < numElementsAdded; i++)
            {
                sum += numbersToAverage[i];
            }

            average = sum / (numElementsAdded * 1.0);

            mAverageDisplay.setText( "The average of your numbers is: " + average );
        }
    }
}











