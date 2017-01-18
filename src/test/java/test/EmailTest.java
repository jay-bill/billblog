package test;

import org.junit.Test;

/**
 * 有可能出现java.lang.NoClassDefFoundError: com/sun/mail/util/MailLogger，
 * 将依赖换成：
 * <dependency >  
            <groupId >com.sun.mail </groupId >  
            <artifactId >javax.mail </artifactId >  
            <version >1.5.4 </version >  
    </dependency >
    即可
 * @author jaybill
 *
 */
public class EmailTest {
	@Test
	public void test(){
//		try {
//			EmailUtil.submitEmail("2399599130@qq.com");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
