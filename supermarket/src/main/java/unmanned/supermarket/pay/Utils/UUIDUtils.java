package unmanned.supermarket.pay.Utils;

import java.util.UUID;

/*
 *
 */
public class UUIDUtils {

	/**
	 * java.util自带的（理论上id不会重复）
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 用手机号或者身份证+UUID：组成唯一
	 * @param code
	 * @return
	 */
	public static String getIdAndUUID(String code){
		return (UUID.randomUUID()+code).toString().replace("-", "");
	}
}

