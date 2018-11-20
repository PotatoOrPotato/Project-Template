package unmanned.supermarket.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

@Controller
@RequestMapping("file")
public class FileController {


        /*<form action="<%=basePath%>/file/test1" method="post" enctype="multipart/form-data">
            <input type="file" name="multipartFile" value="fileValues">
            <input type="submit" value="提交" style="height: 35px; ">
        </form>*/

    //  上下文是要对应的。。名字一定要一样name.multipartFile= MultipartFile.multipartFile

    @RequestMapping("/test1")
    public void runwer(MultipartFile multipartFile) throws Exception {
        byte[] data = multipartFile.getBytes();
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(data);//返回Base64编码过的字节数组字符串
        System.err.println(encode);
        PrintWriter writer = new PrintWriter("D://qqqqqqqqqqqqqqq//test//abc.txt", "UTF-8");
        writer.println(encode);
        writer.close();
        System.out.println("+++++++++++++++++++++");
        // base64字符串转化成图片
        boolean a = GenerateImage(encode);
        System.out.println(a);
    }


    //图片转化成base64字符串
    public static String GetImageStr() {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "D://qqqqqqqqqqqqqqq//test//wx_camera_1523021056870.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }


    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) { //图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {//Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpg图片
            String imgFilePath = "D://qqqqqqqqqqqqqqq//test//222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
