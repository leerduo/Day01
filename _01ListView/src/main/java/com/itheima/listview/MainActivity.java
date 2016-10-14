package com.itheima.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private List<String> data;
	private ListView lv;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化测试数据
        data = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
			data.add("我是数据"+i);
		}
        lv = (ListView) findViewById(R.id.lv);

		/*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				data);

		lv.setAdapter(arrayAdapter);*/


        //创建适配器
        MyAdapter adapter = new MyAdapter();
        //给listview设置适配器
        lv.setAdapter(adapter);
    }
	
	
	class MyAdapter extends BaseAdapter{
		
		//必选方法 只有返回值大于0 才会调用getView方法
		@Override
		public int getCount() {
			//System.out.println(data.size());
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}


		//必选方法 listView的每一个条目的显示都是通过该方法返回的
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			System.out.println(parent.toString());
			//创建一个简单的TextView
			TextView tv;
			//优化
			if(convertView==null){
				tv = new TextView(MainActivity.this);
			}else{
				tv = (TextView) convertView;
			}
			tv.setText((String)getItem(position));
			return tv;
		}


	}
}
