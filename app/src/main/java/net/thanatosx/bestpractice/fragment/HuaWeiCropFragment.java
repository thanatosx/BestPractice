package net.thanatosx.bestpractice.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.thanatosx.bestpractice.R;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by thanatosx on 2016/11/23.
 */

public class HuaWeiCropFragment extends BaseFragment {

    @Bind(R.id.image)
    ImageView mImage;

    private Uri mTempUri;

    @Override
    protected int getContentView() {
        return R.layout.fragment_buttom;
    }

    @OnClick(R.id.btn_normal)
    void onClick() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"), 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("oschina", "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("oschina", "------------");
        if (resultCode != Activity.RESULT_OK) return;
        Log.i("oschina", "============");
        switch (requestCode) {
            case 0:
                Log.i("oschina", "is emulated: " + Environment.isExternalStorageEmulated());
                Log.i("oschina", "=======1=====" + Environment.getExternalStorageState());
                Log.i("oschina", "storage: " + Environment.getExternalStorageState());
                Log.i("oschina", "external cache dir: " + getContext().getExternalCacheDir());
                Log.i("oschina", "external null dir: " + getContext().getExternalFilesDir(null));
                Log.i("oschina", "external pictures dir: " + getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                Log.i("oschina", "external storage pictures dir: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
                File file = new File(getContext().getExternalCacheDir(), "IMG_CROP.jpeg");
                Log.i("oschina", "file: " + file.getAbsolutePath());
                Log.i("oschina", "file: " + file.getPath());
                Log.i("oschina", "======2======");
                if (!file.exists())
                    Log.i("oschina", "======3======");
                    try {
                        boolean isCreated = file.createNewFile();
                        Log.i("oschina", "isCreated: " + isCreated);
                    } catch (IOException e) {
                        Log.i("oschina", "what is problem??? : " + e.getLocalizedMessage());
                        e.printStackTrace();
                        return;
                    }
                Log.i("oschina", "======4======");
                mTempUri = Uri.fromFile(file);
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(data.getData(), "image/*");
//                mTempUri = intent.getData();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mTempUri);
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 1);// 裁剪框比例
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 200);// 输出图片大小
                intent.putExtra("outputY", 200);
                intent.putExtra("scale", true);// 去黑边
                intent.putExtra("return-data", false);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("scaleUpIfNeeded", true);// 去黑边
                startActivityForResult(intent, 1);
                Log.i("oschina", "======5======");
                break;
            case 1:
                Log.i("oschina", "======6======");
                Bitmap bitmap = null;
                Log.i("oschina", "data is " + data);
                if (data != null) {
                    Uri mCropImageUri = data.getData();
                    if (mCropImageUri != null) {
                        bitmap = BitmapFactory.decodeFile(mCropImageUri.getPath());
                        mImage.setImageBitmap(bitmap);
                        break;
                    }
                }
                bitmap = BitmapFactory.decodeFile(mTempUri.getPath());
                mImage.setImageBitmap(bitmap);
                break;
        }
    }
}
