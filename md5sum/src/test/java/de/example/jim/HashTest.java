package de.example.jim;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HashTest {

    @Test
    public void computeMD5() {
        // shell% echo -n a | md5sum
        assertThat(Hash.toHexString(Hash.computeMD5("a".getBytes())), is("0CC175B9C0F1B6A831C399E269772661") );

        // shell% echo -n abc | md5sum
        assertThat(Hash.toHexString(Hash.computeMD5("abc".getBytes())), is("900150983CD24FB0D6963F7D28E17F72") );

        // shell% echo -n password | md5sum
        assertThat(Hash.toHexString(Hash.computeMD5("password".getBytes())), is("5F4DCC3B5AA765D61D8327DEB882CF99") );

     }

}
