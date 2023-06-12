package pt.ipt.dama.dama_threefragmentscamera.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
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

class Fragment1 : Fragment() {
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.fragment1Button)
        // Use the button reference to set listeners, change properties, etc.
        imageView = view.findViewById<ImageView>(R.id.fragment1imageView)

        button.setOnClickListener {
            takePhoto()
        }
    }

    /**
     * function to open the camera and take one photo
     */
    private fun takePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // start the camera action
        resultLauncher.launch(cameraIntent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                imageView.setImageBitmap(data?.extras?.get("data") as Bitmap)
            }
        }
}