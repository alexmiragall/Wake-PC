package com.alexmiragall.wakepc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

public class StorageUtils {

	/**
	 * Check if SDCARD is available
	 * 
	 * @return
	 */
	public static boolean checkSDCardIsAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * Get space available in SDCARD.
	 * 
	 * @return result in bytes
	 */
	public static long spaceAvailableSDCard() {
		if (!checkSDCardIsAvailable()) {
			return 0;
		}

		// do this only *after* you have checked external storage state:
		File extdir = Environment.getExternalStorageDirectory();
		StatFs stats = new StatFs(extdir.getAbsolutePath());
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2)
			return stats.getAvailableBlocks() * stats.getBlockSize();
		else
			return stats.getAvailableBytes();
	}

	/**
	 * Delete directory
	 * @param path of the directory
	 * @return
	 */
	public static boolean deleteDirectory(String path) {
		File file = new File(path);
		if (!checkSDCardIsAvailable())
			return false;
		if (!file.exists())
			return true;
		return deleteDirectory(file);
	}

	/**
	 * Delete directory and subfiles
	 * @param fileOrDirectory
	 * @return
	 */
	public static boolean deleteDirectory(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles()) {
				deleteDirectory(child);
			}
		fileOrDirectory.delete();
		return true;
	}

	/**
	 * Read text file from assets.
	 * @param context
	 * @param filePath
	 * @return
	 */
	public static String readFileAsString(Context context, String filePath) {
		InputStream input;
		try {
			input = context.getAssets().open(filePath);
			int size = input.available();
			byte[] buffer = new byte[size];
			input.read(buffer);
			input.close();
			return new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Save Bitmap as a JPEG
	 * @param file
	 * @param bitmap
	 * @return
	 */
	public static boolean saveJPEG(File file, Bitmap bitmap) {
		try {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileOutputStream fOut = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
