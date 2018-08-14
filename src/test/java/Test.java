import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.jeason.common.utils.IdGen;

public class Test {

	@org.junit.Test
	public void testEntry(){
		//加密方式
        String algorithmName="MD5";

        //加密的字符串
        String source="11111111";
        
        //盐值，用于和密码混合起来用
        ByteSource salt = ByteSource.Util.bytes("DT1705178");

        //加密的次数,可以进行多次的加密操作
        int hashIterations = 1;

        //通过SimpleHash 来进行加密操作
        SimpleHash hash = new SimpleHash(algorithmName, source, salt, hashIterations);

        System.out.println(hash.toString().trim());
	}
	@org.junit.Test
	public void testIdGen(){
		for(int i=0;i<9;i++){
		System.out.println(IdGen.createUUID().trim());
		}
	}
}
