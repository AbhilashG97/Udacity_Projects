package github.com.basiccalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etOperand1, etOperand2;
    private String operand1;
    private String operand2;
    private String result;
    private String operatorSelected;
    private TextView tvResult;
    private Spinner spinner;

    public String getOperatorSelected() {
        return operatorSelected;
    }

    public void setOperatorSelected(String operatorSelected) {
        this.operatorSelected = operatorSelected;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.operator_spinner);
        tvResult = findViewById(R.id.tv_result);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operator_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        etOperand1 = findViewById(R.id.et_operand1);
        etOperand2 = findViewById(R.id.et_operand2);
        buttonListener();
        spinnerListener();
    }

    public void getInputs() {
        if (!(android.text.TextUtils.isEmpty(etOperand1.getText()) &&
                android.text.TextUtils.isEmpty(etOperand2.getText()))) {
            setOperand1(etOperand1.getText().toString());
            setOperand2(etOperand2.getText().toString());
        } else {
            Toast.makeText(this, "Operands are empty !", Toast.LENGTH_SHORT).show();
        }
    }


    public void spinnerListener() {
        spinner = findViewById(R.id.operator_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                setOperatorSelected(adapterView.getItemAtPosition(position).toString());
                Log.v("Operator selected ", getOperatorSelected());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void buttonListener() {
        Button button = findViewById(R.id.btn_calculate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("selected op onClick", getOperatorSelected());
                getInputs();
                try {
                    switch (getOperatorSelected()) {
                        case "+":
                            setResult(getSum().toString());
                            Log.v("Addition", getResult());
                            break;
                        case "-":
                            setResult(getDifference().toString());
                            Log.v("Subtraction", getResult());
                            break;
                        case "*":
                            setResult(getProduct().toString());
                            Log.v("Multiplication", getResult());
                            break;
                        case "/":
                            setResult(getDivisionResult().toString());
                            Log.v("Division", getResult());
                            break;
                        case "%":
                            setResult(getRemainder().toString());
                            Log.v("Modulo", getResult());
                            break;
                        case "^":
                            setResult(getPower().toString());
                            Log.v("Power function", getResult());
                            break;
                    }
                    tvResult.setText(getResult());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private Double getPower() {
        return Math.pow(Double.valueOf(getOperand1()), Double.valueOf(getOperand2()));
    }

    private Double getRemainder() {
        Double divisionResult = 0.0;
        try {
            divisionResult = Double.valueOf(getOperand1()) % Double.valueOf(getOperand2());
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return divisionResult;
    }

    private Double getDivisionResult() {
        Double divisionResult = 0.0;
        try {
            divisionResult = Double.valueOf(getOperand1()) / Double.valueOf(getOperand2());
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return divisionResult;
    }

    private Double getProduct() {
        return Double.valueOf(getOperand1()) * Double.valueOf(getOperand2());
    }


    private Double getDifference() {
        return Double.valueOf(getOperand1()) - Double.valueOf(getOperand2());
    }


    private Double getSum() {
        return Double.valueOf(getOperand1()) + Double.valueOf(getOperand2());
    }

}
