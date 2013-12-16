package jsonTable;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.*;
import android.widget.*;

public class jsonTable {

	public jsonTable(JSONArray jsonArray,String[] jsonTexts,TableLayout table,boolean addTableRows) throws Exception{
		if(addTableRows)
			jsonWithTable(jsonArray,jsonTexts,table);
		else
			jsonWithoutTable(jsonArray,jsonTexts,table);
	}
	private void jsonWithTable(JSONArray jsonArray,String[] jsonTexts,TableLayout table) throws Exception{
		int len=jsonArray.length();
		ArrayList<String> array=new ArrayList<String>();
		for(int i=0;i<len;i++){
			JSONObject childJSONObject = null;
			try {
				childJSONObject = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
				throw new Exception("Child Json Object error");
			}
		
			for(int j=0;j<childJSONObject.length();j++){
				array.add(childJSONObject.getString(jsonTexts[j]));
			}
		}

		int j=0;
		int numOfRows=array.size()/jsonTexts.length;

		for(int i=0;i<numOfRows;i++){
			TableRow r=new TableRow(table.getContext());
			for(int k=0;k<jsonTexts.length;k++){
				TextView t=new TextView(table.getContext());
				t.setText(array.get(i*(jsonTexts.length)+k));
				r.addView(t);
			}
			table.addView(r);
		}
		
	}
	private void jsonWithoutTable(JSONArray jsonArray,String[] jsonTexts,TableLayout table) throws Exception{
		int len=jsonArray.length();
		ArrayList<String> array=new ArrayList<String>();
		for(int i=0;i<len;i++){
			JSONObject childJSONObject = null;
			try {
				childJSONObject = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
				throw new Exception("Child Json Object error");
			}
		
			for(int j=0;j<childJSONObject.length();j++){
				array.add(childJSONObject.getString(jsonTexts[j]));
			}
		}
		int j=0;

		for (int i = 0; i < table.getChildCount(); i++) {
		    View child = table.getChildAt(i);

		    if (child instanceof TableRow) {
		        TableRow row = (TableRow) child;

		        for (int x = 0; x < row.getChildCount(); x++) {
		            TextView view = (TextView)row.getChildAt(x);
		            view.setText(array.get(j));
		            j++;
		        }
		    }
		}
		
	}
}
