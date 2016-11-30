package android.db.me_crossover;

import android.db.me_crossover.helper.DBHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 * A login screen that offers login via email/password.
 */
public class SchedularActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private ConferenceSchedularTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mTitleView;
    private AutoCompleteTextView mDetailView;
    private AutoCompleteTextView mStartDateView;
    private AutoCompleteTextView mEndDateView;
    private AutoCompleteTextView mVenueView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedular);
        // Set up the login form.
        mTitleView = (AutoCompleteTextView) findViewById(R.id.title);
        mDetailView = (AutoCompleteTextView) findViewById(R.id.description);
        mStartDateView = (AutoCompleteTextView) findViewById(R.id.fromDate);
        mEndDateView = (AutoCompleteTextView) findViewById(R.id.toDate);
        mVenueView = (AutoCompleteTextView) findViewById(R.id.venue);


        Button mScheduleBtn = (Button) findViewById(R.id.schedule_button);
        mScheduleBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSchedule();
            }
        });

    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void saveSchedule() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mTitleView.setError(null);
        mDetailView.setError(null);
        mStartDateView.setError(null);
        mEndDateView.setError(null);
        mVenueView.setError(null);

        // Store values at the time of the login attempt.
        String title = mTitleView.getText().toString();
        String detail = mDetailView.getText().toString();
        String start_date = mStartDateView.getText().toString();
        String end_date = mEndDateView.getText().toString();
        String venue = mVenueView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(title) ) {
            mTitleView.setError(getString(R.string.error_field_required));
            focusView = mTitleView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(detail)) {
            mDetailView.setError(getString(R.string.error_field_required));
            focusView = mDetailView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(start_date)) {
            mStartDateView.setError(getString(R.string.error_field_required));
            focusView = mStartDateView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(end_date)) {
            mEndDateView.setError(getString(R.string.error_field_required));
            focusView = mEndDateView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(venue)) {
            mVenueView.setError(getString(R.string.error_field_required));
            focusView = mVenueView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new ConferenceSchedularTask(title, detail,start_date,end_date,venue,0);
            mAuthTask.execute((Void) null);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class ConferenceSchedularTask extends AsyncTask<Void, Void, Boolean> {

        private final String mTitle;
        private final String mDetail;
        private final String mStartDate;
        private final String mEndDate;
        private final String mVenue;
        private final int mManager;

        ConferenceSchedularTask(String title, String detail,String start_date, String end_date,String venue, int manager) {
            mTitle = title;
            mDetail = detail;
            mStartDate = start_date;
            mEndDate = end_date;
            mVenue = venue;
            mManager = manager;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }


            DBHelper.getInstance( SchedularActivity.this ).scheduleConference(mTitle,
            mDetail ,
            mStartDate ,
            mEndDate ,
            mVenue ,
            mManager );

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
            } else {

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;

        }
    }
}

