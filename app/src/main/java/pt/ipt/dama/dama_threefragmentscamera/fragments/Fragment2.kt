package pt.ipt.dama.dama_threefragmentscamera.fragments

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import pt.ipt.dama.dama_threefragmentscamera.R
import java.io.FileDescriptor
import java.io.IOException

class Fragment2 : Fragment() {
    private lateinit var frame: ImageView
    private var imageUri: Uri? = null
    private val RESULT_LOAD_IMAGE = 123
    private val IMAGE_CAPTURE_CODE = 654

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.fragment2Button)
        // Use the button reference to set listeners, change properties, etc.
        frame = view.findViewById<ImageView>(R.id.fragment2imageView)

        // we are going to do something useful
        frame?.setOnLongClickListener {
            // the user press for long time the imageView
            openCamera()
            true
        }

        frame?.setOnClickListener {
            // the user only touch the imageView
            openGallery()
        }
    }

    /**
     * opens the gallery to show photos
     */
    private fun openGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
    }

    /**
     * Starts the camera to take the photo
     */
    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "new picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image from the camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    // *********************************************
    // auxiliary functions
    // *********************************************

    /**
     * Reads a bitmap file
     */
    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor =
                contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK) {
            val bitmap = uriToBitmap(imageUri!!)
            frame?.setImageBitmap(bitmap)
        }
        if (requestCode == RESULT_LOAD_IMAGE &&
            resultCode == Activity.RESULT_OK &&
            data != null
        ) {
            imageUri = data.data
            val bitmap = uriToBitmap(imageUri!!)
            frame?.setImageBitmap(bitmap)
        }

    }

}