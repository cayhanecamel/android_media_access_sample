package android_media_access_sample.cayhanecamel.com.android_media_access_sample

import android.provider.MediaStore
import android.support.v4.content.CursorLoader
import android.support.v7.app.AppCompatActivity

/**
 * Created by suguru.annaka on 2018/03/09.
 */

class Hoge : AppCompatActivity() {

    fun hoge() {
        val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

        val queryUri = MediaStore.Files.getContentUri("external")

        val cursorLoader = CursorLoader(
                this,
                queryUri,
                arrayOf(MediaStore.Images.Media.BUCKET_ID),
                selection,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC")

        val cursor = cursorLoader.loadInBackground()

    }

    companion object {

        private val PROJECTION = arrayOf(MediaStore.Images.Media.BUCKET_ID)
    }
}
