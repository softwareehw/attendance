
package face.search;

import face.search.HttpUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import face.search.GsonUtils;

import java.io.IOException;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

public class FaceSearch {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String search(MultipartFile image) {
    	// 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        byte[] bytes1 = null;
		try {
			//读取图片作为字符串返回
			bytes1 = image.getBytes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String image1 = Base64Util.encode(bytes1);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image1);
            map.put("image_type", "BASE64");
            //map.put("face_type", "LIVE");
            map.put("group_id_list", "employee");
            map.put("quality_control", "LOW");
            map.put("liveness_control", "NORMAL");

            String param = GsonUtils.toJson(map);
            
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
//            		"24.1b1d6c4ad2608dfbd7c4dbfb756c62e6.2592000.1561192392.282335-16322631\r\n" + 
//            		"";
            
            //提取user_id
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject obj = JSONObject.parseObject(result);
            String user = obj.getJSONObject("result").getString("user_list");
            JSONArray ob = JSONArray.parseArray(user);
            String abc = ob.getString(0);
            JSONObject asd = JSONObject.parseObject(abc);
            String user_id = asd.getString("user_id");

            
            

            boolean status = result.contains("\"result\":null}");
            if(status) {
            	return "false";
            }
            else {
                String str = result.substring(result.indexOf("score"),result.indexOf("}"));
                String s = str.substring(7);
                
                double score = Double.parseDouble(s);
                if(score > 90) {
            		return user_id;
                }
                else {
                	return "false";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
    
    public static void main(String[] args) {
    	
    	
    }
}
