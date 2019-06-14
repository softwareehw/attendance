package face.search;

import face.search.HttpUtil;
import face.search.GsonUtils;

import java.io.IOException;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
* 在线活体检测
*/
public class FaceVerify {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static boolean faceVerify(MultipartFile image) {    	
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceverify";
        
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
        	List<Object> list = new ArrayList<Object>();
        	Map<String, Object> map = new HashMap<>();
            map.put("image", image1);
            map.put("image_type", "BASE64");
            list.add(map);
            String param = GsonUtils.toJson(list);           
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            
            //提取livemapscore
            JSONObject obj = JSONObject.parseObject(result);
            String liveness = obj.getJSONObject("result").getString("face_liveness");
            double score = Double.parseDouble(liveness);
           if(score>0.9) {
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
}
