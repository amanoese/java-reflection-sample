import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ System.class })
public class EnvironmentWithPowerMockTest {
  @Test
  public void replaceEnvironment(){
	PowerMockito.mockStatic(System.class);
	PowerMockito.when(System.getenv("TEMP")).thenReturn("/MY_TEMP_DIR");

	assertEquals(System.getenv("TEMP"),"/MY_TEMP_DIR");
  }
}
