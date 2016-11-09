package com.example.rhdbs.chirend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rhdbs on 2016-11-09.
 */

public class CstAdater extends BaseAdapter {

    // 문자열을 보관 할 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    ListViewItem listViewItem;

    // 생성자
    public CstAdater() {

    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view, parent, false);

            //위젯 설정
            ImageView imgVw = (ImageView) convertView.findViewById(R.id.imageview1);
            // TextView에 현재 position의 문자열 추가
            TextView text1 = (TextView) convertView.findViewById(R.id.text1);
            //text.setText(m_List.get(position));
            TextView text2 = (TextView) convertView.findViewById(R.id.text2);
            TextView text3 = (TextView) convertView.findViewById(R.id.text3);

            ListViewItem listViewItem = listViewItemList.get(position);

            imgVw.setImageBitmap(listViewItem.getIcon());
            text1.setText(listViewItem.getTitle());
            text2.setText(listViewItem.getPosition());
            text3.setText(listViewItem.getAge());

            // 리스트 아이템을 터치 했을 때 이벤트 발생
           /* convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    //Toast.makeText(context, "리스트 클릭 : "+listViewItemList.get(pos), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContentsList.mContext, Last_page.class);
                    String str = personList.get(i).get(TAG_TEXT1);
                    String str2 = personList.get(i).get(TAG_TEXT2);
                    String str3 = personList.get(i).get(TAG_TEXT3);

                    intent.putExtra("content", str);
                    ///////////////////////////////////////////////////
                    intent.putExtra("position",str2);
                    intent.putExtra("age",str3);
                    startActivity(intent);
                }
            });*/

            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            /*convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : "+listViewItemList.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });*/
        }

        return convertView;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(Bitmap icon, String title, String position, String age) {
        //listViewItemList.add(_msg);
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setPosition(position);
        item.setTitle(title);
        item.setAge(age);

        listViewItemList.add(item);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        listViewItemList.remove(_position);
    }
}
