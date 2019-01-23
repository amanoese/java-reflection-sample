import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EnvironmentTest {
  @Test
  public void replaceEnvironment() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

	//Final Classのメソッドを取得しアクセス可能に
	Class<?> clazz = Class.forName("java.lang.ProcessEnvironment");
    Field theCaseInsensitiveEnvironment = clazz.getDeclaredField("theCaseInsensitiveEnvironment");
	theCaseInsensitiveEnvironment.setAccessible(true);

	//システム環境変数で必要なものだけを差し替える
	Map<String,String> sytemEnviroment = (Map<String, String>) theCaseInsensitiveEnvironment.get(null);
    sytemEnviroment.put("TEMP","/MY_TEMP_DIR");

	assertEquals(System.getenv("TEMP"),"/MY_TEMP_DIR");
  }
}
