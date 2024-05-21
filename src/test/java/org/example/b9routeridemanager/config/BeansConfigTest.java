package org.example.b9routeridemanager.config;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BeansConfigTest {

    @Test
    public void testGsonBean() {
        BeansConfig beansConfig = new BeansConfig();
        Gson gson = beansConfig.gson();
        assertNotNull(gson);
    }
}
