package com.example.aplicaciontodolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public ToDoAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE));
        int amount = mCursor.getInt(mCursor.getColumnIndex(TaskContract.TaskEntry.CHECAR));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public CheckBox countText;

        public ToDoViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.txtContent);
            countText = itemView.findViewById(R.id.cboxDone);
        }
    }
}