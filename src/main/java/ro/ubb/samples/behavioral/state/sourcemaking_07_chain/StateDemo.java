package ro.ubb.samples.behavioral.state.sourcemaking_07_chain;

import java.io.IOException;
import java.io.InputStreamReader;

class Chain {
    private int state;

    public Chain() {
        state = 0;
    }

    public void pull() {
        if (state == 0) {
            state = 1;
            System.out.println( "   low speed" );
        } else if (state == 1) {
            state = 2;
            System.out.println( "   medium speed" );
        } else if (state == 2) {
            state = 3;
            System.out.println( "   high speed" );
        } else {
            state = 0;
            System.out.println( "   turning off" );
        }
    }
}

public class StateDemo {
    public static void main( String[] args ) throws IOException {
        InputStreamReader is = new InputStreamReader( System.in );
        Chain chain = new Chain();
        while (true) {
            System.out.print( "Press 'Enter'" );
            is.read();
            chain.pull();
        }
    }
}
