import org.junit.Test;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EnvironmentTest {
  @Test
  public void replaceEnvironment() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

	//Final Classのメソッドを取得しアクセス可能に
	Field theCaseInsensitiveEnvironment =  Class.forName("java.lang.ProcessEnvironment").getDeclaredField("theCaseInsensitiveEnvironment");
	theCaseInsensitiveEnvironment.setAccessible(true);

	//リフレクションでもデフォルトではPrivateまたはFinalなものは書き換えは禁止されているので可能な状態に変更
	Field field = Field.class.getDeclaredField("modifiers");
	field.setAccessible(true);
	field.setInt(theCaseInsensitiveEnvironment,theCaseInsensitiveEnvironment.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);

	//システム環境変数で必要なものだけを差し替える
	Map<String,String> seytemEnv = (Map<String, String>) theCaseInsensitiveEnvironment.get(null);
	seytemEnv.put("TEMP","/MY_TEMP_DIR");
	theCaseInsensitiveEnvironment.set(null,seytemEnv);

	assertEquals(System.getenv("TEMP"),"/MY_TEMP_DIR");
  }
}
