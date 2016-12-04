package in.lamiv.weatherforecast;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.lamiv.weatherforecast.utils.BaseFragment;


/**
 * This fragment will be loaded if the data is not available for any scenario
 */
public class TryAgainFragment extends BaseFragment {

    OnActionListener mOnActionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_try_again, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if ( context instanceof TryAgainFragment.OnActionListener) {
            mOnActionListener = (TryAgainFragment.OnActionListener)context;
        }
    }

    @Override
    public void onDetach() {
        mOnActionListener = null;
        super.onDetach();
    }

    public static TryAgainFragment newInstance(){
        TryAgainFragment fragment = new TryAgainFragment();
        return fragment;
    }

    @OnClick(R.id.button_try_again)
    void buttonClicked() {
        if (mOnActionListener != null) {
            mOnActionListener.onTryAgainClicked();
        }
    }

    public interface OnActionListener {
        void onTryAgainClicked();
    }

}
