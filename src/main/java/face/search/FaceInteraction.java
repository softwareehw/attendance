package face.search;

import face.search.HttpUtil;
import face.search.GsonUtils;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;



public class FaceInteraction {
	  //录入照片入库
	  public static String add(String path,int employee_id) {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
	        byte[] bytes1 = null;
			try {
				//读取图片作为字符串返回
				bytes1 = FileUtil.readFileByBytes(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String image1 = Base64Util.encode(bytes1);
	        
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", image1);
	            map.put("group_id", "employee");
	            
	            //user_id应该为员工的工号
	            map.put("user_id", employee_id);
	            map.put("user_info", "picture");
	            map.put("liveness_control", "NORMAL");
	            map.put("image_type", "BASE64");
	            map.put("quality_control", "LOW");

	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = AuthService.getAuth();
                
	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            JSONObject ans = new JSONObject();
	            JSONObject jsonObject = new JSONObject(result);
	            if(jsonObject.getInt("error_code")==0) {
	            	
	    			ans.put("state", 1);
	            	return ans.toString();
	            }
	            else {
	            	ans.put("state", "0");
	            	ans.put("error_meaasge", "识别失败，请重新上传");
	            	return ans.toString();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JSONObject ans = null;
				ans.put("state", "0");
            	ans.put("error_meaasge", "识别失败，请重新上传");
	            return ans.toString();
	        }
	        
	   }
	  
	  //人脸更新
	  public static boolean update(String path,int employee_id) {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
	        byte[] bytes1 = null;
			try {
				//读取图片作为字符串返回
				bytes1 = FileUtil.readFileByBytes(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String image1 = Base64Util.encode(bytes1);
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", image1);
	            map.put("group_id", "employee");
	            map.put("user_id", employee_id);
	            map.put("user_info", "update");
	            map.put("liveness_control", "NORMAL");
	            map.put("image_type", "BASE64");
	            map.put("quality_control", "LOW");
	            String param = GsonUtils.toJson(map);
	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = AuthService.getAuth();
	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            JSONObject jsonObject = new JSONObject(result);
	            int t=jsonObject.getInt("error_code");
	            if(t==0) {
	            	return true;
	            }
	            else {
	            	return false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	  
	  
	  //查看照片库中拥有的员工编号
	  public static List<String> getUsers() {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers";
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("group_id", "employee");

	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = AuthService.getAuth();

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            //System.out.println(result);
	            JSONObject jsonObject = new JSONObject(result);
	            JSONObject t=jsonObject.getJSONObject("result");
	           
	            JSONArray r=t.getJSONArray("user_id_list");
	            List<String> ans=new ArrayList<String>();
	            for(int i=0;i<r.length();i++) {
	            	ans.add(r.getString(i));
	            }
	            return ans;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	  
	  
	  //人脸删除 删除成功返回true 否则返回false
	  public static boolean delete(int employee_id) {
		  String url="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";
		  try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("user_id", employee_id);
	            map.put("group_id", "employee");

	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = AuthService.getAuth();

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            JSONObject jsonObject = new JSONObject(result);
	            int t=jsonObject.getInt("error_code");
	            if(t==0) {
	            	return true;
	            }
	            else {
	            	return false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  
		  return false;
		  
	  }
	  	
	    public static void main(String[] args) {
	    	
	    	String path="C:\\Users\\Administrator\\Desktop\\保研\\me\\扫描材料\\照片\\微信图片_201905212042063.jpg";
	    	
	    	//System.out.println(FaceInteraction.add(path, 12345));
	    	//FaceInteraction.update(path,1234);
//	    	List<String> test=FaceInteraction.getUsers();
//	    	for(int i=0;i<test.size();i++) {
//	    		System.out.println(test.get(i));
//	    	}
	    	//System.out.println(FaceInteraction.delete("user1"));
	    }
	
}
