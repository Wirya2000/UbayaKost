package id.ubaya.a160419033_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import id.ubaya.a160419033_ubayakost.R

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import id.ubaya.a160419033_ubayakost.viewmodel.MapViewModel

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var viewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id: String? = MapFragmentArgs.fromBundle(requireArguments()).kostId
        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        viewModel.fetch(id)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        viewModel.mapLiveData.observe(viewLifecycleOwner) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(it.x.toDouble(), it.y.toDouble()))
                    .title(it.name)
            )
            var zoomLevel: Float = 16.0f
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.x.toDouble(), it.y.toDouble()), zoomLevel))
        }

    }

}