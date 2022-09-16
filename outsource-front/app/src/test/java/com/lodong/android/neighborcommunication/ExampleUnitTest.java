package com.lodong.android.neighborcommunication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void 아오빡쳐(){
        Repository repository = RepositoryImpl.getInstance();
        repository.getMemberList();
    }
}