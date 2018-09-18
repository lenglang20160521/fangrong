import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestHttpClientUtil {

    @Test
    void  testGetNewToken(){
        String newToken = HttpClientUtil.getNewToken();
        Assert.assertNotNull(newToken);
    }


}
