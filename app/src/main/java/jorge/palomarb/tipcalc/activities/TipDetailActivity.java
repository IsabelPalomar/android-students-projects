package jorge.palomarb.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jorge.palomarb.tipcalc.R;

public class TipDetailActivity extends AppCompatActivity {

    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimeStamp)
    TextView txtTimeStamp;

    public final static String TIP_KEY = "tip";
    public final static String DATE_KEY = "timestamp";
    public final static String BILL_TOTAL_KEY = "total";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String strTotal = String.format(getString(R.string.tipdetail_message_bill), intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));
        String strTip = String.format(getString(R.string.global_message_tip),intent.getDoubleExtra(TIP_KEY, 0d));

        txtBillTotal.setText(strTotal);
        txtTip.setText(strTip);
        txtTimeStamp.setText(intent.getStringExtra(DATE_KEY));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            backToMain();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void backToMain()
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}
