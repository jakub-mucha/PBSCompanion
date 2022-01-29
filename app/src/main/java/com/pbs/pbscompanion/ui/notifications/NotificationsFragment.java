package com.pbs.pbscompanion.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pbs.pbscompanion.DAO;
import com.pbs.pbscompanion.R;
import com.pbs.pbscompanion.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private DAO dao;
    private SQLiteDatabase newsDB;
    private RadioGroup radioDepartmentGroup;
    private RadioButton radioDepartmentButton;
    private Button btnDisplay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dao = new DAO();
        dao.initializeDB();
        newsDB = dao.getNewsDB();
        TableLayout tableLayout = (TableLayout) root.findViewById(R.id.newsTable);

        radioDepartmentGroup=(RadioGroup) root.findViewById(R.id.department);
        btnDisplay=(Button) root.findViewById(R.id.select);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId= radioDepartmentGroup.getCheckedRadioButtonId();
                radioDepartmentButton= (RadioButton) root.findViewById(selectedId);
                String department = (String) radioDepartmentButton.getText();
                tableLayout.removeAllViews();
                Cursor cursor = newsDB.rawQuery("SELECT * FROM news WHERE department LIKE '" + department + "';", null);
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++)
                {
                    TableRow tableRow = new TableRow(v.getContext());
                    TextView text = new TextView(v.getContext());
                    text.setText(cursor.getString(1));
                    tableRow.addView(text);
                    tableLayout.addView(tableRow);

                    tableRow = new TableRow(v.getContext());
                    text = new TextView(v.getContext());
                    text.setText(cursor.getString(4));
                    tableRow.addView(text);
                    tableLayout.addView(tableRow);

                    tableRow = new TableRow(v.getContext());
                    text = new TextView(v.getContext());
                    text.setText(cursor.getString(2));
                    tableRow.addView(text);
                    tableLayout.addView(tableRow);

                    text = new TextView(v.getContext());
                    text.setText("");
                    tableRow = new TableRow(v.getContext());
                    tableRow.addView(text);

                    tableLayout.addView(tableRow);

                    cursor.moveToNext();
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

    public void displayNews() {

    }
}