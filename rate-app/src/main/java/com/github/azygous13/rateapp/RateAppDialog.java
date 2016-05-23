package com.github.azygous13.rateapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created on 3/18/2016 AD.
 */
public class RateAppDialog extends DialogFragment implements View.OnClickListener {

    private Button laterButton;
    private Button noButton;
    private Button rateButton;

    public static RateAppDialog newInstance(String title, String description,
                                            String later, String noThanks,
                                            String rateNow, boolean isCancelable,
                                            boolean isShowIcon) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putString("later", later);
        args.putString("no_thanks", noThanks);
        args.putString("rate_now", rateNow);
        args.putBoolean("is_cancelable", isCancelable);
        args.putBoolean("is_show_icon", isShowIcon);

        RateAppDialog fragment = new RateAppDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public RateAppDialog() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_rate_us, container, false);

        setupTextDialog(view);
        setupIcon(view);
        setCancelable(getArguments().getBoolean("is_cancelable"));

        noButton.setOnClickListener(this);
        rateButton.setOnClickListener(this);
        laterButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v == laterButton) {
            SharedPrefUtils.resetPromptRateApp(v.getContext());
        } else if (v == noButton) {
            SharedPrefUtils.setShouldShowDialog(v.getContext(), false);
        } else if (v == rateButton) {
            openPlayStore();
        }
        dismiss();
    }

    private void setupTextDialog(View view) {
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView description = (TextView) view.findViewById(R.id.tv_description);
        laterButton = (Button) view.findViewById(R.id.btn_later);
        noButton = (Button) view.findViewById(R.id.btn_no_thanks);
        rateButton = (Button) view.findViewById(R.id.btn_rate_now);

        Bundle args = getArguments();
        title.setText(args.getString("title"));
        description.setText(args.getString("description"));
        noButton.setText(args.getString("no_thanks"));
        laterButton.setText(args.getString("later"));
        rateButton.setText(args.getString("rate_now"));
    }

    private void setupIcon(View view) {
        ImageView icon = (ImageView) view.findViewById(R.id.imv_icon);
        if (getArguments().getBoolean("is_show_icon", true)) {
            String packageName = getContext().getPackageName();
            try {
                Drawable iconDrawable = getContext().getPackageManager().getApplicationIcon(packageName);
                icon.setImageDrawable(iconDrawable);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            icon.setVisibility(View.VISIBLE);
        } else {
            icon.setVisibility(View.GONE);
        }
    }

    private void openPlayStore() {
        String packageName = getActivity().getPackageName();
        Uri uri = Uri.parse("market://details?id=" + packageName);
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (ActivityNotFoundException e) {
            uri = Uri.parse("http://play.google.com/store/apps/details?id=" + packageName);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}
