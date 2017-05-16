package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import bike.circle.app.R;

public class MyTravelNoteActivity extends BaseActivity implements View.OnClickListener{

    private Button mWriteTravelNote;
    private FrameLayout frameLayout;

    public static Intent getIntent(Context context){
        return new Intent(context,MyTravelNoteActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_travel_note;
    }

    @Override
    protected void initView() {
        mWriteTravelNote = (Button) findViewById(R.id.wirte_travel_note);
        frameLayout = (FrameLayout) findViewById(R.id.test);
    }

    @Override
    protected void bindEvent() {
        mWriteTravelNote.setOnClickListener(this);
        frameLayout.setOnClickListener(this);
    }

    @Override
    protected void service() {

    }

    @Override
    protected void init() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wirte_travel_note:startActivity(WriteActivity.getIntent(MyTravelNoteActivity.this));break;
            case R.id.test:startActivity(TravelNoteDetailedActivity.getIntent(MyTravelNoteActivity.this));break;
        }
    }
}
