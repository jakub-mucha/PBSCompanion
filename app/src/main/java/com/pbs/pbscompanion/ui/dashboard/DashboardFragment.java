package com.pbs.pbscompanion.ui.dashboard;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pbs.pbscompanion.R;
import com.pbs.pbscompanion.databinding.FragmentDashboardBinding;

import java.util.Arrays;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Resources res = root.getContext().getResources();

        ImageView image = (ImageView) root.findViewById(R.id.imageView);
        Button btnDisplay = (Button) root.findViewById(R.id.button);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText room = root.findViewById(R.id.room);
                if(room.getText().toString().contains("RCI")) {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("com.pbs.pbscompanion"+":drawable/"+"rci" , null, null)));
                }
                else if(room.getText().toString().contains("AN")) {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("com.pbs.pbscompanion"+":drawable/"+"an" , null, null)));
                }
                else if(stringContainsItemFromList(room.getText().toString(), new String[] {"A", "B", "C", "D", "E", "F", "G"})) {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("com.pbs.pbscompanion"+":drawable/"+"main" , null, null)));
                }
                else {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("com.pbs.pbscompanion"+":drawable/"+"base" , null, null)));
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}