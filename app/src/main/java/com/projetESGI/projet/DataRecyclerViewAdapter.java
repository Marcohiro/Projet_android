package com.projetESGI.projet;

import com.projetESGI.projet.Users.UsersContent.UsersItem;
import com.projetESGI.projet.ProjectFragment.OnListFragmentInteractionListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * {@link RecyclerView.Adapter} that can display a {@link UsersItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DataRecyclerViewAdapter extends  RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> implements EventListener<QuerySnapshot> {

    private final List<UsersItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Query query;
    private ListenerRegistration registration;

    public DataRecyclerViewAdapter(Query query, OnListFragmentInteractionListener listener){
        mValues = new ArrayList<>();
        mListener = listener;
        this.query = query;
    }

    public void startListening() {
        if (query != null && registration == null) {
            registration = query.addSnapshotListener(this);
        }
    }

    public void stopListening() {
        if (registration != null) {
            registration.remove();
            registration = null;
        }
        mValues.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).username);
        holder.mContentView.setText(mValues.get(position).password);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            //TODO: catch exception
            return;
        }
        for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
            switch (documentChange.getType()) {
                case ADDED:
                    mValues.add(documentChange.getNewIndex(), documentChange.getDocument().toObject(UsersItem.class));
                    notifyItemChanged(documentChange.getNewIndex());
                    break;
                case REMOVED:
                    mValues.remove(documentChange.getOldIndex());
                    notifyItemRemoved(documentChange.getOldIndex());
                    break;
                case MODIFIED:
                    if (documentChange.getNewIndex() == documentChange.getOldIndex()) {
                        mValues.set(documentChange.getOldIndex(), documentChange.getDocument().toObject(UsersItem.class));
                        notifyItemChanged(documentChange.getOldIndex());
                    } else {
                        mValues.remove(documentChange.getOldIndex());
                        mValues.add(documentChange.getNewIndex(), documentChange.getDocument().toObject(UsersItem.class));
                        notifyItemMoved(documentChange.getOldIndex(), documentChange.getNewIndex());
                    }
                    break;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public UsersItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
