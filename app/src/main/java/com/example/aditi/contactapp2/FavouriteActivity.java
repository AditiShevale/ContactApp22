package com.example.aditi.contactapp2;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aditi.contactapp2.Database.Contract;
import com.example.aditi.contactapp2.Pojo.FavAdapter;

public class FavouriteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mfavRecyclerView;
    private static final int LOADER_ID = 1;
    private FavAdapter mFavAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        mfavRecyclerView = findViewById(R.id.favrecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new
                LinearLayoutManager(this);
        mfavRecyclerView.setLayoutManager(mLayoutManager);
        mfavRecyclerView.setItemAnimator(new DefaultItemAnimator());


        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            @Override
            protected void onStartLoading() {
                forceLoad();
            }
            @Nullable
            @Override
            public Cursor loadInBackground() {
                return getContentResolver()
                        .query(Contract.Fav.CONTENT_URI, null,
                                null, null, null);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mFavAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mFavAdapter.swapCursor(null);
    }
}
