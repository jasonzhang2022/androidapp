package com.flexdms.tipcalculator.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;

import com.flexdms.tipcalculator.CalculateActivity;
import com.flexdms.tipcalculator.R;


public class CalculateActivityTest extends ActivityInstrumentationTestCase2<CalculateActivity>{

	CalculateActivity mFirstTestActivity;
	EditText totalCost;
	public CalculateActivityTest() {
	
		super(CalculateActivity.class);
		System.out.println("constructor is called");
	}

	@Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);

        mFirstTestActivity = getActivity();
        totalCost =
                (EditText) mFirstTestActivity
                .findViewById(R.id.totalCost);
    	System.out.println("setup is called");
    }
	
	public void testPreconditions() {
	    assertNotNull("mFirstTestActivity is null", mFirstTestActivity);
	    assertNotNull("mFirstTestText is null", totalCost);
		System.out.println("preconditions is called");
	}
	
	@SmallTest
	public void testTotalCost(){
		setText(totalCost, "32.55");
		System.out.println("test is called");
		
	}
	@SmallTest
	public void testTax(){
		setText(((EditText)mFirstTestActivity.findViewById(R.id.tax)), "66");
		System.out.println("test is called");
		
	}

	public void setText(final EditText field, String text){
		getInstrumentation().runOnMainSync(new Runnable() {
		    @Override
		    public void run() {
		        field.requestFocus();
		    }
		});
		getInstrumentation().waitForIdleSync();
		getInstrumentation().sendStringSync(text);
		getInstrumentation().waitForIdleSync();
	}
}
