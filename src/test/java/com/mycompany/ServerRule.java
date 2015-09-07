package com.mycompany;

import com.mycompany.paivanuutiset.PaivanUutiset;
import org.junit.rules.ExternalResource;
import spark.Spark;

public class ServerRule extends ExternalResource {

    private final int port;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        Spark.port(port);
        Main.setUutishakija(uutisStub());
        Main.main(null);
    }

    @Override
    protected void after() {
        Spark.stop();
    }
    
    PaivanUutiset uutisStub(){
        return new PaivanUutiset(){

            @Override
            public String haeSuosituinUutinen() {
                return "Suosituin uutinen on Alda: A music programming language by daveyarwood, url: http://daveyarwood.github.io/alda/2015/09/05/alda-a-manifesto-and-gentle-introduction/";
            }

            @Override
            public String haeViimeisinUutinen() {
                return "Viimeisin uutinen on How Insurance Companies Profit from “Wearables” by cybernot, url: https://www.sherbit.io/the-insurance-industry-and-the-quantified-self/";
            }
        };
    }
}
