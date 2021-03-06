package baidumap;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyMap extends AppCompatActivity {

    private MapView mymapview=null;
    private TextView mapip;
    private BaiduMap mbaidumap;
    private LocationClient mLocationClient=null;
    private MyLocationListener myLocationListener=new MyLocationListener();
    private boolean isfirst=true;//防止多次调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_my_map);
        initView();
        mbaidumap=mymapview.getMap();
        //地图缩放18
        MapStatusUpdate mapStatusUpdate=MapStatusUpdateFactory.zoomTo(18);
        mbaidumap.setMapStatus(mapStatusUpdate);

        //获取地图UI控制器，隐藏指南针
        UiSettings uiSettings=mbaidumap.getUiSettings();
        uiSettings.setCompassEnabled(false);//不显示


//动态申请权限
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MyMap.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MyMap.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MyMap.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions =permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MyMap.this, permissions, 1);
        }

        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myLocationListener);
        initLocationOption();
        mbaidumap.setMyLocationEnabled(true);
        MyLocationConfiguration.LocationMode mode = MyLocationConfiguration.LocationMode.COMPASS;
        boolean enableDirection = true;
        BitmapDescriptor customMarker = new BitmapDescriptorFactory().fromResource(R.drawable.dinwei);
        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(mode, enableDirection, customMarker);
        mbaidumap.setMyLocationConfiguration(myLocationConfiguration);
        //开始定位
        mLocationClient.start();


    }

    private void initLocationOption() {
        //声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();


        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(5000);
        //可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
        //可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
        //可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
        //可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mLocationClient.setLocOption(locationOption);

    }

    /**
     * 继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。
     */
    private class MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mymapview == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mbaidumap.setMyLocationData(locData);
            navigateTo(location);
            //设置text
            StringBuilder strbuilder=new StringBuilder();
            strbuilder.append("纬度:").append(location.getLatitude()).append("    ");
            strbuilder.append("经线:").append(location.getLongitude()).append("\n");
            strbuilder.append("国家:").append(location.getCountry());
            strbuilder.append("省:").append(location.getProvince());
            Log.d("LTY",location.getProvince());
            strbuilder.append("市:").append(location.getCity());
            strbuilder.append("区:").append(location.getDistrict());
            strbuilder.append("街道:").append(location.getStreet()).append("\n");
            strbuilder.append("错误代码:").append(location.getAdCode()).append("\n"); //获取adcode
            strbuilder.append("定位方式:");
            if (location.getLocType()== BDLocation.TypeGpsLocation){
                strbuilder.append("GPS");
            }else if (location.getLocType()== BDLocation.TypeNetWorkLocation){
                strbuilder.append("网络");
            }
            mapip.setText(strbuilder);

        }
    }
    private void navigateTo(BDLocation location){
        if (isfirst){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);
            mbaidumap.animateMapStatus(update);
            isfirst=false;
        }

}

    //定位初始化
//    private void initLocation() {
//
//        mLocationClient=new LocationClient(MyMap.this);
//        LocationClientOption option=new LocationClientOption();
//        // 设置是否需要地址信息，默认为无地址
//        option.setIsNeedAddress(true);
//        option.setIsNeedLocationDescribe(true);
//        option.setIsNeedLocationPoiList(true);
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        option.setOpenGps(true);//打开GPS
//        option.setCoorType("bd09ll");//设置坐标类型
//        option.setScanSpan(1000);//位置获取时间
//        mLocationClient.setLocOption(option);
//        //注册LocationListener监听器
//        MyLocationListener myLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(myLocationListener);
//        //开启地图定位图层
//        mLocationClient.start();
//    }
//

        @Override
    protected void onDestroy() {
        super.onDestroy();
        mymapview.onDestroy();
        mbaidumap.setMyLocationEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mymapview.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mymapview.onResume();
    }

    private void initView() {
        mymapview = (MapView) findViewById(R.id.mymapview);
        mapip = (TextView) findViewById(R.id.mapip);

    }

}