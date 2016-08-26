package cn.xiaocool.hongyunschool.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaocool.hongyunschool.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherFragment extends Fragment {

    @BindView(R.id.fragment_teacher_elv_teacher)
    ExpandableListView fragmentTeacherElvTeacher;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

    }
}
