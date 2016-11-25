package activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.fragments.HomeFragment;
import com.styx.gta.donorblood.fragments.HomeFragment2;
import com.styx.gta.donorblood.interfaces.UserActionListener;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BaseActivity extends AppCompatActivity implements UserActionListener {


    protected boolean isFragmentExistsInBackStack(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null)
            return true;
        else
            return false;
    }

    public void addFragment(final int mContainer, final Fragment mFragment,
                            final String mTag) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(mContainer, mFragment, mTag);
        fragmentTransaction.addToBackStack(mTag);
        fragmentTransaction.commit();
    }

    public void popBackStack(String tag, int flag) {
        this.getSupportFragmentManager().popBackStack(tag, flag);
    }

    public BaseFragment getTopFragment() {
        return ((BaseFragment) this.getSupportFragmentManager().findFragmentById(R.id.fl_content));
    }

    @Override
    public void doUserAction(UserAction mUserAction, Bundle... mBundle) {
        Fragment mFragment;
        switch (mUserAction) {
            case HOME:
                if (isFragmentExistsInBackStack(HomeFragment.TAG)) {
                    if (getTopFragment() instanceof HomeFragment)
                        return;
                    popBackStack(HomeFragment.TAG, 0);
                } else {
                    mFragment = new HomeFragment();
                    addFragment(R.id.fl_content, mFragment, HomeFragment.TAG);
                }
                break;
            case FIRST:
                if (isFragmentExistsInBackStack(HomeFragment2.TAG)) {
                    if (getTopFragment() instanceof HomeFragment2)
                        return;
                    popBackStack(HomeFragment2.TAG, 0);
                } else {
                    if(mBundle!=null) {
                        mFragment = new HomeFragment2();
                        mFragment.setArguments(mBundle[0]);
                        addFragment(R.id.fl_content, mFragment, HomeFragment2.TAG);
                    }else {
                        Log.e("ERROR","ERROR");
                    }
                }
                break;
        }
    }
}
