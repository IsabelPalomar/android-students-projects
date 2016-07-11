package jorge.palomarb.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jorge.palomarb.tipcalc.R;
import jorge.palomarb.tipcalc.TipCalcApp;
import jorge.palomarb.tipcalc.fragments.TipHistoryListFragment;
import jorge.palomarb.tipcalc.fragments.TipHistoryListFragmentListener;
import jorge.palomarb.tipcalc.models.TipRecord;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentaje)
    EditText inputPercentaje;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment)getFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener)fragment;



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            about();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit()
    {
        //Log.e(getLocalClassName(),"click en submit");
        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if(!strInputTotal.isEmpty())
        {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip),tipRecord.getTip());

            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
     public void handleClickIncrease()
    {
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease()
    {
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear()
    {
       fragmentListener.clearList();
    }

    private void handleTipChange(int change)
    {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if(tipPercentage > 0)
        {
            inputPercentaje.setText(String.valueOf(tipPercentage));
        }
    }


    public int getTipPercentage()
    {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentaje.getText().toString().trim();
        if(!strInputTipPercentage.isEmpty())
        {
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }
        else
        {
            inputPercentaje.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }


    private void hideKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try
        {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        }
        catch (NullPointerException npe)
        {
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strURL = app.getABOUT_URL();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strURL));
        startActivity(intent);
    }


}
