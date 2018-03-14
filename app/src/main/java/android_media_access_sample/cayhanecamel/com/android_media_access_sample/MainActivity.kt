package android_media_access_sample.cayhanecamel.com.android_media_access_sample

import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.LoaderManager.LoaderCallbacks
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity(), LoaderCallbacks<Cursor> {


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

        val queryUri = MediaStore.Files.getContentUri("external")

        val cursorLoader = CursorLoader(
                this,
                queryUri,
                arrayOf(MediaStore.Images.Media.BUCKET_ID,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.PICASA_ID),
                selection,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC")

        return cursorLoader
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        Log.d(javaClass.simpleName, "onLoaderReset called.");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportLoaderManager.initLoader(0, null, this);

    }

    override fun onLoadFinished(loader: Loader<Cursor>?, c: Cursor?) {
        Log.d(javaClass.simpleName, "onLoadFinished called.")
        c!!.moveToFirst()
        do {
            for (i in 0 until c.getColumnCount()) {
                Log.d(javaClass.simpleName, c.getColumnName(i) + " : " + c.getString(i))
            }
        } while (c.moveToNext())
    }
}
