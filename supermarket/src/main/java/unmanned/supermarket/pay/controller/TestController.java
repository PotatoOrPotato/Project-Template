package unmanned.supermarket.pay.controller;

import org.hibernate.util.xml.XmlDocument;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import unmanned.supermarket.pay.Utils.UploadFile;
import unmanned.supermarket.pay.cache.impl.Redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import unmanned.supermarket.pay.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement;
import java.util.stream.Stream;


@RequestMapping(value = "url")
@Controller
public class TestController {

/*    @Autowired
    private TaskExecutor taskExecutor;*/

    @Autowired
    private Redis redis;
    @Autowired
    private TestService testService;

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    public static void main(String[] args) {


    }


    @RequestMapping(value = "/testA",method=RequestMethod.POST)
    public String testA() {
        try {
            logger.info("A成功了。。。。。");


            int num = 10;

            logger.info("返回参数：{}",num);
            return "findUser";
        }catch (Exception e){
            return null;
        }
    }
    @RequestMapping(value = "/testB",method=RequestMethod.POST)
    public String testB() {
        try {
            logger.info("B成功了。。。。。");
            ModelAndView abc = new ModelAndView("abc");
            return "abc";
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/test")
    public ModelAndView inputData(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1111111111111");
        return new ModelAndView("redirect:/url/testB");
    }
    @RequestMapping(value = "/test")
    public ModelAndView inputData(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1111111111111");
        model.addObject("key1","values1");
        return new ModelAndView("forward:/url/testB");
    }
    @RequestMapping(value = "/testB")
    @ResponseBody
    public String outputData(HttpServletRequest request) {
        System.out.println("2222222222");
        return "成功了2";
    }
}
