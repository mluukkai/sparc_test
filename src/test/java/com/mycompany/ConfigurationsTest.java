
package com.mycompany;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

public class ConfigurationsTest {    
    
    @Test
    public void usesTheDefaultPortIfNoEnvSet(){
        assertEquals(4567, Main.getHerokuAssignedPort());
    }

    @Test
    public void usesDefinedPortIfEnvSet(){
        Main.setEnvPort("7654");
        assertEquals(7654, Main.getHerokuAssignedPort());
        Main.setEnvPort(null);
    }
}
