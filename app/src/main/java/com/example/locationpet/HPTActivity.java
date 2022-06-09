package com.example.locationpet;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.locationpet.dto.HospitalLocation;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HPTActivity extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener, MapView.POIItemEventListener {

    private String TAG = "HPTACTIVITY";

    public static boolean markerFlag = false;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HospitalLocationInterface.HOSPITAL_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static double currentLat;
    private static double currentLng;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pet_hpt_activity, container, false);

        MapView mapView = new MapView(getActivity());

        // 카카오맵 인플레이팅
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //map listener 등록
        mapView.setMapViewEventListener(this);
        mapView.setCurrentLocationEventListener(this);

        // 트래킹모드는 3가지를 지원하는데 TrakingModeOff는 현위치 트래킹 모드 및 나침반 모드 모두 꺼진다.
        // TrakingModeOnWithoutHeading 모드는 현위치 트래킹 모드가 켜지고 위치에 따라 지도 중심이 이동되고, 나침반 모드는 꺼진다.
        if (!checkLocationServiceStatus(getActivity())) {
            showDialogForLocationServiceSetting();
        } else {

        }
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                // 사용자가 GPS를 켰는지 검사하는 코드
                if (checkLocationServiceStatus(getActivity())) {
                    Log.d(TAG, "onActivityResult : GPS 활성화 되었음");
                    checkRuntimePermission();
                    return;
                }
                break;
        }
    }

    private boolean checkLocationServiceStatus(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("위치 서비스(GPS) 비활성화");
        builder.setMessage("위치 정보가 필요합니다. GPS를 켜시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void checkRuntimePermission() {
        // 런타임 퍼미션 처리 방법
        // 1. 위치 권한을 가지고 있는지 체크한다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        // 2. 이미 퍼미션을 가지고 있다면 위치 값을 가져올 수 있다. ( 안드로이도 6.0 이하는 런타임 퍼미션이 필요없어 허용된 것으로 인식하니 주의 ! )
        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {
        } else { // 퍼미션 요청을 거부한적 있다면 퍼미션 요청이 필요하다
            // 3. 사용자가 퍼미션 거부를 한 적이 있다면 요청을 진행하기전에 설명을 해준다.
            Toast.makeText(getContext(), "정보를 가져오려면 위치 정보 접근 권한이 필요합니다.",
                    Toast.LENGTH_SHORT).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSONS_REQUEST_CODE);
            } else {
                // 4. 사용자가 퍼미션 거부를 한 적이 없다면 퍼미션 요청을 바로 한다.
                // 요청 결과는 onRequestPermissionResult에서 수신된다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSONS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 요청 코드가 PERMISSONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신 되었다면
        if (requestCode == PERMISSONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;
            // 모든 권한을 허용했는지 체크
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            // 위치 값을 가져올 수 있는 경우
            if (check_result == true) {
                Log.d(TAG, "start");
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {
                    Toast.makeText(getContext(), "권한이 거부되었습니다. 다시 시도하여 퍼미션을 허용해주세요.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "권한이 거부되었습니다. 설정에서 GPS 권한을 허용해주세요.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float accuracyInMeters) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        currentLat = mapPointGeo.latitude;
        currentLng = mapPointGeo.longitude;
        Log.i(TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy(%f)",
                mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));

        if(!markerFlag){
            HospitalLocationInterface hospitalLocationInterface = retrofit.create(HospitalLocationInterface.class);

            Call<List<HospitalLocation.Response>> call = hospitalLocationInterface.HospitalLocationRequest(currentLng, currentLat, 1000);
            call.enqueue(new Callback<List<HospitalLocation.Response>>() {
                @Override
                public void onResponse(Call<List<HospitalLocation.Response>> call, Response<List<HospitalLocation.Response>> response) {
                    List<HospitalLocation.Response> hospitalData = new ArrayList<>(response.body());
                    for(HospitalLocation.Response res : hospitalData){
                        Log.d(TAG, String.valueOf(res.getHospitalLat()) + " + " + res.getHospitalLot());
                        // 마커 찍기
                        MapPOIItem marker = new MapPOIItem();
                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord( res.getHospitalLat(), res.getHospitalLot());
                        marker.setItemName(res.getHospitalName());
                        marker.setTag(0);
                        marker.setMapPoint(mapPoint);
                        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                        mapView.addPOIItem(marker);
                    }
                }

                @Override
                public void onFailure(Call<List<HospitalLocation.Response>> call, Throwable t) {

                }
            });

            markerFlag = true;
        }
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
